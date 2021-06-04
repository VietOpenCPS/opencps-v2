package org.opencps.gogoshell.tool.command;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.util.Validator;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.apache.felix.service.command.Descriptor;
import org.opencps.dossiermgt.constants.DossierSyncTerm;
import org.opencps.dossiermgt.model.*;
import org.opencps.dossiermgt.service.*;
import org.opencps.gogoshell.tool.command.util.Console;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import de.vandermeer.asciitable.AsciiTable;

/**
 * @author Admin
 */
@Component(property = {
	    "osgi.command.function=opencps", 
	    "osgi.command.function=listEmployee", 
	    "osgi.command.function=listGroup",
	    "osgi.command.function=employee",
	    "osgi.command.function=dossier",
	    "osgi.command.function=service",
	    "osgi.command.function=sync",
	    "osgi.command.function=cpslog",
	    "osgi.command.function=execsql",
	    "osgi.command.scope=opencps"
	}, service = Object.class)
public class GogoShellTool {
	@Descriptor("Giới thiệu phiên bản OpenCPS")
	public void opencps() {
		System.out.println("Cám ơn bạn đã sử dụng OpenCPS 3.0!!");
	}
	
	@Descriptor("Tìm tất cả các site trong hệ thống")
	public void listGroup() {
		List<Group> groups = _groupLocalService.getGroups(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for (Group g : groups) {
			if (g.isSite()) {
				System.out.println("|" + g.getGroupId() + "\t|" + g.getName() + "\t");				
			}
		}
	}
	@Descriptor("Liệt kê tất cả danh sách cán bộ trong một tổ chức")
	public void listEmployee(@Descriptor("Mã nhóm groupId") long groupId) {
		System.out.println("List employee in group");
	}
	
	@Descriptor("Nhóm các thao tác liên quan đến cán bộ")
	public void employee(
			@Descriptor("Các thao tác hỗ trợ find, active, delete") String command, String ... params) {
		switch (command) {
		case "find":
			findEmployeeByEmail(params[0]);
			break;
		case "active":
			if (params.length == 0) {
				System.out.println("Không đủ tham số");
			}
			else {
				long employeeId = Long.valueOf(params[0]);
				active(employeeId);
			}
			break;
		case "deactive":
			if (params.length == 0) {
				System.out.println("Không đủ tham số");
			}
			else {
				long employeeId = Long.valueOf(params[0]);
				deactive(employeeId);
			}
			break;
		case "delete":
			break;
		default:
			System.out.println("Thao tác không hỗ trợ!!");
			break;
		}
	}
	
	@Descriptor("Nhóm các thao tác liên quan đến hồ sơ")
	public void dossier(
			@Descriptor("Các thao tác hỗ trợ find, reindex") String command, String ... params) {
		switch (command) {
			case "find":
				break;
			case "reindex":
				long dossierId = Long.valueOf(params[0]);
				reindex(dossierId);
				break;
			case "reindex_range":
				long startDossierId = Long.valueOf(params[0]);
				long endDossierId = Long.valueOf(params[1]);
				for(long i = startDossierId; i <= endDossierId; i++) {
					Dossier dossier = DossierLocalServiceUtil.fetchDossier(i);
					if(dossier != null) {
						_log.info("reindex_range: " + i);
						reindex(i);
					}
					
				}
				break;
			case "update_range":
				long startId = Long.valueOf(params[0]);
				long endId = Long.valueOf(params[1]);
//				long groupId = Long.valueOf(params[2]);
				for(long i = startId; i <= endId; i++) {
					Dossier dossier = DossierLocalServiceUtil.fetchDossier(i);
					if(dossier != null) {
						_log.info("update_range: " + i);
						updateDossier(i);
					}

				}
				break;
			default:
				System.out.println("Thao tác không hỗ trợ!!");
				break;
		}		
	}
	
	@Descriptor("Hỗ trợ thủ tục hành chính, dịch vụ công")
	public void service(@Descriptor("Các thao tác hỗ trợ validate") String command, String ... params) {
		switch (command) {
		case "validate":
			if (params.length == 0) {
				List<Group> groups = _groupLocalService.getGroups(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
				for (Group g : groups) {
					if (g.isSite()) {
						System.out.println("-----------------------------------------------------------------");
						serviceValidate(g.getGroupId());			
						System.out.println("-----------------------------------------------------------------");
					}
				}				
			}
			else {
				long groupId = Long.valueOf(params[0]);
				System.out.println("Đang chờ kiểm tra...");
				serviceValidate(groupId);
				System.out.println("Kiểm tra đã xong.");				
			}
			break;
		default:
			System.out.println("Thao tác không hỗ trợ!!");
			break;	
		}
	}
	
	@Descriptor("Công cụ xử lý đồng bộ hồ sơ")
	public void sync(@Descriptor("Các thao tác hỗ trợ list, resync") String command, String ... params) {
		switch (command) {
		case "list":
			long dossierId = Long.valueOf(params[0]);
			listSync(dossierId);
			break;
		case "resync":
			long dossierSyncId = Long.valueOf(params[0]);
			resync(dossierSyncId);
			break;
		default:
			System.out.println("Thao tác không hỗ trợ!!");
			break;	
		}
	}
	
	private void resync(long dossierSyncId) {
		DossierSync ds = _dossierSyncLocalService.fetchDossierSync(dossierSyncId);
		if (ds != null) {
			ds.setState(DossierSyncTerm.STATE_WAITING_SYNC);
			ds.setRetry(0);
			_dossierSyncLocalService.updateDossierSync(ds);
		}
		else {
			System.out.println("Đồng bộ mã ID không tồn tại");
		}
	}
	
	@Descriptor("Công cụ xem log hệ thống")
	public void cpslog(@Descriptor("Các thao tác hỗ trợ list") String command, 
			@Descriptor("Đường dẫn đến tệp log") String path, 
			@Descriptor("Số dòng tính từ cuối tệp cần xem") int line) {
		String current;
		try {
			current = new java.io.File( "." ).getCanonicalPath();
		    System.out.println("Current dir:" + current);
		    if (!"".contentEquals(path)) {
		    	System.out.println(readLastLine(path, line));
		    }
		} catch (IOException e) {
			_log.debug(e);
			e.printStackTrace();
		}
	}
	
	@Descriptor("Công cụ xem chạy lệnh SQL")
	public void execsql(@Descriptor("Lệnh SQL cần chạy") String sql) {
		System.out.println("Lệnh SQL \"" + sql + "\"");
		ResultSet rs = null;
		try (Connection connection = DataAccess.getConnection()) {
			try (PreparedStatement ptmt = connection.prepareStatement(sql)) {
				rs = ptmt.executeQuery();
				if (rs != null && rs.next()) {
					ResultSetMetaData rsmd = rs.getMetaData();
					if (rsmd != null) {
						int columnsNumber = rsmd.getColumnCount();
						System.out.println("Số cột trong kết quả: " + columnsNumber);
						if (columnsNumber > 0) {
							AsciiTable at = new AsciiTable();
							List<String> columnNames = new ArrayList<String>();
							
							for (int i = 1; i <= columnsNumber; i++) {
								columnNames.add(rsmd.getColumnName(i));
							}
							at.addRule();
							at.addRow(columnNames);
							at.addRule();

							Console.println(at.render(160));
							List<String> columnValues = new ArrayList<String>();
							at = new AsciiTable();
							do {
								for (int i = 1; i <= columnsNumber; i++) {
									columnValues.add(rs.getString(i) != null ? rs.getString(i) : StringPool.BLANK);
								}
							}	
							while (rs.next());
							at.addRule();
							at.addRow(columnValues);
							at.addRule();

							Console.println(at.render(160));							
						}
						else {
							StringBuilder columnNames = new StringBuilder();
							columnNames.append("");
							for (int i = 1; i <= columnsNumber; i++) {
								columnNames.append("\t|" + rsmd.getColumnName(i));
							}
							System.out.println(columnNames.toString());
							
							do {
								for (int i = 1; i <= columnsNumber; i++) {
									System.out.print("\t|" + rs.getString(i));
								}
								System.out.println();
							}	
							while (rs.next());
						}
					}
				}
			}
		}
		catch (SQLException e) {
			_log.debug(e);
			e.printStackTrace();
		}
		finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            	_log.debug(e);
            	e.printStackTrace();
            }
        }
	}
	
