package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Streaming and Outputting the Paths of All Files That Match a File Extension
 *
 * <pre>{@code
 * 	java StreamsPathDemo . txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-48
 * @since 2024/7/8
 */
public class StreamsPathDemo {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java StreamsPathDemo dirpath ext");
			return;
		}

		BiPredicate<Path, BasicFileAttributes> predicate = (path, attrs) ->
				attrs.isRegularFile() && path.getFileName().toString().endsWith(args[1]);

		try (Stream<Path> stream = Files.find(Paths.get(args[0]), 1, predicate)) {
			List<Path> entries = stream.collect(Collectors.toList());
			for (Path entry : entries) {
				System.out.println(entry);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
