package com.mawen.learn.basic.io.book.chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Copying a Source File to a Destination File with try-with-resources.
 *
 * <pre>{@code
 * 	java CopyTryWithResources xanadu.txt xanadu.bak
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-2
 * @since 2024/5/29
 */
public class CopyTryWithResources {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("usage: java Copy srcfile destfile");
			return;
		}

		try (FileInputStream fis = new FileInputStream(args[0]);
			 FileOutputStream fos = new FileOutputStream(args[1])) {
			int b;
			while ((b = fis.read()) != -1) {
				fos.write(b);
			}
		}
		catch (FileNotFoundException e) {
			System.err.println(args[0] + " could not be opened for input, or " + args[1] + " could not be opened for output");
		}
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
}
