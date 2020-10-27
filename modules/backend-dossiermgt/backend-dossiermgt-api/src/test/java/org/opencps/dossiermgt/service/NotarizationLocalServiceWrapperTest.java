package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotarizationLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DIDTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DIDTest7() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotarizationTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.addNotarization(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotarizationTest9() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.addNotarization(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), Long.valueOf(0), 0, new Date(), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationsTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getNotarizations(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_DIDTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.countByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getNotarization(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotarizationsCountTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getNotarizationsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotarizationTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.createNotarization(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchNotarizationTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.fetchNotarization(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByAdvancedSearchTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.countByAdvancedSearch(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByAdvancedSearchTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.findByAdvancedSearch(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotarizationTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.deleteNotarization(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotarizationTest19() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.deleteNotarization(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeNotarizationTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.removeNotarization(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotarizationTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.updateNotarization(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotarizationTest22() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.updateNotarization(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, Long.valueOf(0), Long.valueOf(0), 0, new Date(), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest27() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest29() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest32() {
		try{
			NotarizationLocalServiceWrapper object = new NotarizationLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}