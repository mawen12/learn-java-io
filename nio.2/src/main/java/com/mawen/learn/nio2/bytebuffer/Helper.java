package com.mawen.learn.nio2.bytebuffer;

import java.io.IOException;
import java.nio.Buffer;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/13
 */
public class Helper {

	public static void showTitle() {
		System.out.format("%-20s %-3s %-3s %-3s %-3s%n",
				"Title", "bufferPosition", "limit", "remaining", "capacity");
	}

	public static void showStatus(String where, Buffer b) throws IOException {
		System.out.format("%-25s %-11d %-6d %-7d %-3d%n",
				where, b.position(), b.limit(), b.remaining(), b.capacity());
	}
}
