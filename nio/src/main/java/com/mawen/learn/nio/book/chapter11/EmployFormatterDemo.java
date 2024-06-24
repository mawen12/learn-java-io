package com.mawen.learn.nio.book.chapter11;

import java.util.Locale;

/**
 * Exercising the Employee Class
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 11-4
 * @since 2024/6/21
 */
public class EmployFormatterDemo {

	public static void main(String[] args) {
		Employee emp = new Employee("John Doe", 1000);
//		print(emp);

		FormatterEmployee formatterEmployee = new FormatterEmployee("John Doe", 1000);
		print(formatterEmployee);
	}

	static void print(Object emp) {
		System.out.printf("[%s]%n", emp);
		System.out.printf(Locale.FRENCH, "[%s]%n", emp);
		System.out.printf("[%S]%n", emp);
		System.out.printf("[%10.3s]%n", emp);
		System.out.printf("[%-10.3s]%n", emp);
		System.out.printf("[%#s]%n", emp);
	}
}
