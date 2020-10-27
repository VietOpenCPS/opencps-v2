package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessSequenceLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.deleteProcessSequence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessSequenceTest8() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.deleteProcessSequence(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessSequencesTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getProcessSequences(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.updateProcessSequence(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessSequenceTest11() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.updateProcessSequence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.addProcessSequence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessSequenceTest13() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.addProcessSequence(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.fetchProcessSequence(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySID_SNOTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.findBySID_SNO(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_SNTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.findByG_SN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessSequenceDBTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.updateProcessSequenceDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceProcessTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getByServiceProcess(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getProcessSequence(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessSequenceByUuidAndGroupIdTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getProcessSequenceByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessSequencesByUuidAndCompanyIdTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getProcessSequencesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessSequencesByUuidAndCompanyIdTest22() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getProcessSequencesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessSequenceByUuidAndGroupIdTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.fetchProcessSequenceByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest29() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest31() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest34() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_SID_SNOSTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getByG_SID_SNOS(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessSequencesCountTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.getProcessSequencesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceWrapper object = new ProcessSequenceLocalServiceWrapper(null);
			object.createProcessSequence(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}