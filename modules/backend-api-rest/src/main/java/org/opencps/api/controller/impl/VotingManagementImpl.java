package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.constants.StatisticManagementConstants;
import org.opencps.api.controller.VotingManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.statistic.model.OpencpsVotingStatistic;
import org.opencps.statistic.service.OpencpsVotingStatisticLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.feedback.action.VotingActions;
import backend.feedback.action.impl.VotingActionsImpl;

public class VotingManagementImpl implements VotingManagement {
	private Log _log = LogFactoryUtil.getLog(VotingManagementImpl.class);

	@Override
	public Response checkVotePermission(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String applicantIdNo, String dossierNo) {
		JSONObject result = JSONFactoryUtil.createJSONObject();
		
		List<Dossier> lstDossiers = DossierLocalServiceUtil.findByDN_AN(dossierNo, applicantIdNo);
		if (lstDossiers.size() > 0) {
			result.put(ConstantUtils.VOTING_HASPERMISSION, true);
		}
		else {
			result.put(ConstantUtils.VOTING_HASPERMISSION, false);
		}
		
		return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
	}

	@Override
	public Response getStatisticDVCQG(HttpServletRequest request, HttpHeaders header, Company company,
									  Locale locale, User user, ServiceContext serviceContext) {
		try {
			DVCQGIntegrationActionImpl integrationAction = new DVCQGIntegrationActionImpl();
			integrationAction.syncSummaryVote();
			return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
		}catch (Exception e) {
			_log.error(e.getMessage());
		}

		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
	}

