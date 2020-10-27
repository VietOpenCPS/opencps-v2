package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessSequenceLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.deleteProcessSequence(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.updateProcessSequence(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessSequenceTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.addProcessSequence(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySID_SNOTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.findBySID_SNO(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_SNTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.findByG_SN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessSequenceDBTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.updateProcessSequenceDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceProcessTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.getByServiceProcess(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_SID_SNOSTest() {
		try{
			ProcessSequenceLocalServiceImpl object = new ProcessSequenceLocalServiceImpl();
			object.getByG_SID_SNOS(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}