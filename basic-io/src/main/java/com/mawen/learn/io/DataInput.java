package com.mawen.learn.io;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/11/21
 */
public interface DataInput {

	void readFully(byte b[]) throws IOException;

	void readFully(byte b[], int off, int len) throws IOException;

	int skipBytes(int n) throws IOException;

	boolean readBoolean() throws IOException;

	byte readByte() throws IOException;

	int readUnsignedByte() throws IOException;

	short readShort() throws IOException;

	int readUnsignedShort() throws IOException;

	char readChar() throws IOException;

	int readInt() throws IOException;

	long readLong() throws IOException;

	float readFloat() throws IOException;

	double readDouble() throws IOException;

	String readLine() throws IOException;

	String readUTF() throws IOException;
}
