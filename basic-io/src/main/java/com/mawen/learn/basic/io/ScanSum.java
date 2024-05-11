package com.mawen.learn.basic.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Locale;
import java.util.Scanner;

/**
 * When use {@link Scanner} read Java Language's primitive types(expect for char), as well as {@link java.math.BigInteger} and {@link java.math.BigDecimal},
 * we have to mention the locale, because thousands separators and decimal symbols are locale specific.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/scanning.html">scanning</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/10
 */
public class ScanSum {

	public static void main(String[] args) throws FileNotFoundException {

		Scanner s = null;
		double sum = 0;

		try {
			s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));
			// in US locale, string "32,767" as representing an integer value
			s.useLocale(Locale.US);

			while (s.hasNext()) {
				if (s.hasNextDouble()) {
					sum += s.nextDouble();
				}
				else {
					s.next();
				}
			}
		}
		finally {
			if (s != null) {
				s.close();
			}
		}

		System.out.println(sum);
	}
}
