package com.mawen.learn.io;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/10/31
 */
public class CharConversionException extends IOException{

	private static final long serialVersionUID = 3440893383274322637L;

	public CharConversionException() {
		super();
	}

	public CharConversionException(String message) {
		super(message);
	}
}
