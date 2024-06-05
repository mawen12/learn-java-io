package com.mawen.learn.nio.book.charpter7;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Channel Transfer
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-6
 * @since 2024/6/5
 */
public class ChannelTransferDemo {

	public static void main(String[] args) {
		// verify a single command-line argument is specified
		if (args.length != 1) {
			System.err.println("usage: java ChannelTransferDemo filespec");
			return;
		}

		// create a file input stream to the file identified by the command-line argument
		try (FileInputStream fis = new FileInputStream(args[0])) {
			// obtain a file channel for reading from this file
			FileChannel inChannel = fis.getChannel();
			// obtain an output channel for sending bytes to the standard output stream
			WritableByteChannel outChannel = Channels.newChannel(System.out);
			// transfer the file content to standard output
			inChannel.transferTo(0, inChannel.size(), outChannel);
		}
		catch (IOException e) {
			System.out.println("I/O error: " + e.getClass());
		}
	}

}
