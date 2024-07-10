package com.mawen.learn.nio.book.chapter12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Saving Web Page HTML to a Text File
 *
 * <pre>{@code
 * 	java SavePageDemo https://apress.com
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-26
 * @since 2024/7/4
 */
public class SavePageDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java SavePageDemo url");
			return;
		}

		URL url = new URL(args[0]);
		InputStreamReader isr = new InputStreamReader(url.openStream());
		BufferedReader br = new BufferedReader(isr);

		List<String> lines = new ArrayList<>();
		String line;
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}

		System.out.println("Read " + lines.size() + " lines");

		Path path = Paths.get("page.html");
		Files.write(path, lines);
		path.toFile().deleteOnExit();
	}
}
