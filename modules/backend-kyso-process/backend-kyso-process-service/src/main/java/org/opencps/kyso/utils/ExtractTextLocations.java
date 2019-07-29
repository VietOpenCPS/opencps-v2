/**
* OpenCPS is the open source Core Public Services software
* Copyright (C) 2016-present OpenCPS community

* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* any later version.

* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>
*/

package org.opencps.kyso.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.common.PDStream;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.TextPosition;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

/**
 * @author trungnt
 */

public class ExtractTextLocations extends PDFTextStripper {

	private float anchorX = 0;
	private float anchorY = 0;
	private float signatureWidth = 0;
	private float signatureHeight = 0;
	private float pageWidth = 0;
	private float pageHeight = 0;
	private float pageLLX = 0;
	private float pageURX = 0;
	private float pageLLY = 0;
	private float pageURY = 0;
	
	private boolean found;
	
	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	private int pageSize = 1;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public ExtractTextLocations()
		throws IOException {
		super.setSortByPosition(true);
	}

	public ExtractTextLocations(String fullPath) throws IOException {

		PDDocument document = null;

		try {
			File input = new File(fullPath);
			document = PDDocument.load(input);

			if (document.isEncrypted()) {
				try {
					document.decrypt(StringPool.BLANK);
				} catch (Exception e) {
					_log.error(e);
				}
			}

			// ExtractTextLocations printer = new ExtractTextLocations();

			List allPages = document.getDocumentCatalog().getAllPages();

			if (allPages != null && allPages.size() > 0) {

				setPageSize(allPages.size());

				for (int i = 0; i < allPages.size(); i++) {
					PDPage page = (PDPage) allPages.get(i);

					_log.info("***Page__" + i + 1);
					PDStream contents = page.getContents();
					if (contents != null) {
						this.processStream(page, page.findResources(), page.getContents().getStream());
					}

					PDRectangle pageSize = page.findMediaBox();

					if (pageSize != null) {
						// setPageSize(i);

						setPageWidth(pageSize.getWidth());
						setPageHeight(pageSize.getHeight());
						setPageLLX(pageSize.getLowerLeftX());
						setPageURX(pageSize.getUpperRightX());
						setPageLLY(pageSize.getLowerLeftY());
						setPageURY(pageSize.getUpperRightY());

						// Already find special character
						// break;
					}

					if (isFound()) {

						_log.info("IS_FOUND");
						setPageSize(i + 1);

						break;
					}
				}
			}
		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	@Override
	protected void processTextPosition(TextPosition text) {

		

		if (text.getCharacter().equals(StringPool.POUND) && Float.compare(text.getFontSize(), 1f) == 0) {
			setFound(true);
			
			System.out.println("String[" + text.getXDirAdj() + "," + text.getYDirAdj() + " fs=" + text.getFontSize()
					+ " xscale=" + text.getXScale() + " height=" + text.getHeightDir() + " space="
					+ text.getWidthOfSpace() + " width=" + text.getWidthDirAdj() + "]" + text.getCharacter());

			System.out.println("String[" + text.getX() + "," + text.getY() + " fs=" + text.getFontSize() + " xscale="
					+ text.getXScale() + " height=" + text.getHeight() + " space=" + text.getWidthOfSpace() + " width="
					+ text.getWidth() + "]" + text.getCharacter());
			setAnchorX(text.getX());
			setAnchorY(text.getY());
			setSignatureHeight(text.getHeight());
			setSignatureWidth(text.getWidth());
		}

	}

	public float getAnchorX() {

		return anchorX;
	}

	public void setAnchorX(float anchorX) {

		this.anchorX = anchorX;
	}

	public float getAnchorY() {

		return anchorY;
	}

	public void setAnchorY(float anchorY) {

		this.anchorY = anchorY;
	}

	public float getSignatureWidth() {

		return signatureWidth;
	}

	public void setSignatureWidth(float signatureWidth) {

		this.signatureWidth = signatureWidth;
	}

	public float getSignatureHeight() {

		return signatureHeight;
	}

	public void setSignatureHeight(float signatureHeight) {

		this.signatureHeight = signatureHeight;
	}

	public float getPageWidth() {

		return pageWidth;
	}

	public void setPageWidth(float pageWidth) {

		this.pageWidth = pageWidth;
	}

	public float getPageHeight() {

		return pageHeight;
	}

	public void setPageHeight(float pageHeight) {

		this.pageHeight = pageHeight;
	}

	public float getPageLLX() {

		return pageLLX;
	}

	public void setPageLLX(float pageLLX) {

		this.pageLLX = pageLLX;
	}

	public float getPageURX() {

		return pageURX;
	}

	public void setPageURX(float pageURX) {

		this.pageURX = pageURX;
	}

	public float getPageLLY() {

		return pageLLY;
	}

	public void setPageLLY(float pageLLY) {

		this.pageLLY = pageLLY;
	}

	public float getPageURY() {

		return pageURY;
	}

	public void setPageURY(float pageURY) {

		this.pageURY = pageURY;
	}

	private Log _log = LogFactoryUtil
		.getLog(ExtractTextLocations.class);

}
