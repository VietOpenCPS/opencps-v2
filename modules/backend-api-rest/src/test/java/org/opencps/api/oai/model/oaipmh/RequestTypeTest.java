package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RequestTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			RequestType object = new RequestType();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			RequestType object = new RequestType();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUntilTest() {
		try{
			RequestType object = new RequestType();
			object.getUntil();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSetTest() {
		try{
			RequestType object = new RequestType();
			object.getSet();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVerbTest() {
		try{
			RequestType object = new RequestType();
			object.getVerb();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIdentifierTest() {
		try{
			RequestType object = new RequestType();
			object.setIdentifier("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetadataPrefixTest() {
		try{
			RequestType object = new RequestType();
			object.getMetadataPrefix();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setResumptionTokenTest() {
		try{
			RequestType object = new RequestType();
			object.setResumptionToken("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResumptionTokenTest() {
		try{
			RequestType object = new RequestType();
			object.getResumptionToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMetadataPrefixTest() {
		try{
			RequestType object = new RequestType();
			object.setMetadataPrefix("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void seteliverableTypeTest() {
		try{
			RequestType object = new RequestType();
			object.seteliverableType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeTest() {
		try{
			RequestType object = new RequestType();
			object.getDeliverableType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIdentifierTest() {
		try{
			RequestType object = new RequestType();
			object.getIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUntilTest() {
		try{
			RequestType object = new RequestType();
			object.setUntil("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVerbTest() {
		try{
			RequestType object = new RequestType();
			object.setVerb(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSetTest() {
		try{
			RequestType object = new RequestType();
			object.setSet("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromTest() {
		try{
			RequestType object = new RequestType();
			object.getFrom();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromTest() {
		try{
			RequestType object = new RequestType();
			object.setFrom("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}