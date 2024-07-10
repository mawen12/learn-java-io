package com.mawen.learn.nio.book.chapter12;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Saving Web Page HTML to a Text File, Revisited
 *
 * <pre>{@code
 * 	java SavePageByWriterDemo https://apress.com
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-27
 * @since 2024/7/4
 */
public class SavePageByWriterDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usgae: java SavePageByWriterDemo url");
			return;
		}

		URL url = new URL(args[0]);
		InputStreamReader isr = new InputStreamReader(url.openStream());
		BufferedReader br = new BufferedReader(isr);
		BufferedWriter bw = Files.newBufferedWriter(Paths.get("page.html"));
		String line;
		while ((line = br.readLine()) != null) {
			bw.write(line, 0, line.length());
			bw.newLine();
		}
		// must close the file to write data to storage
		bw.close();
	}
}
