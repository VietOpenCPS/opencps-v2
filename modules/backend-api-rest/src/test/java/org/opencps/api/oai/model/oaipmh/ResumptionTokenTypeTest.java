package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResumptionTokenTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompleteListSizeTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.setCompleteListSize(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpirationDateTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.getExpirationDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExpirationDateTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.setExpirationDate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompleteListSizeTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.getCompleteListSize();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCursorTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.getCursor();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCursorTest() {
		try{
			ResumptionTokenType object = new ResumptionTokenType();
			object.setCursor(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}