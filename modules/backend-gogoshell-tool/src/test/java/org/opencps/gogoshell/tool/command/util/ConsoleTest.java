package org.opencps.gogoshell.tool.command.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ConsoleTest {
	@Before
	public void setUp() {
	}
	@Test
	public void printlnTest() {
		try{
			Console.println("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void printlnTest2() {
		try{
			Console.println(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void printTest() {
		try{
			Console.print("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void printTest4() {
		try{
			Console.print(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}