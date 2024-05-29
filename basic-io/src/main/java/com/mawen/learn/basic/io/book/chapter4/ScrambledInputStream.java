package com.mawen.learn.basic.io.book.chapter4;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Unscrambling a Stream of Bytes.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-6
 * @since 2024/5/29
 */
public class ScrambledInputStream extends FilterInputStream {

	private int[] map;

	public ScrambledInputStream(InputStream in, int[] map) {
		super(in);
		if (map == null) {
			throw new NullPointerException("map is null");
		}
		if (map.length != 256) {
			throw new IllegalArgumentException("map.length != 256");
		}
		this.map = map;
	}

	@Override
	public int read() throws IOException {
		int value = super.read();
		return value == -1 ? -1 : map[value];
	}

	@Override
	public int read(byte[] b, int off, int len) throws IOException {
		// nBytes <= len
		int nBytes = in.read(b, off, len);
		if (nBytes <= 0) {
			return nBytes;
		}

		for (int i = 0; i < nBytes; i++) {
			b[off + i] = (byte) map[off + i];
		}
		return nBytes;
	}
}
