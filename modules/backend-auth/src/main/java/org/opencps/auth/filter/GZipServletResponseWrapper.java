package org.opencps.auth.filter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.opencps.auth.api.keys.Constants;

class GZipServletResponseWrapper extends HttpServletResponseWrapper {

	private GZipServletOutputStream gzipOutputStream = null;
	private PrintWriter printWriter = null;

	public GZipServletResponseWrapper(HttpServletResponse response) throws IOException {
		super(response);
	}

	public void close() throws IOException {

		// PrintWriter.close does not throw exceptions.
		// Hence no try-catch block.
		if (this.printWriter != null) {
			this.printWriter.close();
		}

		if (this.gzipOutputStream != null) {
			this.gzipOutputStream.close();
		}
	}

	/**
	 * Flush OutputStream or PrintWriter
	 *
	 * @throws IOException
	 */

	@Override
	public void flushBuffer() throws IOException {

		// PrintWriter.flush() does not throw exception
		if (this.printWriter != null) {
			this.printWriter.flush();
		}

//		IOException exception1 = null;
		try {
			if (this.gzipOutputStream != null) {
				this.gzipOutputStream.flush();
			}
		} catch (IOException e) {
			throw e;
		}

//		IOException exception2 = null;
		try {
			super.flushBuffer();
		} catch (IOException e) {
			throw e;
		}

//		if (exception1 != null)
//			throw exception1;
//		if (exception2 != null)
//			throw exception2;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (this.printWriter != null) {
			throw new IllegalStateException(Constants.G_ZIP_SERVLET_GET_OUTPUT_STREAM_EX);
		}
		if (this.gzipOutputStream == null) {
			this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
		}
		return this.gzipOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (this.printWriter == null && this.gzipOutputStream != null) {
			throw new IllegalStateException(Constants.G_ZIP_SERVLET_GET_WRITER_EX);
		}
		if (this.printWriter == null) {
			this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
//			this.printWriter = new PrintWriter(
//					new OutputStreamWriter(this.gzipOutputStream, getResponse().getCharacterEncoding()));
			this.printWriter = new PrintWriter(
					new OutputStreamWriter(this.gzipOutputStream, Constants.G_ZIP_SERVLET_GET_WRITER_CHARSET));
		}
		return this.printWriter;
	}

	@Override
	public void setContentLength(int len) {
		// ignore, since content length of zipped content
		// does not match content length of unzipped content.
	}
}