package com.mawen.learn.nio.book.chatper8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Serving Time to Clients
 *
 * <pre>{@code
 * 	value: 1718674643559
 * 	binary: 		 00000000 00000000 00000001 10010000 00101000 11111101 11011010 01100111
 * 	signed: 	   			0	     0		  1	    -122       40       -3      -38      103
 * 	correct(& 255):	   		0        0        1      144	   40      253	    218      103
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 8-1
 * @since 2024/6/17
 */
public class SelectorServer {

	final static int DEFAULT_PORT = 9999;

	static ByteBuffer bb = ByteBuffer.allocateDirect(8);

	public static void main(String[] args) throws IOException {
		int port = DEFAULT_PORT;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		System.out.println("Server staring ... listening on port " + port);

		// obtain a server socket channel followed by the underlying socket
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ServerSocket ss = ssc.socket();
		// bound to the specified port
		ss.bind(new InetSocketAddress(port));
		// configure for nonblocking mode in preparation for registering this channel with a selector
		ssc.configureBlocking(false);

		// obtain a selector
		Selector s = Selector.open();
		// the server socket channel registers itself with the selector so that it can learn
		// when the channel is ready to perform an accept operation
		ssc.register(s, SelectionKey.OP_ACCEPT);

		while (true) {
			int n = s.select();
			if (n == 0) {
				continue; // there are no ready channels to process
			}
			Iterator<SelectionKey> it = s.selectedKeys().iterator();
			while (it.hasNext()) {
				SelectionKey key = it.next();
				if (key.isAcceptable()) {
					// A connection was accepted by a ServerSocketChannel
					SocketChannel sc = ((ServerSocketChannel )key.channel()).accept();
					if (sc == null) {
						continue; // in case accept() returns null
					}
					System.out.println("Receiving connection");
					bb.clear();
					long millis = System.currentTimeMillis();
					bb.putLong(millis);
					bb.flip();
					System.out.println("Writing current time " + millis);
					while (bb.hasRemaining()) {
						sc.write(bb);
					}
					sc.close();
				}
				it.remove();
			}
		}
	}
}
