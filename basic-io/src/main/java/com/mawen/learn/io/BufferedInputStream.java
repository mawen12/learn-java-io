package com.mawen.learn.io;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

import com.mawen.design.pattern.annotations.structure.Decorator;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/10/31
 */
public class BufferedInputStream extends FilterInputStream {

	private static int DEFAULT_BUFFER_SIZE = 8192;

	private static int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;

	protected volatile byte buf[];

	private static final AtomicReferenceFieldUpdater<BufferedInputStream, byte[]> bufUpdater =
			AtomicReferenceFieldUpdater.newUpdater(BufferedInputStream.class, byte[].class, "buf");

	protected int count;

	protected int pos;

	protected int markPos = -1;

	protected int markLimit;

	private InputStream getInIfOpen() throws IOException {
		InputStream input = in;
		if (input == null)
			throw new IOException("Stream closed");
		return input;
	}

	private byte[] getBufIfOpen() throws IOException {
		byte[] buffer = buf;
		if (buffer == null)
			throw new IOException("Stream closed");
		return buffer;
	}

	@Decorator(component = InputStream.class)
	public BufferedInputStream(InputStream in) {
		this(in, DEFAULT_BUFFER_SIZE);
	}

	@Decorator(component = InputStream.class)
	public BufferedInputStream(InputStream in, int size) {
		super(in);
		if (size <= 0) {
			throw new IllegalArgumentException("Buffer size <= 0");
		}
		buf = new byte[size];
	}

	private void fill() throws IOException {
		byte[] buffer = getBufIfOpen();
		if (markPos < 0) {
			pos = 0;
		}
		else if (pos >= buffer.length) {
			if (markPos > 0) {
				int sz = pos - markPos;
				System.arraycopy(buffer,markPos,buffer,0,sz);
				pos = sz;
				markPos = 0;
			}
			else if (buffer.length >= markLimit) {
				markPos = -1;
				pos = 0;
			}
			else if (buffer.length >= MAX_BUFFER_SIZE) {
				throw new OutOfMemoryError("Required array size too large");
			}
			else {
				int nsz = (pos <= MAX_BUFFER_SIZE - pos) ? pos * 2 : MAX_BUFFER_SIZE;
				if (nsz > markLimit) {
					nsz = markLimit;
				}
				byte nbuf[] = new byte[nsz];
				System.arraycopy(buffer, 0, nbuf, 0, pos);
				if (!bufUpdater.compareAndSet(this, buffer, nbuf)) {
					throw new IOException("Stream closed");
				}
				buffer = nbuf;
			}
		}

		count = pos;
		int n = getInIfOpen().read(buffer, pos, buffer.length - pos);
		if (n > 0) {
			count = n + pos;
		}
	}

	public synchronized int read() throws IOException {
		if (pos >= count) {
			fill();
			if (pos >= count) {
				return -1;
			}
		}
		return getBufIfOpen()[pos++] & 0xff;
	}

	private int read1(byte[] b, int off, int len) throws IOException {
		int avail = count - pos;
		if (avail < 0) {
			if (len >= getBufIfOpen().length && markPos < 0) {
				return getInIfOpen().read(b, off, len);
			}
			fill();
			avail = count - pos;
			if (avail <= 0) {
				return -1;
			}
		}
		int cnt = avail < len ? avail : len;
		System.arraycopy(getBufIfOpen(),pos,b,off,cnt);
		pos += cnt;
		return cnt;
	}

	
}
