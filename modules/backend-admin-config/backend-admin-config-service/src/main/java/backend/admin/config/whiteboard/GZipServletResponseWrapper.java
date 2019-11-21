package backend.admin.config.whiteboard;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

class GZipServletResponseWrapper extends HttpServletResponseWrapper {

	private GZipServletOutputStream gzipOutputStream = null;
	private PrintWriter printWriter = null;
	private static final String ENCODING = "UTF-8";
	private static final String ILLEGAL_OUTPUTSTREAM_MESSAGE = "PrintWriter obtained already - cannot get OutputStream";
	private static final String ILLEGAL_PRINTWRITER_MESSAGE = "OutputStream obtained already - cannot get PrintWriter";
	
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
		if (this.printWriter != null) {
			this.printWriter.flush();
		}

		IOException exception1 = null;
		try {
			if (this.gzipOutputStream != null) {
				this.gzipOutputStream.flush();
			}
		} catch (IOException e) {
			exception1 = e;
		}

		IOException exception2 = null;
		try {
			super.flushBuffer();
		} catch (IOException e) {
			exception2 = e;
		}

		if (exception1 != null)
			throw exception1;
		if (exception2 != null)
			throw exception2;
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if (this.printWriter != null) {
			throw new IllegalStateException(ILLEGAL_OUTPUTSTREAM_MESSAGE);
		}
		if (this.gzipOutputStream == null) {
			this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
		}
		return this.gzipOutputStream;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if (this.printWriter == null && this.gzipOutputStream != null) {
			throw new IllegalStateException(ILLEGAL_PRINTWRITER_MESSAGE);
		}
		if (this.printWriter == null) {
			this.gzipOutputStream = new GZipServletOutputStream(getResponse().getOutputStream());
			this.printWriter = new PrintWriter(
					new OutputStreamWriter(this.gzipOutputStream, StandardCharsets.UTF_8));
		}
		return this.printWriter;
	}

	@Override
	public void setContentLength(int len) {
	}
}