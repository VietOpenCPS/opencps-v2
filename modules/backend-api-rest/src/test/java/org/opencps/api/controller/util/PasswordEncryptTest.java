package org.opencps.api.controller.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PasswordEncryptTest {
    @Before
    public void setUp() {
    }

    @Test
    public void asHexStrTest() throws Exception {
        String message = "This is just an example";
        String encryptedMessage = PasswordEncrypt.encrypt(message);
        Assert.assertEquals(encryptedMessage, "ec9634d2f7ba601f31e9b3d8c3ac03299628044355fb6e023d2c88917813fe4b");
    }

}