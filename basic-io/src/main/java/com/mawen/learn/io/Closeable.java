package com.mawen.learn.io;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/10/31
 */
public interface Closeable extends AutoCloseable {

	void close() throws IOException;
}
