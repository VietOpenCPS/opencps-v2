package org.opencps.gogoshell.tool.command;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.GroupLocalService;

import java.util.List;

import org.apache.felix.service.command.Descriptor;
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
	    "osgi.command.scope=opencps"
	}, service = Object.class)
public class GogoShellTool {
	@Descriptor("Show the OpenCPS version")
	public void opencps() {
		System.out.println("Thank you for using OpenCPS 3.0!!");
	}
	
	@Descriptor("List all group that has site")
	public void listGroup() {
		List<Group> groups = _groupLocalService.getGroups(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		for (Group g : groups) {
			if (g.isSite()) {
				System.out.println("|" + g.getGroupId() + "\t|" + g.getName() + "\t");				
			}
		}
	}
	@Descriptor("List all employee of organization")
	public void listEmployee(long groupId) {
		System.out.println("List employee in group");
	}
	
	@Descriptor("Command process employee in site")
	public void employee(
			@Descriptor("find, active, delete command") String command, String email) {
		List<Employee> lstEmps = _employeeLocalService.findByEmail(email);
		System.out.println("Found " + lstEmps.size() + " employees");
		for (Employee e : lstEmps) {
			System.out.println("|" + e.getEmployeeNo() + "\t|" + e.getFullName() + "\t|" + e.getTelNo() + "\t|" + e.getScope() + "\t|" + (e.getWorkingStatus() == 0 ? "Nghỉ việc" : "Đang làm việc"));							
		}
	}
	@Reference
	private GroupLocalService _groupLocalService;
	
	@Reference
	private EmployeeLocalService _employeeLocalService;
}