package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentTypeLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDocumentTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.createDocumentType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocumentTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getDocumentType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTypeCodeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getByTypeCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDocTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.updateDocType(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDocumentTypeDBTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.updateDocumentTypeDB(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocumentTypesTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getDocumentTypes(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void insertDocTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.insertDocType(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDocumentTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.addDocumentType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDocumentTypeByUuidAndGroupIdTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.fetchDocumentTypeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDocumentTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.updateDocumentType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocumentTypeByUuidAndGroupIdTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getDocumentTypeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocumentTypesCountTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getDocumentTypesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDocumentTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.deleteDocumentType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDocumentTypeTest21() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.deleteDocumentType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDocumentTypeTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.fetchDocumentType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest27() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest29() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest32() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DocumentTypeLocalServiceWrapper object = new DocumentTypeLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}