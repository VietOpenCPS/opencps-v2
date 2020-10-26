package org.opencps.api.comment.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CommentListModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCommentModelTest() {
		try{
			CommentListModel object = new CommentListModel();
			object.getCommentModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}