	@Override
	public Response syncStatisticVoteToDVC(HttpServletRequest request, HttpHeaders header,
										   Company company, Locale locale, User user,
										   ServiceContext serviceContext, String body) {
		try {
			//Remove old data
			List<OpencpsVotingStatistic> lists = OpencpsVotingStatisticLocalServiceUtil.getOpencpsVotingStatistics(0, 100);
			for(OpencpsVotingStatistic oneStatistic: lists){
				OpencpsVotingStatisticLocalServiceUtil.removeVotingStatisticByMonthYear(oneStatistic.getGroupId(),
						oneStatistic.getMonth(), oneStatistic.getYear());
			}

			//Sync new data from MCDT
			JSONArray votes = JSONFactoryUtil.createJSONArray(body);
			JSONObject item;

			for (int i = 0; i < votes.length(); i++) {
				item = votes.getJSONObject(i);
				OpencpsVotingStatisticLocalServiceUtil.updateVotingStatistic(0L,
						item.getLong("companyId", 0L), item.getLong("groupId", 0L),
						item.getLong("userId", 0L), item.getString("userName", ""),
						item.getInt("month", 0), item.getInt("year", 0),
						item.getString("votingSubject", ""), item.getInt("totalVoted", 0),
						item.getInt("veryGoodCount", 0), item.getInt("goodCount", 0),
						item.getInt("badCount", 0), item.getInt("percentVeryGood", 0),
						item.getInt("percentGood", 0), item.getInt("percentBad", 0),
						item.getString("govAgencyCode", ""), item.getString("govAgencyName", ""),
						item.getString("domainCode", ""), item.getString("domainName", ""),
						item.getString("votingCode", ""), item.getInt("totalCount", 0));
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(null).build();
		}catch (Exception e) {
			_log.error(e.getMessage());
		}
		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
	}

	@Override
	public Response getEmployeeVotingReport(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String body) {
		//long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		try {
			
			JSONArray bodyObj = JSONFactoryUtil.createJSONArray(body);

			VotingActions actions = new VotingActionsImpl();
			
			JSONObject result = actions.getEmployeesVotingStatistic(bodyObj);
		
			return Response.status(HttpURLConnection.HTTP_OK).entity(result.toJSONString()).build();
		} catch (Exception e) {
			_log.error(e);
		}
		
		return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).entity(null).build();
	}

	
	@Override
	public Response exportEmployeeVotingReport(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String body) {
		XSSFWorkbook workbook = null;
		try {

			JSONArray bodyObj = JSONFactoryUtil.createJSONArray(body);

			VotingActions actions = new VotingActionsImpl();

			JSONObject result = actions.getEmployeesVotingStatistic(bodyObj);

			JSONArray voting = result.getJSONArray("voting");

			workbook = new XSSFWorkbook();

			XSSFSheet sheet = workbook.createSheet("Voting");

			// int headerRows = 2;

			// STT + Ten DV + Luot Danh Gia + Cau hoi 1 + ... + Cau hoi N
			// int columns = 1 + 1 + 1;

			int startRow = 0;

			int startColumn = 0;

			// Row headerRow = sheet.createRow(startRow);

			List<String> headerRow1 = new ArrayList<String>();
			headerRow1.add("STT");
			headerRow1.add("Tên Đơn Vị");
			headerRow1.add("Số Lượt Đánh Giá");

			List<String> headerRow2 = new ArrayList<String>();

			List<Object[]> headerRangeAddress = new ArrayList<Object[]>();
			headerRangeAddress.add(new Object[] { startRow, startRow + 1, startColumn, startColumn});
			headerRangeAddress
					.add(new Object[] { startRow, startRow + 1, startColumn + 1, startColumn + 1});
			headerRangeAddress
					.add(new Object[] { startRow, startRow + 1, startColumn + 2, startColumn + 2});

			Row row1 = sheet.createRow(startRow);
			int row1ColCount = 0;
			for (String value : headerRow1) {
				Cell cell = row1.createCell(startColumn + row1ColCount);
				cell.setCellValue(value);
				row1ColCount++;
			}

			if (voting != null && voting.length() > 0) {
				int count = 0;
				for (int i = 0; i < voting.length(); i++) {
					// headerRow1.add(voting.getJSONObject(i).getString("question"));
					JSONArray choices = voting.getJSONObject(i).getJSONArray("choices");
					for(int c = 0 ; c < choices.length(); c++) {
						headerRow2.add(choices.getString(c));
					}
					String question = voting.getJSONObject(i).getString("question");
					Cell cell = row1.createCell(startColumn + row1ColCount);
					cell.setCellValue(question);
					row1ColCount += choices.length();

					// columns += choices.length();
					headerRangeAddress.add(new Object[] { startRow, startRow, startColumn + 3 + count,
							startColumn + 3 + count + choices.length() - 1 });
					count += choices.length();
				}
			}

			Row row2 = sheet.createRow(startRow + 1);
			int row2ColCount = 0;
			for (String value : headerRow2) {
				Cell cel2 = row2.createCell(startColumn + 3 + row2ColCount);
				cel2.setCellValue(value);
				row2ColCount++;
			}

			for (Object[] headerName : headerRangeAddress) {
				CellRangeAddress rangeAddress = new CellRangeAddress((int) headerName[0], (int) headerName[1],
						(int) headerName[2], (int) headerName[3]);
				sheet.addMergedRegion(rangeAddress);
			}
			
			JSONArray data = result.getJSONArray("data");
			
			if(data != null && data.length() > 0) {
				startRow += 2;
				int stt = 1;
				for(int d = 0; d < data.length(); d++) {
					JSONObject jobposObj = data.getJSONObject(d);
					Row jobposRow = sheet.createRow(startRow);
					Cell cell1 = jobposRow.createCell(startColumn);
					cell1.setCellValue(String.valueOf(stt));
					Cell cell2 = jobposRow.createCell(startColumn + 1);
					cell2.setCellValue(jobposObj.getString("jobposName"));
					
					JSONArray empArr = jobposObj.getJSONArray("employees");
					
					if(empArr != null && empArr.length() > 0) {
						for(int e = 0; e < empArr.length(); e++) {
							startRow += 1;
							Row empRow = sheet.createRow(startRow);
							Cell c1 = empRow.createCell(startColumn);
							c1.setCellValue(stt + "." + (e + 1));
							JSONObject empObj = empArr.getJSONObject(e);
							String name = empObj.getString("name");
							int totalVote = empObj.getInt("totalVote");
							Cell c2 = empRow.createCell(startColumn + 1);
							c2.setCellValue(name);
							Cell c3 = empRow.createCell(startColumn + 2);
							c3.setCellValue(totalVote);
							JSONArray votingArr = empObj.getJSONArray("voting");
							if(votingArr != null && votingArr.length() > 0) {
								for(int v = 0; v < votingArr.length(); v++) {
									JSONObject votingObj = votingArr.getJSONObject(v);
									int pos = votingObj.getInt("pos");
									JSONArray selected = votingObj.getJSONArray("selected");
									for(int s = 0; s < selected.length(); s++) {
										int offset = 0;
										for(int t = 0;  t < voting.length(); t++) {
											if(pos == t) {
												break;
											}
											
											offset += voting.getJSONObject(t).getJSONArray("choices").length();
										}
										Cell c = empRow.createCell(startColumn + 3 + offset + s);
										c.setCellValue(selected.getInt(s));
									}
								}
							}
						}
					}
					
					stt++;
					startRow++;
				}
			}

			File file = FileUtil.createTempFile("xlsx");

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			byte[] input = bos.toByteArray();
			try {
				FileOutputStream out = new FileOutputStream(file);
				out.write(input);
				out.flush();
				out.close();
				workbook.close();
			} catch (Exception e) {
				_log.debug(e);
			}

			Response.ResponseBuilder responseBuilder = Response.ok((Object) file);

			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME),
					file.getName());

			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename);
			responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_EXCEL);
			return responseBuilder.build();
		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}

	}
}
