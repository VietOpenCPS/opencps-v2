package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.*;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.opencps.adminconfig.model.AdminConfig;
import org.opencps.adminconfig.service.AdminConfigLocalServiceUtil;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.constants.StatisticManagementConstants;
import org.opencps.api.controller.AdminConfigManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.springframework.http.HttpStatus;

import backend.admin.config.whiteboard.BundleLoader;
import backend.auth.api.exception.BusinessExceptionImpl;

public class AdminConfigManagementImpl implements AdminConfigManagement {
	private static String convertDateToString(Date date) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(_TIMESTAMP);

		if (Validator.isNull(date) || Validator.isNull(_TIMESTAMP)) {
			return StringPool.BLANK;
		}
		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone(ConstantUtils.TIMEZONE_ASIA_HOCHIMINH));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	public static final String _TIMESTAMP = "dd/MM/yyyy HH:mm";
	private static final Log _log = LogFactoryUtil.getLog(AdminConfigManagementImpl.class);

	private static final String TYPE = "type";
	private static final String CMD = "cmd";
	private static final String GET = "get";
	private static final String DELETE = "delete";
	private static final String ADMIN = "admin";
	private static final String API = "api";
	private static final String BUNDLE_NAME = "bundle_name";
	private static final String SERVICE_UTIL_NAME = "service_util_name";
	private static final String MODEL_NAME = "model_name";
	private static final String DATA = "data";
	private static final String ID = "id";
	private static final String STATUS = "status";

	private static final String CODE = "code";

	private static final String CONFIG = "config";

	private static final String FILTER = "filter";

	private static final String RESPONE = "respone";

	private static final String COMPARE = "compare";

	private static final String COUNTER = "counter";

	private static final String END = "end";

	private static final String START = "start";
	private static final String COMPANY_ID = "companyId";
	private static final String DYNAMIC_QUERY = "dynamicQuery";
	private static final String RESPONSE_TYPE = "responeType";
	private static final String DETAIL = "detail";
	private static final String TITLE = "title";
	private static final String COLUMN = "column";
	private static final String COLUMNS = "columns";
	private static final String LIST_TABLE_MENU = "listTableMenu";
	private static final String PUBLIC_MANAGER = "publicManager";
	private static final String MENU = "menu";
	private static final String NAME = "name";
	private static final String VALUE_FILTER = "value_filter";
	private static final String NUMBER = "number";
	private static final String AUTO_COMPLETE = "autocomplete";
	private static final String TYPE_INT = "int";
	private static final String DATA_TYPE = "data_type";
	private static final String KEY = "key";
	private static final String CHECK_BOX = "checkbox";
	private static final String QUERY_LIKE = "like";
	private static final String COMPARE_LT = "lt";
	private static final String COMPARE_LE = "le";
	private static final String COMPARE_GT = "gt";
	private static final String COMPARE_GE = "ge";
	private static final String LOCAL_ACCESSS = "localaccess";
	private static final String P_AUTH = "Token";
	private static final String USER_REQUEST_ID = "userid";
	private static final String USER_ID = "USER_ID";
	private static final String CLASSNAME_WORKING_UNIT = "opencps_workingunit";
	private static final String CLASSNAME_APPLICANT = "opencps_applicant";
	private static final String HEARDER_NAME = "headersName";
	private static final String DETAIL_COLUMN = "detailColumns";
	private static final String EXT_FORM = "extForm";
	private static final String DEPENDENCY_TITLE = "dependency_title";
	private static final String DEPENDENCY_LINK = "dependency_link";
	private static final String DYNAMIC_COUNT = "dynamicQueryCount";
	private static final String PROCESS_DELETE = "adminProcessDelete";
	private static final String PROCESS_DATA = "adminProcessData";
	private static final String HEADERS = "headers";
	private static final String CLASSNAME_EMPLOYEE = "opencps_employee";
	private static final String ACCEPT = "Accept";
	private static final String SORT = "sort";
	private static final String SORT_ASC = "asc";
	private static final String FILE_ITEM = "opencps_fileitem";
	private static final String CLASSNAME_DELIVERABLE_TYPE = "opencps_deliverabletype";
	private static final String TYPE_CODE = "typeCode";
	private static final String NOK = "NOK";

	@Override
	public Response onMessage(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User u,
			ServiceContext serviceContext, String text) {
		JSONObject messageData = JSONFactoryUtil.createJSONObject();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		//String portalURL = PortalUtil.getPortalURL(request);
		
		try {
			JSONObject message = JSONFactoryUtil.createJSONObject(text);
			_log.debug("SOCKET MESSAGE: " + message.toJSONString());
			try {
				
				if (message.getString(TYPE).equals(ADMIN)) {

					String code = message.getString(CODE);
					String id = message.getString(ID);
	
					AdminConfig adminConfig = AdminConfigLocalServiceUtil.fetchByCode(code);
	
					String bunderStr;
					String modelStr;
					String serviceUtilStr;
	
					if (Validator.isNull(adminConfig)) {
						bunderStr = message.getString(BUNDLE_NAME);
						modelStr = message.getString(MODEL_NAME);
						serviceUtilStr = message.getString(SERVICE_UTIL_NAME);
					} else {
						bunderStr = adminConfig.getBundleName();
						modelStr = adminConfig.getModelName();
						serviceUtilStr = adminConfig.getServiceUtilName();
					}
	
					BundleLoader bundleLoader = new BundleLoader(bunderStr != null ? bunderStr : StringPool.BLANK);
					Class<?> model = bundleLoader.getClassLoader().loadClass(modelStr != null ? modelStr : StringPool.BLANK);
	
					Method method = null;
	
					int lengColumns = 0;
					
					if (message.getString(CMD).equals(GET)) {
	
						method = bundleLoader.getClassLoader().loadClass(serviceUtilStr != null ? serviceUtilStr : StringPool.BLANK).getMethod(DYNAMIC_QUERY);
	
						DynamicQuery dynamicQuery = (DynamicQuery) method.invoke(model);
	
						if (Validator.isNotNull(adminConfig) && !DETAIL.equals(message.getString(RESPONSE_TYPE))) {
							String columns = adminConfig.getColumns();
	
							JSONArray arraysColumn = JSONFactoryUtil.createJSONArray(columns);
							
							if (arraysColumn.length() > 0) {
								
								lengColumns = arraysColumn.length();
								
								ProjectionList projectionList = ProjectionFactoryUtil.projectionList();
	
								for (int i = 0; i < arraysColumn.length(); i++) {
	
									JSONObject column = arraysColumn.getJSONObject(i);
									
									projectionList.add(ProjectionFactoryUtil.property(column.getString(COLUMN)));

								}
								
								if (LIST_TABLE_MENU.equals(message.getString(RESPONE))) {
									projectionList.add(ProjectionFactoryUtil.property(PUBLIC_MANAGER));
								}
	
								dynamicQuery.setProjection(projectionList);
	
							}

						} else if (MENU.equals(message.getString(RESPONSE_TYPE))) {
							ProjectionList projectionList = ProjectionFactoryUtil.projectionList();

							projectionList.add(ProjectionFactoryUtil.property(CODE));
							projectionList.add(ProjectionFactoryUtil.property(NAME));

							dynamicQuery.setProjection(projectionList);
						}
	
						if (Validator.isNotNull(message.getJSONArray(FILTER))
								&& message.getJSONArray(FILTER).length() > 0) {
	
							for (int i = 0; i < message.getJSONArray(FILTER).length(); i++) {
	
								JSONObject filter = message.getJSONArray(FILTER).getJSONObject(i);

								if (Validator.isNotNull(filter.getString(VALUE_FILTER))
										&& filter.getString(VALUE_FILTER).length() > 0) {

									if (StringPool.EQUAL.equals(filter.getString(COMPARE))) {
										if (NUMBER.equals(filter.getString(TYPE)) || AUTO_COMPLETE.equals(filter.getString(TYPE))) {
											if (filter.getBoolean(TYPE_INT)) {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getInt(VALUE_FILTER)));
											} else {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getLong(VALUE_FILTER)));
											}
											
										} else if (CHECK_BOX.equals(filter.getString(TYPE))) {
											if (TYPE_INT.equals(filter.getString(DATA_TYPE))) {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getBoolean(VALUE_FILTER) ? 1 : 0));
											} else {
												dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
														.eq(filter.getBoolean(VALUE_FILTER)));
											}
										} else {
											dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
													.eq(filter.getString(VALUE_FILTER)));
										}
									} else if (QUERY_LIKE.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(
												PropertyFactoryUtil.forName(filter.getString(KEY)).like(StringPool.PERCENT
														+ filter.getString(VALUE_FILTER) + StringPool.PERCENT));
									} else if (COMPARE_LT.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.lt(filter.getLong(VALUE_FILTER)));
									} else if (COMPARE_LE.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.le(filter.getLong(VALUE_FILTER)));
									} else if (COMPARE_GT.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.gt(filter.getLong(VALUE_FILTER)));
									} else if (COMPARE_GE.equals(filter.getString(COMPARE))) {
										dynamicQuery.add(PropertyFactoryUtil.forName(filter.getString(KEY))
												.ge(filter.getLong(VALUE_FILTER)));
									}
	
								}
	
							}
	
						}
	
						if (groupId > 0 && adminConfig.getGroupFilter()) {
							Disjunction disjunction = RestrictionsFactoryUtil.disjunction();
							if (Validator.isNotNull(code)
									&& (CLASSNAME_WORKING_UNIT.equals(code) || CLASSNAME_APPLICANT.equals(code))) {
								disjunction.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, groupId));
							} else {
								disjunction.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, 0l));
								disjunction.add(RestrictionsFactoryUtil.eq(Field.GROUP_ID, groupId));
							}
							dynamicQuery.add(disjunction);
						}

						if (Validator.isNotNull(message.getString(SORT))
								&& message.getString(SORT).indexOf(StringPool.UNDERLINE) > 0) {
							String sortKey = message.getString(SORT).split(StringPool.UNDERLINE)[0];
							String sortType = message.getString(SORT).split(StringPool.UNDERLINE)[1];
							if (SORT_ASC.equals(sortType)) {
								dynamicQuery.addOrder(OrderFactoryUtil.asc(sortKey));
							} else {
								dynamicQuery.addOrder(OrderFactoryUtil.desc(sortKey));
							}
						}

						method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(DYNAMIC_QUERY,
								DynamicQuery.class, int.class, int.class);
	
						messageData.put(STATUS, HttpStatus.OK);
	
						JSONObject headersObj = JSONFactoryUtil.createJSONObject(adminConfig.getHeadersName());
	
	//					System.out.println("code: " + code.equals("opencps_employee"));
						
						if (message.getBoolean(CONFIG)) {

							JSONObject config = JSONFactoryUtil.createJSONObject();
							config.put(CODE, adminConfig.getCode());
							config.put(NAME, adminConfig.getName());
							config.put(HEARDER_NAME, headersObj.getJSONArray(HEADERS));
							config.put(COLUMNS, adminConfig.getColumns());
							config.put(DETAIL_COLUMN, adminConfig.getDetailColumns());
							config.put(EXT_FORM, adminConfig.getExtForm());
							config.put(DEPENDENCY_TITLE, headersObj.get(DEPENDENCY_TITLE));
							config.put(DEPENDENCY_LINK, headersObj.get(DEPENDENCY_LINK));

							messageData.put(message.getString(RESPONE), config);
	
						} else if (message.getBoolean(COUNTER)) {

							Method methodCounter = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(DYNAMIC_COUNT,
									DynamicQuery.class);
							
							messageData.put(message.getString(RESPONE), methodCounter.invoke(model, dynamicQuery));
	
						} else {

							int start = Validator.isNotNull(message.getString(START)) ? message.getInt(START) : 0;
							int end = Validator.isNotNull(message.getString(END)) ? message.getInt(END) : 1;
							if (CLASSNAME_EMPLOYEE.equals(code)) {
								
								@SuppressWarnings("unchecked")
								List<Object[]> employees = (List<Object[]>) method.invoke(model, dynamicQuery, start, end);
	
								_log.debug("employees: " + employees);
								if (lengColumns > 0) {
									for (Object[] obj: employees) {
	
										_log.debug("obj[lengColumns - 1]: " + obj[lengColumns - 1]);
										if (Validator.isNotNull(obj[lengColumns - 1])) {
											long userIdMapping = (long) obj[lengColumns - 1];
											User user = UserLocalServiceUtil.fetchUser(userIdMapping);
											if (user != null && user.getLoginDate() != null) {
												obj[lengColumns - 1] = convertDateToString(user.getLoginDate());												
											}
										}
										
									}
								}
	//							System.out.println("employees.employees()" + employees);
								messageData.put(message.getString(RESPONE), employees);
								
							} else {
								messageData.put(message.getString(RESPONE), method.invoke(model, dynamicQuery, start, end));
							}
							
						}
	
						messageData.put(TITLE, headersObj.getString(TITLE));
	
					} else {
	
						if (message.getString(CMD).equals(DELETE)) {
	
							method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(PROCESS_DELETE,
									Long.class);
	
							messageData.put(message.getString(RESPONE), method.invoke(model, message.getLong(ID)));
							messageData.put(STATUS, HttpStatus.OK);
	
						} else {

							method = bundleLoader.getClassLoader().loadClass(serviceUtilStr).getMethod(PROCESS_DATA,
									JSONObject.class);
	
							JSONObject postData = message.getJSONObject(DATA);
							JSONObject messageError = JSONFactoryUtil.createJSONObject();

							if("0".equals(id)) {
								if (Validator.isNotNull(code) && (CLASSNAME_DELIVERABLE_TYPE.equals(code))) {
									DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.fetchByG_DLT(postData.getLong(Field.GROUP_ID), postData.getString(TYPE_CODE));
									if (Validator.isNotNull(deliverableType)) {
										messageError.put(STATUS, NOK);
										return Response.status(HttpURLConnection.HTTP_OK).entity(messageError.toJSONString()).build();
									}
								}
							}
//							postData.put(Field.GROUP_ID, groupId);
							if(FILE_ITEM.equals(code)){
								postData.put(Field.GROUP_ID, 0L);
							}else{
								postData.put(Field.GROUP_ID, groupId);
							}
							postData.put(COMPANY_ID, company.getCompanyId());
							postData.put(Field.USER_ID, u.getUserId());
							postData.put(Field.USER_NAME, u.getFullName());

							messageData.put(message.getString(RESPONE), method.invoke(model, message.getJSONObject(DATA)));
							messageData.put(STATUS, HttpStatus.OK);
	
						}
	
					}
				} else if (message.getString(TYPE).equals(API)) {

//					RestTemplate restTemplate = new RestTemplate();
//
//					restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//
//					org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
//
//					headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//
//					JSONObject headerObject = message.getJSONObject("headers");
//
//					JSONArray keys = headerObject.names();
//
//					for (int i = 0; i < keys.length(); ++i) {
//
//						String key = keys.getString(i);
//						String value = headerObject.getString(key);
//
//						headers.set(key, value);
//
//					}
//					headers.set("localaccess", headerObject.getString("Token"));
//					headers.set("userid", headerObject.getString("USER_ID"));
//					
//					HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//					
//					HttpEntity<String> response = restTemplate.exchange(portalURL + message.getString("api"),
//							HttpMethod.GET, entity, String.class);
//
//					String resultString = response.getBody();
//
//					JSONArray responeData = JSONFactoryUtil.createJSONArray();
//					try {
//						responeData = JSONFactoryUtil.createJSONObject(resultString).getJSONArray("data");
//					} catch (Exception e) {
//						_log.debug(e);
//						responeData = JSONFactoryUtil.createJSONArray(resultString);
//					}
//					messageData.put(message.getString(RESPONE), responeData);
//
//					messageData.put(STATUS, HttpStatus.OK);
					
//				    String apiUrl = StringPool.BLANK;
				    String apiUrl;
				    
				    StringBuilder sb = new StringBuilder();
				    try
				    {
				        URL urlVal = null;
						apiUrl = OpenCPSConfigUtil.getAdminProxyUrl() + message.getString(API);
						urlVal = new URL(apiUrl);
						
						JSONObject headerObject = message.getJSONObject(HEADERS);
						HttpURLConnection conn = (HttpURLConnection) urlVal.openConnection();

						JSONArray keys = headerObject.names();

						for (int i = 0; i < keys.length(); ++i) {

							String key = keys.getString(i);
							String value = headerObject.getString(key);

							conn.setRequestProperty(key, value);

						}
				        
						conn.setRequestProperty(LOCAL_ACCESSS, headerObject.getString(P_AUTH));
						conn.setRequestProperty(USER_REQUEST_ID, headerObject.getString(USER_ID));

				        conn.setRequestMethod(message.getString(CMD).toUpperCase());
						conn.setRequestProperty(ACCEPT, ConstantUtils.CONTENT_TYPE_JSON);
						conn.setRequestProperty(HttpHeaders.CONTENT_TYPE,
								ConstantUtils.CONTENT_TYPE_JSON);

				        conn.setDoInput(true);
						conn.setDoOutput(true);
						conn.setConnectTimeout(OpenCPSConfigUtil.getRestConnectionTimeout());
						conn.setReadTimeout(OpenCPSConfigUtil.getRestReadTimeout());
						
				        BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				        			        
				        int cp;
				        while ((cp = brf.read()) != -1) {
				          sb.append((char) cp);
				        }
						JSONArray responeData = JSONFactoryUtil.createJSONArray();
						try {
							responeData = JSONFactoryUtil.createJSONObject(sb.toString()).getJSONArray(ConstantUtils.DATA);
						} catch (Exception e) {
							_log.debug(e);
							responeData = JSONFactoryUtil.createJSONArray(sb.toString());
						}
						messageData.put(message.getString(RESPONE), responeData);
						messageData.put(STATUS, HttpStatus.OK);
						
						conn.disconnect();
				    }
				    catch(IOException e)
				    {
				        _log.debug("Something went wrong while reading/writing in stream!!");
				        _log.debug(e);
				    }
					
				}
	
				messageData.put(RESPONE, message.getString(RESPONE));
				messageData.put(CMD, message.getString(CMD));
	
			} catch (Exception e) {
				_log.debug(e);
				messageData.put(STATUS, HttpStatus.INTERNAL_SERVER_ERROR);
				messageData.put(RESPONE, message.getString(RESPONE));
				messageData.put(CMD, message.getString(CMD));
			}
	
			messageData.put(TYPE, message.getString(TYPE));
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(messageData.toJSONString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	private static final String TABLE_CONFIG = "tableConfig";
	private static final String TABLE_DATA = "tableData";
	private static final String STT = "STT";
	@Override
	public Response exportDataConfig(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
									 User user, ServiceContext serviceContext, String columnName, String content) {
		HSSFWorkbook workbook = null;
		try {

			JSONObject headerInfo = JSONFactoryUtil.createJSONObject(columnName);
			JSONObject headerData = headerInfo.getJSONObject(TABLE_CONFIG);
			// Create Workbook
			workbook = new HSSFWorkbook();

			// Create sheet
			String sheetName = headerData.getString(CODE);
			HSSFSheet mainSheet = workbook.createSheet(sheetName);
			int rowIndex = 0;
			int stt = 0;

			// Write header
			JSONArray headersName = headerData.getJSONArray(HEARDER_NAME);
			writeHeader(mainSheet, rowIndex, headersName);

			// Write data
			rowIndex++;
			stt++;
			Response response = onMessage(request, header, company, locale, user, serviceContext, content);
			JSONObject contentInfo = JSONFactoryUtil.createJSONObject(response.getEntity().toString());
			JSONArray contentData = contentInfo.getJSONArray(TABLE_DATA);
			for (int i = 0; i < contentData.length(); i++) {
				// Create row
				Row row = mainSheet.createRow(rowIndex);
				// Write data on row
				JSONArray objectDataRow = contentData.getJSONArray(i);
				writeData(mainSheet, objectDataRow, row, headersName, stt);
				rowIndex++;
				stt++;
			}

			// Auto resize column witdth
			int numberOfColumn = mainSheet.getRow(0).getPhysicalNumberOfCells();
			autosizeColumn(mainSheet, numberOfColumn);

			// Create file excel

			String fileName = headerData.getString(NAME) + StringPool.UNDERLINE
					+ String.format("%d.xls", System.currentTimeMillis());
			_log.info("fileName: "+fileName);

			File exportDir = new File(StatisticManagementConstants.FOLDER_EXPORTED);
			if (!exportDir.exists()) {
				exportDir.mkdirs();
			}

			File file = new File(exportDir+ StringPool.SLASH + fileName);

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			workbook.write(bos);
			byte[] input = bos.toByteArray();
			try {
				FileOutputStream out = new FileOutputStream(file);
				out.write(input);
				out.flush();
				out.close();
				workbook.close();
			}
			catch (Exception e) {
				_log.debug(e);
			}

			Response.ResponseBuilder responseBuilder = Response.ok((Object) file);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), file.getName());
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION,attachmentFilename);
			responseBuilder.header(HttpHeaders.CONTENT_TYPE, ConstantUtils.MEDIA_TYPE_EXCEL);

			return responseBuilder.build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}  finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}
	}

	// Create Column Name
	private static void writeHeader(HSSFSheet sheet, int rowIndex, JSONArray headerArr ) {
		// create CellStyle
		CellStyle cellStyle = createStyleForHeader(sheet);

		// Create row
		Row row = sheet.createRow(rowIndex);

		// Create first cell - STT
		Cell firstCell = row.createCell(0);
		firstCell.setCellStyle(cellStyle);
		firstCell.setCellValue(STT);

		// Create cell
		for(int i = 1; i< headerArr.length(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headerArr.getString(i));
		}
	}

	// Create Content Data
	private static void writeData(HSSFSheet sheet, JSONArray objectDataRow, Row row, JSONArray headerName, int stt) {
		// create CellStyle
		CellStyle cellStyle = createStyleForContent(sheet);
		Cell firstCell = row.createCell(0);
		firstCell.setCellStyle(cellStyle);
		firstCell.setCellValue(stt);
		for (int i = 1; i < headerName.length(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(objectDataRow.get(i).toString());
		}
	}
	// Auto resize column width
	private static void autosizeColumn(HSSFSheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	// Create CellStyle for header
	private static CellStyle createStyleForHeader(HSSFSheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 14); // font size
		font.setColor(IndexedColors.BLACK.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	// Create CellStyle for content
	private static CellStyle createStyleForContent(HSSFSheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(false);
		font.setFontHeightInPoints((short) 11); // font size
		font.setColor(IndexedColors.BLACK.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		return cellStyle;
	}

}
