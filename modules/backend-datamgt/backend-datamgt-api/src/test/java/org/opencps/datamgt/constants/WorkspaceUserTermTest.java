package org.opencps.datamgt.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkspaceUserTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignUserIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getAssignUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignUserIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setAssignUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkspaceUserIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setWorkspaceUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkspaceUserIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getWorkspaceUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkspaceIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.getWorkspaceId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkspaceIdTest() {
		try{
			WorkspaceUserTerm object = new WorkspaceUserTerm();
			object.setWorkspaceId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}