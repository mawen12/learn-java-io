package com.mawen.learn.io;

import com.mawen.learn.lang.Important;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/10/31
 */
public class ByteArrayInputStream extends InputStream {

	protected byte buf[];

	protected int pos;

	protected int mark = 0;

	protected int count;

	public ByteArrayInputStream(byte buf[]) {
		this.buf = buf;
		this.pos = 0;
		this.count = buf.length;
	}

	public ByteArrayInputStream(byte buf[], int offset, int length) {
		this.buf = buf;
		this.pos = offset;
		this.count = Math.min(offset + length, buf.length);
		this.mark = offset;
	}

	@Important
	@Override
	public synchronized int read() {
		return (pos < count) ? (buf[pos++] & 0xff) : -1;
	}

	@Override
	public synchronized int read(byte[] b, int off, int len) throws IOException {
		if (b == null) {
			throw new NullPointerException();
		}
		else if (off < 0 || len > 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		}

		if (pos >= count) {
			return -1;
		}

		int avail = count - pos;
		if (len > avail) {
			len = avail;
		}
		if (len <= 0) {
			return 0;
		}
		System.arraycopy(buf, pos, b, off, len);
		pos += len;
		return len;
	}

	@Override
	public synchronized long skip(long n) {
		long k = count - pos;
		if (n < k) {
			k = n < 0 ? 0 : n;
		}

		pos += k;
		return k;
	}

	@Override
	public synchronized int available() {
		return count - pos;
	}

	public boolean markSupported() {
		return true;
	}

	public void mark(int readAheadLimit) {
		mark = pos;
	}

	public synchronized void reset() {
		pos = mark;
	}

	public void close() throws IOException {
	}
}
