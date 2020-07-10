package backend.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;


public class APIDateTimeUtilsTest {
    @Before
    public void setUp() {
    }

    @Test
    public void timeZone2LuceneTest() {
        String outputDateString = APIDateTimeUtils.timeZone2Lucene("20180822142266");
        Assert.assertEquals(outputDateString, "26090704075542");
    }
}
