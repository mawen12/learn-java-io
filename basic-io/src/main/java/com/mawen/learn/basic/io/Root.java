package com.mawen.learn.basic.io;

/**
 * Use {@link java.io.PrintStream#print(int)} and {@link java.io.PrintStream#println(int)}
 * by converting the value with the appropriate {@link Object#toString()} method.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/formatting.html">formatting</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/10
 */
public class Root {

	public static void main(String[] args) {
		int i = 2;
		double r = Math.sqrt(i);

		System.out.print("The square root of ");
		System.out.print(i);
		System.out.print(" is ");
		System.out.print(r);
		System.out.println(".");

		i = 5;
		r = Math.sqrt(i);
		System.out.println("The square root of " + i + " is " + r + ".");
	}
}
