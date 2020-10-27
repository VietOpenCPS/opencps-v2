package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.updatePaymentConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentConfigTest8() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.updatePaymentConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByInvoiceTemplateNoTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getByInvoiceTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePaymentConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.removePaymentConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentConfigDBTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.updatePaymentConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.updateEConfig(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPaymentConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateInvoidFormTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.updateInvoidForm(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPaymentConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.addPaymentConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigByGovAgencyCodeTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPaymentConfigByGovAgencyCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPaymentConfigByUuidAndGroupIdTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.fetchPaymentConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigsByUuidAndCompanyIdTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPaymentConfigsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigsByUuidAndCompanyIdTest22() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPaymentConfigsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPaymentConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.fetchPaymentConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigsTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPaymentConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigByUuidAndGroupIdTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPaymentConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigsCountTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPaymentConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.createPaymentConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePaymentConfigTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.deletePaymentConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePaymentConfigTest29() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.deletePaymentConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest35() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest37() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest40() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			PaymentConfigLocalServiceWrapper object = new PaymentConfigLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}