package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Streaming and Outputting All Lines from a Text File
 *
 * <pre>{@code
 * 	java LambdaStreamsPathDemo poem.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-49
 * @since 2024/7/8
 */
public class StreamsLineDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java LambdaStreamsPathDemo textfilepath");
			return;
		}

		Files.lines(Paths.get(args[0])).forEach(System.out::println);
	}
}