	private String getLastNLogLines(String path, int nLines) {
		 try (ReversedLinesFileReader object = new ReversedLinesFileReader(new File(path))) {
		    StringBuilder result = new StringBuilder();
		    for(int i = 0; i < nLines; i++){
		        String line = object.readLine();
		        if(line == null)
		            break;
		        result.append(line);
		        result.append("\n");
		    }
		    return result.toString();
		 } catch (IOException e) {
			 _log.debug(e);
			e.printStackTrace();
		}
		 
		 return "";
	}
	
	public static List<String> readLastLine(String path, int numLastLineToRead) {

		List<String> result = new ArrayList<>();

		try (ReversedLinesFileReader reader = new ReversedLinesFileReader(new File(path), StandardCharsets.UTF_8)) {

			String line = "";
			while ((line = reader.readLine()) != null && result.size() < numLastLineToRead) {
				result.add(line);
				result.add("\n");
			}

		} catch (IOException e) {
			_log.debug(e);
			e.printStackTrace();
		}

		return result;

	}

	private void listSync(long dossierId) {
		Dossier dossier = _dossierLocalService.fetchDossier(dossierId);
		if (dossier == null) {
			System.out.println("Mã ID hồ sơ không tồn tại??");
			return;
		}
		List<DossierSync> lstSyncs = _dossierSyncLocalService.findByG_DID(dossier.getGroupId(), dossierId);
		System.out.println("|Mã ID\t|Mã hành động\t|Tên hành động\t|Người thực hiện\t|Server đồng bộ\t|Trạng thái\t|Loại đồng bộ");
		for (DossierSync ds : lstSyncs) {
			System.out.println("|" + ds.getDossierSyncId() + "\t|" + ds.getActionCode() + "\t|" + ds.getActionName() + "\t|" + ds.getActionUser() + "\t|" + ds.getServerNo() + "\t|" + ds.getState() + "\t|" + ds.getSyncType());
		}
	}
	
