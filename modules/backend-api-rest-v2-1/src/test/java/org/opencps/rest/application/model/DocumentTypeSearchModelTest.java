package org.opencps.rest.application.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentTypeSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void startTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.start(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sortTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.sort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void orderTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.order("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void endTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.end(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void keywordTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.keyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOrderTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.getOrder();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSortTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.getSort();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getKeywordTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.getKeyword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setKeywordTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.setKeyword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSortTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.setSort("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOrderTest() {
		try{
			DocumentTypeSearchModel object = new DocumentTypeSearchModel();
			object.setOrder("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}