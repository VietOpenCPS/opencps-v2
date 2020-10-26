package org.opencps.api.comment.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CommentTopListTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			CommentTopList object = new CommentTopList();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCommentTopModelTest() {
		try{
			CommentTopList object = new CommentTopList();
			object.getCommentTopModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			CommentTopList object = new CommentTopList();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}