	private void serviceValidate(long groupId) {
		Group g = _groupLocalService.fetchGroup(groupId);
		
		List<ServiceInfo> lstSIs = _serviceInfoLocalService.findByGroup(groupId);
		boolean check = true;
		for (ServiceInfo serviceInfo : lstSIs) {
			List<ServiceConfig> lstScs = _serviceConfigLocalService.getByServiceInfo(groupId, serviceInfo.getServiceInfoId());
			
			for (ServiceConfig sc : lstScs) {
				try {
					int countOption = _processOptionLocalService.countByServiceConfigId(sc.getServiceConfigId());
					if (countOption == 0) {
						check = false;
						System.out.println("|" + serviceInfo.getServiceCode() + "\t|" + serviceInfo.getAdministrationCode() + "\t|" + serviceInfo.getDomainCode() + "\t|" + serviceInfo.getGovAgencyText() + "\t|" + "Chưa gán quy trình");
					}
				} catch (Exception e) {
					_log.debug(e);
					e.printStackTrace();
				}
				
			}
		}
		if (!check) {
			System.out.println("Cấu hình dịch vụ có lỗi trên đơn vị " + g.getName() + " hãy kiểm tra lại");
		}
		else {
			System.out.println("Cấu hình dịch vụ có vẻ ổn trên đơn vị " + g.getName() + "!");			
		}
	}

	private ServiceConfig getServiceConfig(String serviceInfoCode, String govAgencyCode, String dossierTemplateNo,
										   long groupId) {
		try {
			ServiceConfig config = ServiceConfigLocalServiceUtil.getBySICodeAndGAC(groupId, serviceInfoCode, govAgencyCode);
			_log.debug("++++config:" + config);
			return config;
		}catch (Exception e){
			e.getMessage();
		}
		return null;
	}

