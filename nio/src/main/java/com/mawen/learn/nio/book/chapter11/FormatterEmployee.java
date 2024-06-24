package com.mawen.learn.nio.book.chapter11;

import java.util.Formattable;
import java.util.FormattableFlags;
import java.util.Formatter;
import java.util.Locale;

/**
 * Implementing {@link Formattable}
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 11-5
 * @since 2024/6/21
 */
public class FormatterEmployee implements Formattable {

	private final String name;

	private final int empno;

	public FormatterEmployee(String name, int empno) {
		this.name = name;
		this.empno = empno;
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		StringBuilder sb = new StringBuilder();

		String output = this.name;
		if (formatter.locale().equals(Locale.FRENCH) && name.equals("John Doe")) {
			output = "Jean Dupont";
		}
		output += ": " + empno;

		if ((flags & FormattableFlags.UPPERCASE) == FormattableFlags.UPPERCASE) {
			output = output.toUpperCase();
		}

		boolean alternate = (flags & FormattableFlags.ALTERNATE) == FormattableFlags.ALTERNATE;
		alternate |= (precision >= 0 && precision <= 8);
		if (alternate) {
			output = " " + empno;
		}

		if (precision == -1 || output.length() <= precision) {
			sb.append(output);
		}
		else {
			sb.append(output.substring(0, precision - 1)).append('*');
		}

		int len = sb.length();
		if (len < width) {
			boolean leftJustified = (flags & FormattableFlags.LEFT_JUSTIFY) == FormattableFlags.LEFT_JUSTIFY;
			for (int i = 0, end = width - len; i < end; i++) {
				if (leftJustified) {
					sb.append(' ');
				}
				else {
					sb.insert(0, ' ');
				}
			}
		}

		formatter.format(sb.toString());
	}

	@Override
	public String toString() {
		return name +": " + empno;
	}
}
