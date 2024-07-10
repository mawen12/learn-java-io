package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

/**
 * Watching a Directory for Creations, Deletions, and Modifications
 *
 * <pre>{@code
 * 	java WatchServiceDemo .
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-51
 * @since 2024/7/9
 */
public class WatchServiceDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java WatchServiceDemo directory");
			return;
		}

		FileSystem fsDefault = FileSystems.getDefault();
		WatchService ws = fsDefault.newWatchService();
		Path dir = fsDefault.getPath(args[0]);
		dir.register(ws, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

		while (true) {
			WatchKey key;
			try {
				key = ws.take();
			}
			catch (InterruptedException e) {
				return;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind<?> kind = event.kind();
				if (kind == StandardWatchEventKinds.OVERFLOW) {
					System.out.printf("overflow");
					continue;
				}

				WatchEvent ev = (WatchEvent) event;
				Path filename = (Path) ev.context();
				System.out.printf("%s: %s%n", ev.kind(), filename);
			}

			boolean valid = key.reset();
			if (!valid) {
				break;
			}
		}
	}
}
