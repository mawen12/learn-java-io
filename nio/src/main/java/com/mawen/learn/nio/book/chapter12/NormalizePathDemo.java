package com.mawen.learn.nio.book.chapter12;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Normalizing, Relativizing, and Resolving Paths
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-5
 * @since 2024/6/21
 */
public class NormalizePathDemo {

	public static void main(String[] args) {
		Path path = Paths.get("reports", ".", "2015", "jan");
		System.out.println(path);
		System.out.println(path.normalize());

		path =Paths.get("reports", "2015", "..", "jan");
		System.out.println(path.normalize());
		System.out.println();

		path = Paths.get("reports", "2015", "jan");
		System.out.println(path);
		System.out.println(path.relativize(Paths.get("reports", "2016", "mar")));

		try {
			Path root = FileSystems.getDefault().getRootDirectories().iterator().next();

			if (root != null) {
				System.out.printf("Root: %s%n", root);
				Path path1 = Paths.get(root.toString(), "reports", "2016", "mar");

				System.out.printf("Path: %s%n", path1);
				System.out.println(path1.relativize(path1));
			}
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		System.out.println();
		path = Paths.get("reports", "2015");
		System.out.println(path);
		System.out.println(path.resolve("apr"));

		System.out.println();
		Path path2 = Paths.get("reports", "2015", "jan");
		System.out.println(path2);
		System.out.println(path2.getParent());
		System.out.println(path2.resolveSibling(Paths.get("mar")));
		System.out.println(path2.resolve(Paths.get("mar")));
	}

}
