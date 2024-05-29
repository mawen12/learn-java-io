package com.mawen.learn.basic.io.book.chapter4;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Scrambling a Stream of bytes.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-4
 * @since 2024/5/29
 */
public class ScrambledOutputStream extends FilterOutputStream {

	private int[] map;

	public ScrambledOutputStream(OutputStream out, int[] map) {
		super(out);
		if (map == null) {
			throw new NullPointerException("map is null");
		}
		if (map.length != 256) {
			throw new IllegalArgumentException("map.length != 256");
		}
		this.map = map;
	}

	@Override
	public void write(int b) throws IOException {
		out.write(map[b]);
	}
}
