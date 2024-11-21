package com.mawen.learn.io;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/11/21
 */
public interface ObjectStreamConstants {

	short STREAM_MAGIC = (short) 0xaced;

	short STREAM_VERSION = 5;

	byte TC_BASE = 0x70;

	byte TC_NULL = 0x70;

	byte TC_REFERENCE = 0x71;

	byte TC_CLASSDESC = 0x72;

	byte TC_OBJECT = 0x73;

	byte TC_STRING = 0x74;

	byte TC_ARRAY = 0x75;

	byte TC_CLASS = 0x76;

	byte TC_BLOCKDATA = 0x77;

	byte TC_ENDBLOCKDATA = 0x78;

	byte TC_RESET = 0x79;

	byte TC_BLOCKDATALONG = 0x7A;

	byte TC_EXCEPTION = 0x7B;

	byte TC_LONGSTRING = 0x7C;

	byte TC_PROXYCLASSDESC = 0x7D;

	byte TC_ENUM = 0x7E;

	byte TC_MAX = 0x7E;

	int baseWireHandle = 0x7e0000;

	byte SC_WRITE_METHOD = 0x01;

	byte SC_BLOCK_DATA = 0x08;

	byte SC_SERIALIZABLE = 0x02;

	byte SC_EXTERNALIZABLE = 0x04;

	byte SC_ENUM = 0x10;

	int PROTOCOL_VERSION_1 = 1;

	int PROTOCOL_VERSION_2 = 2;


}
