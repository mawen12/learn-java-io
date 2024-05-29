package com.mawen.learn.basic.io.book.chapter4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * Scrambling a File's Bytes.
 *
 * <pre>{@code
 * 	java Scramble xanadu.txt xanadu-random.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-5
 * @since 2024/5/29
 */
public class Scramble {

	public static void main(String[] args) {

		if (args.length != 2) {
			System.err.println("usage: java Scramble srcpath destpath");
			return;
		}

		try (FileInputStream fis = new FileInputStream(args[0]);
			 FileOutputStream fos = new FileOutputStream(args[1]);
			 ScrambledOutputStream sos = new ScrambledOutputStream(fos, makeMap())) {
			int b;
			while ((b = fis.read()) != -1) {
				sos.write(b);
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
