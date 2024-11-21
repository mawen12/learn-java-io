package com.mawen.learn.io;

import java.util.Enumeration;
import java.util.Vector;

import com.mawen.design.pattern.annotations.structure.Decorator;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/10/31
 */
public class SequenceInputStream extends InputStream {

	Enumeration<? extends InputStream> e;
	InputStream in;

	@Decorator(component = InputStream.class)
	public SequenceInputStream(Enumeration<? extends InputStream> e) {
		this.e = e;
		try {
			nextStream();
		}
		catch (IOException ex) {
			throw new Error("panic");
		}
	}

	@Decorator(component = InputStream.class)
	public SequenceInputStream(InputStream s1, InputStream s2) {
		Vector<InputStream> v = new Vector<>(2);

		v.addElement(s1);
		v.addElement(s2);
		e = v.elements();
		try {
			nextStream();
		}
		catch (IOException ex) {
			throw new Error("panic");
		}
	}

	final void nextStream() throws IOException {
		if (in != null) {
			in.close();
		}

		if (e.hasMoreElements()) {
			in = e.nextElement();
			if (in == null)
				throw new NullPointerException();
		}
		else in = null;
	}

	@Override
	public int available() throws IOException {
		if (in == null) {
			return 0;
		}
		return in.available();
	}

	@Override
	public int read() throws IOException {
		while (in != null) {
			int c = in.read();
			if (c != -1) {
				return c;
			}
			nextStream();
		}
		return -1;
	}

	public int read(byte b[], int off, int len) throws IOException {
		if (in == null) {
			return -1;
		}
		else if (b == null) {
			throw new NullPointerException();
		}
		else if (off < 0 || len < 0 || len > b.length - off) {
			throw new IndexOutOfBoundsException();
		}
		else if (len == 0) {
			return 0;
		}

		do {
			int n = in.read(b, off, len);
			if (n > 0) {
				return n;
			}
			nextStream();
		}
		while (in != null);

		return -1;
	}

	public void close() throws IOException {
		do {
			nextStream();
		} while (in != null);
	}
}
