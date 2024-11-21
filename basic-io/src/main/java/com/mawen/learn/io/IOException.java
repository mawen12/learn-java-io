package com.mawen.learn.io;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/10/31
 */
public class IOException extends Exception {

	private static final long serialVersionUID = -709908661734054435L;

	public IOException() {
		super();
	}

	public IOException(String message) {
		super(message);
	}

	public IOException(String message, Throwable cause) {
		super(message, cause);
	}

	public IOException(Throwable cause) {
		super(cause);
	}
}
