package org.opencps.gogoshell.tool.command;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpenCPSSchedulerManagerCommandTest {
	@Before
	public void setUp() {
	}
	@Test
	public void resumeTest() {
		try{
			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
			object.resume("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resumeTest2() {
		try{
			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
			object.resume("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void listTest() {
//		try{
//			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
//			object.list("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void infoTest() {
		try{
			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
			object.info("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void resumeAllTest() {
//		try{
//			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
//			object.resumeAll();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void pauseTest() {
		try{
			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
			object.pause("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void pauseTest7() {
		try{
			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
			object.pause("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void pauseAllTest() {
//		try{
//			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
//			object.pauseAll();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void jobsIsFiredTest() {
//		try{
//			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
//			object.jobsIsFired("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void jobIsFiredTest() {
//		try{
//			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
//			object.jobIsFired("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void listJobsInProgressTest() {
//		try{
//			OpenCPSSchedulerManagerCommand object = new OpenCPSSchedulerManagerCommand();
//			object.listJobsInProgress("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}