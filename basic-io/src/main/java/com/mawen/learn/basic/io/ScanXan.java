package com.mawen.learn.basic.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

/**
 * Use {@link Scanner} to break down formatted input into tokens.
 * {@link Scanner} use white space to separate tokens.
 * {@link Scanner} is not stream, by call {@link Scanner#close()} to indicate that we are done with its underlying stream.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/scanning.html">scanning</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/10
 */
public class ScanXan {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner s = null;

		try {
			s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));

			while (s.hasNext()) {
				// the scanner treats all input tokens as simple String values,
				// so the s.next() return String.
				System.out.println(s.next());
			}
		}
		finally {
			if (s != null) {
				s.close();
			}
		}
	}
}
