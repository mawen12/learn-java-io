package com.mawen.learn.nio.book.chapter11;

import java.util.Formatter;

/**
 * Demonstrating the Formatter Class
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 11-1
 * @since 2024/6/18
 */
public class FormatterDemo {

	public static void main(String[] args) {
		Formatter formatter = new Formatter();

		formatter.format("%d", 123);
		System.out.println(formatter.toString());

		formatter.format("%x", 123);
		System.out.println(formatter.toString());

		formatter.format("%c", 'X');
		System.out.println(formatter.toString());

		formatter.format("%f", 0.1);
		System.out.println(formatter.toString());

		formatter.format("%s%n", "Hello, World");
		System.out.println(formatter.toString());

		formatter.format("%10.2f", 98.375);
		System.out.println(formatter.toString());

		formatter.format("%05d", 123);
		System.out.println(formatter.toString());

		formatter.format("%1$d %1$d", 123);
		System.out.println(formatter.toString());

		formatter.format("%d %d", 123);
		System.out.println(formatter.toString());

		formatter.close();
	}
}
