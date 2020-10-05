package org.opencps.dossiermgt.action.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccentUtilsTest {
    @Before
    public void setUp() {
    }

    @Test
    public void asHexStrTest() throws Exception {
        String message = "Trần Văn A";
        String removeAccentMessage = AccentUtils.removeAccent(message);
        Assert.assertEquals(removeAccentMessage, "Tran Van A");
    }

}