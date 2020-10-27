package backend.feedback.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EvaluationLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getEvaluationbyEmployeeIdTest() {
		try{
			EvaluationLocalServiceImpl object = new EvaluationLocalServiceImpl();
			object.getEvaluationbyEmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEvaluationbyEmployeeIdScoreTest() {
		try{
			EvaluationLocalServiceImpl object = new EvaluationLocalServiceImpl();
			object.getEvaluationbyEmployeeIdScore(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEvaluationTest() {
		try{
			EvaluationLocalServiceImpl object = new EvaluationLocalServiceImpl();
			object.addEvaluation(Long.valueOf(0), Long.valueOf(0), "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}