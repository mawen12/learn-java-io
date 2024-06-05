package com.mawen.learn.nio.book.charpter7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/6/4
 */
public class ChannelScatterAndGatherDemo {

	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("x.dat");
		ScatteringByteChannel src = (ScatteringByteChannel) Channels.newChannel(fis);

		ByteBuffer buffer1 = ByteBuffer.allocateDirect(5);
		ByteBuffer buffer2 = ByteBuffer.allocateDirect(3);
		ByteBuffer[] buffers = {buffer1, buffer2};

		src.read(buffers);

		print(buffer1);
		System.out.println();
		print(buffer2);

		buffer1.rewind();
		buffer2.rewind();

		FileOutputStream fos = new FileOutputStream("y.dat");
		GatheringByteChannel dest = (GatheringByteChannel) Channels.newChannel(fos);
		buffers[0] = buffer2;
		buffers[1] = buffer1;
		dest.write(buffers);
	}

	private static void print(ByteBuffer buffer) {
		buffer.flip();
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
	}
}
