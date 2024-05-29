package com.mawen.learn.basic.io.book.chapter3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Implementing the Parts Flat File Database.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 3-1
 * @since 2024/5/28
 */
public class PartsDB {

	public static final int PNUM_LEN = 20;
	public static final int DESC_LEN = 30;
	public static final int QUAN_LEN = 4;
	public static final int COST_LEN = 4;

	/**
	 * Return the byte length of one record.
	 *
	 * <p>one partNum use a character that occupy two bytes in the file.
	 * one partDesc use a character that occupy two bytes in the file.
	 * qty use an int that occupy four bytes in the file.
	 * cost use an int that occupy four bytes in the file.
	 *
	 * <p>the {@link String#length()} return the length of char array. when compute bytes length,
	 * it should multiply by 2.
	 */
	private static final int REC_LEN = 2 * PNUM_LEN + 2 * DESC_LEN + QUAN_LEN + COST_LEN;

	private RandomAccessFile raf;

	public PartsDB(String path) throws FileNotFoundException {
		this.raf = new RandomAccessFile(path, "rw");
	}

	/**
	 * append a record to the file
	 */
	public void append(String partNum, String partDesc, int qty, int cost) throws IOException {
		raf.seek(raf.length());
		write(partNum, partDesc, qty, cost);
	}

	/**
	 * return the number of records in the file
	 */
	public int numRecs() throws IOException {
		return (int) raf.length() / REC_LEN;
	}

	/**
	 * select and return a specific record
	 */
	public Part select(int recno) throws IOException {
		if (recno < 0 || recno > numRecs()) {
			throw new IllegalArgumentException(recno + " out of range");
		}

		raf.seek(recno * REC_LEN);
		return read();
	}

	/**
	 * update a specific record
	 */
	public void update(int recno, String partNum, String partDesc, int qty, int cost) throws IOException {
		if (recno < 0 || recno > numRecs()) {
			throw new IllegalArgumentException(recno + " out of range");
		}

		raf.seek(recno * REC_LEN);
		write(partNum, partDesc, qty, cost);
	}

	/**
	 * close the file
	 */
	public void close() {
		try {
			raf.close();
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}

	private Part read() throws IOException {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < PNUM_LEN; i++) {
			sb.append(raf.readChar());
		}
		// remove the padding before saving a String-based field value in the Part Object
		String partNum = sb.toString().trim();

		sb.setLength(0);
		for (int i = 0; i < DESC_LEN; i++) {
			sb.append(raf.readChar());
		}
		// remove the padding before saving a String-based field value in the Part Object
		String partDesc = sb.toString().trim();

		int qty = raf.readInt();
		int cost = raf.readInt();

		return new Part(partNum, partDesc, qty, cost);
	}

	private void write(String partNum, String partDesc, int qty, int cost) throws IOException {
		StringBuffer sb = new StringBuffer(partNum);
		if (sb.length() > PNUM_LEN) {
			sb.setLength(PNUM_LEN);
		}
		else if (sb.length() < PNUM_LEN) {
			int len = PNUM_LEN - sb.length();
			for (int i = 0; i < len; i++) {
				// pads String-based values that are shorter than a field size with spaces on the right
				sb.append(" ");
			}
		}
		raf.writeChars(sb.toString());

		sb = new StringBuffer(partDesc);
		if (sb.length() > DESC_LEN) {
			sb.setLength(DESC_LEN);
		}
		else if (sb.length() < DESC_LEN) {
			int len = DESC_LEN - sb.length();
			for (int i = 0; i < len; i++) {
				// pads String-based values that are shorter than a field size with spaces on the right
				sb.append(" ");
			}
		}
		raf.writeChars(sb.toString());

		raf.writeInt(qty);
		raf.writeInt(cost);
	}


	public static class Part {
		private String partNum;
		private String desc;
		private int qty;
		private int cost;

		public Part(String partNum, String desc, int qty, int cost) {
			this.partNum = partNum;
			this.desc = desc;
			this.qty = qty;
			this.cost = cost;
		}

		public String getPartNum() {
			return partNum;
		}

		public String getDesc() {
			return desc;
		}

		public int getQty() {
			return qty;
		}

		public int getCost() {
			return cost;
		}
	}
}
