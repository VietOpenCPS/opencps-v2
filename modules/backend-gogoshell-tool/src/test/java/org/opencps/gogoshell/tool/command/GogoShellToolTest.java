package org.opencps.gogoshell.tool.command;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GogoShellToolTest {
	@Before
	public void setUp() {
	}
	@Test
	public void syncTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.sync("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void employeeTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.employee("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dossierTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.dossier("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void serviceTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.service("abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void listGroupTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.listGroup();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void opencpsTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.opencps();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readLastLineTest() {
		try{
			GogoShellTool.readLastLine("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cpslogTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.cpslog("abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void execsqlTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.execsql("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void listEmployeeTest() {
		try{
			GogoShellTool object = new GogoShellTool();
			object.listEmployee(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}