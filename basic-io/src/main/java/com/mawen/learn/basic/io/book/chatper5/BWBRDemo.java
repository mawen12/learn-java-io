package com.mawen.learn.basic.io.book.chatper5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/31
 */
public class BWBRDemo {

	static String[] lines = {
			"It was the best of times, it was the worst of times,",
			"it was the age of wisdom, it was the age of foolishness,",
			"it was the epoch of belief, it was the epoch of incredulity,",
			"it was the season of Light, it was the season of Darkness,",
			"it was the spring of hope, it was the winter of despair."
	};

	public static void main(String[] args) throws IOException {

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("temp"))) {
			for (String line : lines) {
				writer.write(line, 0, line.length());
				writer.newLine();
			}
		}

		try (BufferedReader reader = new BufferedReader(new FileReader("temp"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}

		Files.deleteIfExists(Paths.get("temp"));
	}
}
