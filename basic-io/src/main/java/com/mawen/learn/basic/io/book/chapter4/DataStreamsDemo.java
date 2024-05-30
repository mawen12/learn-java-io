package com.mawen.learn.basic.io.book.chapter4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Outputting and then Inputting a Stream of Multibyte Values
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-8
 * @since 2024/5/30
 */
public class DataStreamsDemo {

	final static String FILE_NAME = "values.dat";

	public static void main(String[] args) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
			 DataOutputStream dos = new DataOutputStream(fos)) {
			dos.writeInt(1995);
			dos.writeUTF("Saving this String in modified UTF-8 format!");
			dos.writeFloat(1.0F);
		}
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}

		try (FileInputStream fis = new FileInputStream(FILE_NAME);
			 DataInputStream dis = new DataInputStream(fis)) {
			System.out.println(dis.readInt());
			System.out.println(dis.readUTF());
			System.out.println(dis.readFloat());
		}
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}

		Files.deleteIfExists(Paths.get(FILE_NAME));
	}
}