	private void updateDossier(long dossierId) {
		Dossier dossier = _dossierLocalService.fetchDossier(dossierId);
		_log.debug("Originality: " + dossier.getOriginality());
		_log.debug("Online: " + dossier.getOnline());
		_log.debug("serviceLevel: " + dossier.getServiceLevel());
		if (dossier != null && dossier.getOriginality() == 2L && dossier.getOnline() &&  dossier.getServiceLevel() == 0L){
			try {
				ServiceConfig config =  getServiceConfig(dossier.getServiceCode(), dossier.getGovAgencyCode(),
						dossier.getDossierTemplateNo(), dossier.getGroupId());
				if (config != null) {
					dossier.setServiceLevel(config.getServiceLevel());
					DossierLocalServiceUtil.updateDossier(dossier);
					System.out.println("Update serviceLevel cho hồ sơ thành công");
				}

			} catch (Exception e) {
				_log.debug(e);
				e.printStackTrace();
			}
		} else {
			System.out.println("Không tìm thấy hồ sơ với ID hồ sơ " + dossierId);
		}
	}
	
	private void reindex(long dossierId) {
		Dossier dossier = _dossierLocalService.fetchDossier(dossierId);
		if (dossier != null) {
			Indexer<Dossier> indexer =
					IndexerRegistryUtil.nullSafeGetIndexer(Dossier.class);
			try {
				indexer.reindex(dossier);
				System.out.println("Đánh chỉ mục hồ sơ thành công");
			} catch (Exception e) {
				_log.debug(e);
				e.printStackTrace();
			}			
		}
		else {
			System.out.println("Không tìm thấy hồ sơ với ID hồ sơ " + dossierId);
		}
	}
	private void active(long employeeId) {
		Employee e = _employeeLocalService.fetchEmployee(employeeId);
		if (e == null) {
			System.out.println("Không tìm thấy cán bộ để kích hoạt tài khoản");
			return;
		}
		User u = _userLocalService.fetchUser(e.getMappingUserId());
		if (u == null) {
			System.out.println("Cán bộ chưa mở tài khoản trên hệ thống");
			return;
		}
		try {
			_userLocalService.updateStatus(u.getUserId(), 0);
		} catch (Exception ex) {
			_log.debug(ex);
			System.out.println("Lỗi không thể kích hoạt tài khoản cán bộ");
		}
		
	}

	private void deactive(long employeeId) {
		Employee e = _employeeLocalService.fetchEmployee(employeeId);
		if (e == null) {
			System.out.println("Không tìm thấy cán bộ để khóa tài khoản");
			return;
		}
		User u = _userLocalService.fetchUser(e.getMappingUserId());
		if (u == null) {
			System.out.println("Cán bộ chưa mở tài khoản trên hệ thống");
			return;
		}
		try {
			_userLocalService.updateStatus(u.getUserId(), 5);
		} catch (Exception ex) {
			_log.debug(ex);
			System.out.println("Lỗi không thể khóa tài khoản cán bộ");
		}
		
	}
	
	private void findEmployeeByEmail(String email) {
		List<Employee> lstEmps = _employeeLocalService.findByEmail(email);
		System.out.println("Tìm thấy " + lstEmps.size() + " cán bộ");
		for (Employee e : lstEmps) {
			System.out.println("|" + e.getEmployeeId() + "\t|" + e.getEmployeeNo() + "\t|" + e.getFullName() + "\t|" + e.getTelNo() + "\t|" + e.getScope() + "\t|" + (e.getWorkingStatus() == 0 ? "Nghỉ việc" : "Đang làm việc"));							
		}		
	}
	
	@Reference
	private GroupLocalService _groupLocalService;
	
	@Reference
	private EmployeeLocalService _employeeLocalService;
	
	@Reference
	private UserLocalService _userLocalService;
	
	@Reference
	private DossierLocalService _dossierLocalService;
	
	@Reference
	private ServiceInfoLocalService _serviceInfoLocalService;
	
	@Reference
	private ServiceConfigLocalService _serviceConfigLocalService;
	
	@Reference
	private ProcessOptionLocalService _processOptionLocalService;

	@Reference
	private DossierSyncLocalService _dossierSyncLocalService;
	
	private static Log _log = LogFactoryUtil.getLog(GogoShellTool.class);
}