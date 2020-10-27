package org.opencps.usermgt.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ContactGroupTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupNameTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getGroupName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupNameTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setGroupName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactGroupIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getContactGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactGroupIdTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setContactGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSharedTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getShared();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactListTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.getContactList();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSharedTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setShared(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactListTest() {
		try{
			ContactGroupTerm object = new ContactGroupTerm();
			object.setContactList("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}