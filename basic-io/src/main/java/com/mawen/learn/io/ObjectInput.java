package com.mawen.learn.io;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/11/21
 */
public interface ObjectInput extends DataInput, AutoCloseable {

	Object readObject() throws ClassNotFoundException, IOException;

	int read() throws IOException;

	int read(byte b[]) throws IOException;

	int read(byte b[], int off, int len) throws IOException;

	int available() throws IOException;

	void close() throws IOException;
}
