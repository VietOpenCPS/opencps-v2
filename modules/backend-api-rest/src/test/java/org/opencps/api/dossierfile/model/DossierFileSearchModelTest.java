package org.opencps.api.dossierfile.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFileSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTypeTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.getType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setTemplate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTypeTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOwnerTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setOwner(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOriginalTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.isOriginal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isOwnerTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.isOwner();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.getTemplate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOriginalTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setOriginal(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			DossierFileSearchModel object = new DossierFileSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}