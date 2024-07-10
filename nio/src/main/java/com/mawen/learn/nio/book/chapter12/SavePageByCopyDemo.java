package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Saving Web Page HTML via {@link java.nio.file.Files#copy(InputStream, Path, CopyOption...)}
 *
 * <pre>{@code
 * 	java SavePageByCopyDemo https://apress.com
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-35
 * @since 2024/7/5
 */
public class SavePageByCopyDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage java SavePageByCopyDemo url");
			return;
		}

		URL url = new URL(args[0]);
		Files.copy(url.openStream(), Paths.get("page.html"));
	}
}
