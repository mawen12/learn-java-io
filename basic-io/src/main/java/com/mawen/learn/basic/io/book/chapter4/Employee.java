package com.mawen.learn.basic.io.book.chapter4;

import java.io.Serializable;

/**
 * Implementing Serializable
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-9
 * @since 2024/5/30
 */
public class Employee implements Serializable {

	private String name;

	private int age;

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}
}
