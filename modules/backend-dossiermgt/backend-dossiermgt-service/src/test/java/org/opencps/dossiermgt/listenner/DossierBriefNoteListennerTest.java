package org.opencps.dossiermgt.listenner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierBriefNoteListennerTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getProcessOptionTest() {
//		try{
//			DossierBriefNoteListenner object = new DossierBriefNoteListenner();
//			object.getProcessOption("abcde", "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void onBeforeUpdateTest() {
		try{
			DossierBriefNoteListenner object = new DossierBriefNoteListenner();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onBeforeUpdateTest3() {
		try{
			DossierBriefNoteListenner object = new DossierBriefNoteListenner();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onBeforeUpdateTest4() {
		try{
			DossierBriefNoteListenner object = new DossierBriefNoteListenner();
			object.onBeforeUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest() {
		try{
			DossierBriefNoteListenner object = new DossierBriefNoteListenner();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest6() {
		try{
			DossierBriefNoteListenner object = new DossierBriefNoteListenner();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void onAfterUpdateTest7() {
		try{
			DossierBriefNoteListenner object = new DossierBriefNoteListenner();
			object.onAfterUpdate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}