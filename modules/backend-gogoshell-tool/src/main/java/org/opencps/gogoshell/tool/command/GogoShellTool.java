package org.opencps.gogoshell.tool.command;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.UserLocalService;

import java.util.List;

import org.apache.felix.service.command.Descriptor;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.DossierLocalService;
import org.opencps.dossiermgt.service.ProcessOptionLocalService;
import org.opencps.dossiermgt.service.ServiceConfigLocalService;
import org.opencps.dossiermgt.service.ServiceInfoLocalService;
import org.opencps.usermgt.model.Employee;
import org.opencps.usermgt.service.EmployeeLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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
			
			default:
				System.out.println("Thao tác không hỗ trợ!!");
				break;
		}		
	}
	
	@Descriptor("Hỗ trợ thủ tục hành chính, dịch vụ công")
	public void service(@Descriptor("Các thao tác hỗ trợ validate") String command, String ... params) {
		switch (command) {
		case "validate":
			long groupId = Long.valueOf(params[0]);
			System.out.println("Đang chờ kiểm tra...");
			serviceValidate(groupId);
			System.out.println("Kiểm tra đã xong.");
			break;
		default:
			System.out.println("Thao tác không hỗ trợ!!");
			break;	
		}
	}
	
	private void serviceValidate(long groupId) {
		List<ServiceInfo> lstSIs = _serviceInfoLocalService.findByGroup(groupId);
		for (ServiceInfo serviceInfo : lstSIs) {
			List<ServiceConfig> lstScs = _serviceConfigLocalService.getByServiceInfo(groupId, serviceInfo.getServiceInfoId());
			
			for (ServiceConfig sc : lstScs) {
				try {
					int countOption = _processOptionLocalService.countByServiceConfigId(sc.getServiceConfigId());
					if (countOption == 0) {
						System.out.println("|" + serviceInfo.getServiceCode() + "\t|" + serviceInfo.getAdministrationCode() + "\t|" + "Chưa gán quy trình");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
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
}