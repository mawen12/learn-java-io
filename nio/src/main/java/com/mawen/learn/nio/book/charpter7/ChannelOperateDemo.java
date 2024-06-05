package com.mawen.learn.nio.book.charpter7;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Demonstrating a File Channel
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/6/4
 */
public class ChannelOperateDemo {

	public static void main(String[] args) throws IOException {
		RandomAccessFile file = new RandomAccessFile("temp", "rw");
		// obtain a file channel for communicating with this file
		FileChannel fc = file.getChannel();

		long pos = 0;
		// report the file channel's current position
		System.out.println("Position = " + (pos = fc.position()));
		// report the file's size
		System.out.println("siz = " + fc.size());
		String msg = "This is a test message.";
		ByteBuffer buffer = ByteBuffer.allocate(msg.length() * 2);
		// treats this buffer as a character buffer and store the message in the buffer
		buffer.asCharBuffer().put(msg);

		// write buffer data into file channel
		fc.write(buffer);
		// recommend to the underlying operating system that the data be committed to the underlying storage device
		fc.force(true);
		System.out.println("position = " + fc.position());
		System.out.println("size = " + fc.size());

		buffer.clear();
		// resets the position the where it was before the message was written
		fc.position(pos);
		// reads the previously written content back into the buffer
		fc.read(buffer);
		buffer.flip();
		while (buffer.hasRemaining()) {
			System.out.print(buffer.getChar());
		}
	}

}
