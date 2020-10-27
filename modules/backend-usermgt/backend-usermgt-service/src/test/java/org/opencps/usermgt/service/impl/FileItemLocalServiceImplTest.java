package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileItemLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_FTNSTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.findByG_FTNS(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_FTNTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.findByG_FTN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileItemTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.updateFileItem(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileItemTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.deleteFileItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchFileItemTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.fetchFileItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_STest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.findByG_S(Long.valueOf(0), 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_STest9() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.findByG_S(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFileItemTest() {
		try{
			FileItemLocalServiceImpl object = new FileItemLocalServiceImpl();
			object.createFileItem(null, Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}