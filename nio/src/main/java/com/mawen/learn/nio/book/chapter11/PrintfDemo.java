package com.mawen.learn.nio.book.chapter11;

/**
 * Formatting via printf()
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 11-2
 * @since 2024/6/18
 */
public class PrintfDemo {

	public static void main(String[] args) {
		System.out.printf("%04X%n", 478);
		System.out.printf("Current date: %1$tb %1$te, %1$tY%n", System.currentTimeMillis());
	}
}
