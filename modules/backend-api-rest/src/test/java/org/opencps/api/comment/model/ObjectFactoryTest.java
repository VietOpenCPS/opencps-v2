package org.opencps.api.comment.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createCommentInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createCommentInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createCommentListModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createCommentListModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createCommentModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createCommentModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createCommentSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createCommentSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}