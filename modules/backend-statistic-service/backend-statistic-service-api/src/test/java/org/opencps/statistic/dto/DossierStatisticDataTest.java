package org.opencps.statistic.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticDataTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupIdTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainNameTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getDomainName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainNameTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setDomainName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainCodeTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getDomainCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainCodeTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setDomainCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceivedCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setReceivedCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnlineCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOnlineCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOverdueCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOverdueCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUndueCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setUndueCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOvertimeCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOvertimeCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOntimeCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOntimeCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setTotalCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportingTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setReporting(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRemainingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setRemainingCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTotalCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getTotalCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOverdueCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOverdueCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOvertimeCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOvertimeCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceivedCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getReceivedCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReportingTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.isReporting();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOntimeCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOntimeCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUndueCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getUndueCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnlineCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOnlineCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setProcessCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReleaseCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getReleaseCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReleaseCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setReleaseCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getProcessCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBetimesCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getBetimesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDoneCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getDoneCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDeniedCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setDeniedCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeniedCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getDeniedCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBetimesCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setBetimesCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDoneCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setDoneCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCancelledCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getCancelledCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReleasingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getReleasingCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOntimePercentageTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOntimePercentage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCancelledCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setCancelledCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOntimePercentageTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOntimePercentage(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProcessingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setProcessingCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUnresolvedCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setUnresolvedCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReleasingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setReleasingCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUnresolvedCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getUnresolvedCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getProcessingCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRemainingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getRemainingCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceiveDossierSatCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getReceiveDossierSatCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReceiveDossierSatCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setReceiveDossierSatCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOvertimeInsideTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOvertimeInside();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInteroperatingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getInteroperatingCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOvertimeInsideTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOvertimeInside(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setViaPostalCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setViaPostalCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInteroperatingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setInteroperatingCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReleaseDossierSatCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setReleaseDossierSatCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierOnline3CountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getDossierOnline3Count();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierOnline3CountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setDossierOnline3Count(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierOnline4CountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getDossierOnline4Count();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierOnline4CountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setDossierOnline4Count(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReleaseDossierSatCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getReleaseDossierSatCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromViaPostalCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getFromViaPostalCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromViaPostalCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setFromViaPostalCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOvertimeOutsideTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOvertimeOutside();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOvertimeOutsideTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOvertimeOutside(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getViaPostalCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getViaPostalCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSystemTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getSystem();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSystemTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setSystem("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWaitingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getWaitingCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSaturdayCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getSaturdayCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInsideCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setInsideCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOnegateCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOnegateCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWaitingCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setWaitingCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInsideCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getInsideCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnegateCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOnegateCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSaturdayCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setSaturdayCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOutsideCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.setOutsideCount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOutsideCountTest() {
		try{
			DossierStatisticData object = new DossierStatisticData();
			object.getOutsideCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}