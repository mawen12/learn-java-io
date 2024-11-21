package com.mawen.learn.io;


import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/11/21
 */
public class ObjectInputStream extends InputStream implements ObjectInput, ObjectStreamConstants {

	private static final int NULL_HANDLE = -1;

	private static final Object unsharedMarker = new Object();

	private static final Map<String, Class<?>> primClasses = new HashMap<>(8, 1.0F);

	static {
		primClasses.put("boolean", boolean.class);
		primClasses.put("byte", byte.class);
		primClasses.put("char", char.class);
		primClasses.put("short", short.class);
		primClasses.put("int", int.class);
		primClasses.put("long", long.class);
		primClasses.put("float", float.class);
		primClasses.put("double", double.class);
		primClasses.put("void", void.class);
	}

	private class BlockDataInputStream extends InputStream implements DataInput {

		private static final int MAX_BLOCK_SIZE = 1024;

		private static final int MAX_HEADER_SIZE = 5;

		private static final int CHAR_BUF_SIZE = 256;

		private static final int HEADER_BLOCKED = -2;

		private final byte[] buf = new byte[MAX_BLOCK_SIZE];

		private final byte[] hbuf = new byte[MAX_HEADER_SIZE];

		private final char[] cbuf = new char[CHAR_BUF_SIZE];

		private boolean blkmode = false;

		private int pos = 0;

		private int end = -1;

		private int unread = 0;

		private final PeekInputStream in;

		private final 

	}
}
