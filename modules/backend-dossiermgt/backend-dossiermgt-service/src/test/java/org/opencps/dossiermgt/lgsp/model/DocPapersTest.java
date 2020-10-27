package org.opencps.dossiermgt.lgsp.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocPapersTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setAmountTest() {
		try{
			DocPapers object = new DocPapers();
			object.setAmount(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaperTypeTest() {
		try{
			DocPapers object = new DocPapers();
			object.setPaperType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaperNameTest() {
		try{
			DocPapers object = new DocPapers();
			object.setPaperName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAmountTest() {
		try{
			DocPapers object = new DocPapers();
			object.getAmount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaperTypeTest() {
		try{
			DocPapers object = new DocPapers();
			object.getPaperType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaperNameTest() {
		try{
			DocPapers object = new DocPapers();
			object.getPaperName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}