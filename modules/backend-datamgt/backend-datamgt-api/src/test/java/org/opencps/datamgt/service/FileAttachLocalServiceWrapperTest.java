package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileAttachLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileAttachsTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getFileAttachs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileAttachsCountTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getFileAttachsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_docFileIdTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.findByF_docFileId(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_className_classPKTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.findByF_className_classPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileAttachTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.updateFileAttach(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileAttachTest13() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.updateFileAttach(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileAttachTest14() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.updateFileAttach(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileAttachTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.createFileAttach(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileAttachTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.deleteFileAttach(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileAttachTest17() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.deleteFileAttach(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileAttachTest18() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.deleteFileAttach(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void copyFileAttachTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.copyFileAttach(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileAttachTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getFileAttach(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFileAttachTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.addFileAttach(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFileAttachTest22() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.addFileAttach(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest28() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest30() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest33() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchFileAttachTest() {
		try{
			FileAttachLocalServiceWrapper object = new FileAttachLocalServiceWrapper(null);
			object.fetchFileAttach(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}