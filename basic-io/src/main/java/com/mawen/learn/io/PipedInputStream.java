package com.mawen.learn.io;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/10/31
 */
public class PipedInputStream extends InputStream {

	boolean closeByWriter = false;
	volatile boolean closedByReader = false;
	boolean connected = false;

	Thread readSide;
	Thread writeSide;

	private static final int DEFAULT_PIPE_SIZE = 1024;

	protected static final int PIPE_SIZE = DEFAULT_PIPE_SIZE;

	protected byte buffer[];

	protected int in = -1;

	protected int out = 0;


}
