package com.mawen.learn.basic.io.book.chapter4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Unscrambling a File's Bytes.
 *
 * <pre>{@code
 * 	java Unscramble xanadu-random.txt xanadu-random.bak
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-7
 * @since 2024/5/29
 */
public class Unscramble {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("usage: java Unscramble scrpath destpath");
			return;
		}

		try (FileInputStream fis = new FileInputStream(args[0]);
			 ScrambledInputStream sis = new ScrambledInputStream(fis, makeMap());
			 FileOutputStream fos = new FileOutputStream(args[1]);
		) {
			int b;
			while ((b = sis.read()) != -1) {
				fos.write(b);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	static int[] makeMap() {
		int[] map = new int[256];
		int length = map.length;
		for (int i = 0; i < length; i++) {
			map[i] = i;
		}
		// Shuffle map
		Random r = new Random(0);
		for (int i = 0; i < length; i++) {
			int n = r.nextInt(length);
			int temp = map[i];
			map[i] = map[n];
			map[n] = temp;
		}
		return map;
	}
}
