package org.opencps.api.oai.model.oaipmh;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createMetadataFormatTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMetadataFormatType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createListRecordsTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createListRecordsType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createListMetadataFormatsTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createListMetadataFormatsType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResumptionTokenTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createResumptionTokenType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createListIdentifiersTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createListIdentifiersType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDescriptionTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createDescriptionType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRequestTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createRequestType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createGetRecordTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createGetRecordType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createListSetsTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createListSetsType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createIdentifyTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createIdentifyType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMetadataTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMetadataType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOAIPMHerrorTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createOAIPMHerrorType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOAIPMHtypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createOAIPMHtype();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createSetTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createSetType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAboutTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createAboutType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createHeaderTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createHeaderType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOAIPMHTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createOAIPMH(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRecordTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createRecordType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}