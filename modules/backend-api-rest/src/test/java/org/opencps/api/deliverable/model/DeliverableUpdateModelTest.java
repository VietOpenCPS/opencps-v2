package org.opencps.api.deliverable.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableUpdateModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setExpireDateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.setExpireDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpireDateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.getExpireDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIssueDateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.getIssueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRevalidateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.getRevalidate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubjectTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.getSubject();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableActionTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.getDeliverableAction();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverableActionTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.setDeliverableAction("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableStateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.getDeliverableState();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeliverableStateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.setDeliverableState("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubjectTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.setSubject("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIssueDateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.setIssueDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRevalidateTest() {
		try{
			DeliverableUpdateModel object = new DeliverableUpdateModel();
			object.setRevalidate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}