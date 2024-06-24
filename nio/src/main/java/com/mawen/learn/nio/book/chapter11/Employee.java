package com.mawen.learn.nio.book.chapter11;

/**
 * An Employee Consists of a Name and Number
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 11-3
 * @since 2024/6/21
 */
public class Employee {

	private final String name;

	private final int empno;

	public Employee(String name, int empno) {
		this.name = name;
		this.empno = empno;
	}

	@Override
	public String toString() {
		return name + ": " + empno;
	}
}
