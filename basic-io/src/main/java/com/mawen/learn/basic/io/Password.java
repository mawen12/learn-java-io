package com.mawen.learn.basic.io;

import java.io.Console;
import java.util.Arrays;

/**
 * We should run this program in terminal.
 * <pre>{@code
 * 	cd /Users/mawen/Documents/learn/JAVA/learn-java-io/basic-io/target/classes
 *	java com/mawen/learn/basic/io/Password
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/10
 */
public class Password {

	public static void main(String[] args) {

		Console c = System.console();
		if (c == null) {
			System.err.println("No console.");
			System.exit(1);
		}

		String login = c.readLine("Enter your login: ");
		char[] oldPassword = c.readPassword("Enter your password: ");

		if (verify(login, oldPassword)) {
			boolean noMatch;
			do {
				char[] newPassword1 = c.readPassword("Enter your new password: ");
				char[] newPassword2 = c.readPassword("Enter new password again: ");
				noMatch = !Arrays.equals(newPassword1, newPassword2);
				if (noMatch) {
					c.format("Passwords don't match. Try again.%n");
				}
				else {
					change(login, newPassword1);
					c.format("Password for %s changed.%n", login);
				}
				Arrays.fill(newPassword1, ' ');
				Arrays.fill(newPassword2, ' ');
			} while (noMatch);
		}

		Arrays.fill(oldPassword, ' ');
	}

	// Dummy change method.
	static boolean verify(String login, char[] password) {
		return true;
	}

	// Dummy change method.
	static void change(String login, char[] password) {

	}
}
