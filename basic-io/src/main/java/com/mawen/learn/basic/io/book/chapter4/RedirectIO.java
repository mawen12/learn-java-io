package com.mawen.learn.basic.io.book.chapter4;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Programmatically Specifying the Standard Input Source and Standard Output/Error Destinations.
 *
 * <pre>{@code
 * 	java RedirectIO xanadu.txt b.txt c.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/30
 */
public class RedirectIO {

	public static void main(String[] args) throws IOException {

		if (args.length != 3) {
			System.err.println("usage: java RedirectIO stdinfile stoutfile stderrfile");
			return;
		}

		System.setIn(new FileInputStream(args[0]));
		System.setOut(new PrintStream(args[1]));
		System.setErr(new PrintStream(args[2]));

		int ch;

		while ((ch = System.in.read()) != -1) {
			System.out.print((char) ch);
		}
		System.err.println("Redirected error output");
	}
}
