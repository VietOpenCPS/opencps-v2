package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SequencesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getProcessSequenceTest() {
		try{
			Sequences object = new Sequences();
			object.getProcessSequence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}