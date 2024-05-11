package com.mawen.learn.basic.io;

/**
 * Use {@link java.io.PrintStream#format(String, Object...)} formats multiple arguments based on a format string.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/formatting.html">formatting</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/10
 */
public class Root2 {

	public static void main(String[] args) {
		int i = 2;
		double r = Math.sqrt(i);

		System.out.printf("The square root of %d is %f.%n", i, r);
	}
}
