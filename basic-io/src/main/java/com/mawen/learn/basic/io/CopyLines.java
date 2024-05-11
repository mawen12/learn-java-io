package com.mawen.learn.basic.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Use Buffered Character Streams read from {@code xanadu.txt} and write to {@code characteroutput.txt}.
 * It wrap unbuffered streams. The default buffered size is {@code 8192 8KB}.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/buffers.html">buffers</a>
 * @since 2024/5/9
 */
public class CopyLines {

	public static void main(String[] args) throws IOException {

		BufferedReader inputStream = null;
		BufferedWriter outputStream = null;

		try {
			inputStream = new BufferedReader(new FileReader("xanadu.txt"));
			outputStream = new BufferedWriter(new FileWriter("characteroutput.txt"));
			String l; // a line of text with the line

			while ((l = inputStream.readLine()) != null) {
				outputStream.newLine();
				outputStream.write(l);
			}
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}
}
