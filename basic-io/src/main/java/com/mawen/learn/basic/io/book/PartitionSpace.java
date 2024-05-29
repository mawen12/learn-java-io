package com.mawen.learn.basic.io.book;

import java.io.File;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/20
 */
public class PartitionSpace {

	public static void main(String[] args) {

		File[] roots = File.listRoots();
		for (File root : roots) {
			System.out.println("Partition: " + root);
			System.out.println("Free space on this partition = " + root.getFreeSpace());
			System.out.println("Usable space on this partition = " + root.getUsableSpace());
			System.out.println("Total space on this partition = " + root.getTotalSpace());
			System.out.println("***");
		}
	}
}
