package com.mawen.learn.basic.io.book.chapter4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * File copy by {@link FileInputStream} and {@link FileOutputStream}.
 *
 * <pre>{@code
 * 	java Copy xanadu.txt xanadu.bak
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-1
 * @since 2024/5/29
 */
public class Copy {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("usage: java Copy srcfile destfile");
			return;
		}

		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(args[0]);
			fos = new FileOutputStream(args[1]);
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
		finally {
			if (fis != null) {
				try {
					fis.close();
				}
				catch (IOException e) {
					assert false; // shouldn't happen in this context
				}
			}
			if (fos != null) {
				try {
					fos.close();
				}
				catch (IOException e) {
					assert false; // shouldn't happen in this context
				}
			}
		}
	}
}
