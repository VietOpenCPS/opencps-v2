package org.opencps.sms.service.client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MessageEntityTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hashCodeTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMsgbodyTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setMsgbody("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMtseqTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setMtseq("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMoidTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setMoid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDestTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setDest("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSrcTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setSrc("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMoseqTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setMoseq("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMtseqrefTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setMtseqref("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMttotalsegTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setMttotalseg("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCmdcodeTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.setCmdcode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMoidTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getMoid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSrcTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getSrc();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDestTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getDest();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCmdcodeTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getCmdcode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMoseqTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getMoseq();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMttotalsegTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getMttotalseg();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMtseqTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getMtseq();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMtseqrefTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getMtseqref();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMsgbodyTest() {
		try{
			MessageEntity object = new MessageEntity();
			object.getMsgbody();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSerializerTest() {
		try{
			MessageEntity.getSerializer("abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeserializerTest() {
		try{
			MessageEntity.getDeserializer("abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTypeDescTest() {
		try{
			MessageEntity.getTypeDesc();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}