package com.mawen.learn.nio.book.chapter9;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Playing with Regular Expressions
 *
 * <pre>{@code
 * 	java RegExDemo ox ox
 *
 * 	java RegExDemo box ox
 *
 * 	java RegExDemo ox box
 *
 * 	java RegExDemo .ox box
 * }</pre>
 *
 * <h2>1.A simple character
 * <p>consist of literal characters placed side by side and matches only these characters
 * <p>[abc] only match a,b and c character
 * <pre>{@code
 * 	java RegExDemo t[aiou]ck tack
 * }</pre>
 *
 * <h2>2.A negation character
 * <p>consist of circumflex metacharacter(^), followed by literal characters placed side by side,
 * and matches all character except for the characters in the class
 * <p>[^abc] doesn't match a, b and c character
 * <pre>{@code
 * 	java RegExDemo [^b]ox box
 *
 * 	java RegExDemo [^b]ox fox
 * }</pre>
 *
 * <h2>3.A range character
 * <p>consist on successive literal characters expressed as a starting literal character, followed by
 * the hyphen metacharacter(-), followed by an ending literal character, and matches all characters in
 * this range
 * <p>[a-z] match all characters from a though z
 * <pre>{@code
 * 	java RegExDemo [a-z]ox fox
 *
 * 	java RegExDemo [a-z]ox Fox
 * }</pre>
 *
 * <h2>4.A union character
 * <p>consist of multiple nested character classes and matches all characters that belong to the resulting
 * region
 * <p>[abc[u-z]] match a,b,c,u,v,w,x,y,z
 * <pre>{@code
 * 	java RegExDemo [t[a-c]]ox tbck
 *
 * 	java RegExDemo [t[a-c]]ox tzck
 *
 * 	java RegExDemo [[0-9][A-F][a-f]] e
 * }</pre>
 *
 * <h2>5.A intersection character
 * <p>consist of multiple && separated nested character classes and matches all characters that are common
 * to these nested character classes
 * <p>[a-c&&[c-f]] consist of character c, which is the only character common to [a-c] and [c-f]
 * <pre>{@code
 * 	java RegExDemo [aeiouy&&[y]] y
 *
 * 	java RegExDemo [aeiouy&&[y]] a
 * }</pre>
 *
 * <h2>6.A subtraction character
 * <p>consist of multiple && separated nested character classes, where at least one nested character class is
 * a negation character class, and match all characters except for those indicated by the negation class/classes
 * <p>[a-z&&[^x-z]] consists of characters a though w
 * <pre>{@code
 * 	java RegExDemo [a-z&&[^x-z]] y
 *
 * 	java RegExDemo [a-z&&[^x-z]] b
 * }</pre>
 *
 * <h2>7.The period metacharacter
 * <p>. match all characters except for the line terminator
 * <pre>{@code
 * 	java RegExDemo .ox box
 *
 * 	java RegExDemo .ox fox
 * }</pre>
 *
 * <h2>8.A predefined character
 * <pre>{@code
 * +----------------------------+------------------------------------------------+
 * | Predefined Character Class |                  Description                   |
 * +----------------------------+------------------------------------------------+
 * | \d                         | Match any digit character. [0-9]               |
 * | \D                         | Match any non-digit character. [^\d]           |
 * | \s                         | Match any whitespace character. [\t\n\x0B\f\r] |
 * | \S                         | Match any non-whitespace character. [^\s]      |
 * | \w                         | Match any word character. [a-zA-Z0-9]          |
 * | \W                         | Match any non-word character. [^\w]            |
 * +----------------------------+------------------------------------------------+
 * }</pre>
 * <pre>{@code
 * 	java RegExDemo \wbc abc
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 9-1
 * @since 2024/6/18
 */
public class RegExDemo {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java RegExDemo regex input");
			return;
		}

		try {
			System.out.println("regex: " + args[0]);
			System.out.println("input: " + args[1]);
			Pattern p = Pattern.compile(args[0]);
			Matcher m = p.matcher(args[1]);

			while (m.find()) {
				System.out.println("Located [" + m.group() + "] starting at " + m.start() + " and ending at " + (m.end() - 1));
			}
		}
		catch (PatternSyntaxException e) {
			System.err.println("Bad regex: " + e.getMessage());
			System.err.println("Description: " + e.getDescription());
			System.err.println("Index: " + e.getIndex());
			System.err.println("Incorrect pattern: " + e.getPattern());
		}
	}
}
