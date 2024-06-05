package com.mawen.learn.nio.book.chapter7;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-11
 * @since 2024/6/5
 */
public class PipeChannelDemo {

	final static int BUFFER_SIZE = 10;
	final static int LIMIT = 3;

	public static void main(String[] args) throws IOException {
		// obtain a pipe
		final Pipe pipe = Pipe.open();

		// create sender
		Thread sender = new Thread(new SenderTask(pipe));
		// create receiver
		Thread receiver = new Thread(new ReceiverTask(pipe));

		sender.start();
		receiver.start();
	}

	private static final class SenderTask implements Runnable {

		private final Pipe pipe;

		public SenderTask(Pipe pipe) {
			this.pipe = pipe;
		}

		@Override
		public void run() {
			// obtain a writable byte channel from the pipe
			try (WritableByteChannel src = pipe.sink()) {
				// allocate a byte buffer for storing content to be written
				ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

				for (int i = 0; i < LIMIT; i++) {

					buffer.clear();

					for (int j = 0; j < BUFFER_SIZE; j++) {
						// fill buffer
						buffer.put((byte) (Math.random() * 256));
					}
					// flip for draining
					buffer.flip();

					try {
						// because a single method call might not drain the entire buffer,
						// write() is invoked in a loop util it returns 0, which means that there is no more content to write
						while (src.write(buffer) > 0);
					}
					catch (IOException e) {
						System.err.println("I/O error: " + e.getMessage());
					}
				}
			}
			catch (IOException e) {
				System.err.println("I/O error: " + e.getMessage());
			}
		}
	}

	private static final class ReceiverTask implements Runnable {

		private final Pipe pipe;

		public ReceiverTask(Pipe pipe) {
			this.pipe = pipe;
		}

		@Override
		public void run() {
			// obtain a readable byte channel from the pipe
			try (ReadableByteChannel dst = pipe.source()) {
				// allocate a buffer for storing read content
				ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);

				try {
					// continually reads from the channel util the read() method returns -1
					// which indicates that the channel has reached the end of the stream
					while (dst.read(buffer) > 0) {
						// the buffer is flipped to prepare it for draining
						buffer.flip();
						while (buffer.hasRemaining()) {
							byte content = buffer.get();
							// each byte is bitwise ANDed with 255 to prevent a negative value from being output
							// get() return 8-bit integer value, that's converted to a 32-bit integer during the System.out.println() method call
							// This conversion applies sign extension, which means that some byte values become negative 32-bit integers
							System.out.println("Source: " + content + ", ret: " + (content & 255));
						}
						buffer.clear();
					}
				}
				catch (IOException ioe) {
					System.err.println("I/O error: " + ioe.getMessage());
				}
			}
			catch (IOException e) {
				System.err.println("I/O error: " + e.getMessage());
			}
		}
	}
}
