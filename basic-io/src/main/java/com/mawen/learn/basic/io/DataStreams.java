package com.mawen.learn.basic.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

/**
 * Data streams support binary I/O of primitive data type values
 * (boolean, char, byte, short, int, long, float and double) as well as String values.
 * Data streams detects an end-of-file condition by catching {@link EOFException},
 * instead of testing for an invalid return value.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/datastreams.html">datastreams</a>
 * @since 2024/5/10
 */
public class DataStreams {

	static final String dataFile = "invoicedata.txt";

	static final double[] prices = {19.99, 9.99, 15.99, 3.99, 4.99};
	static final int[] units = {12, 8, 13, 29, 50};
	static final String[] descs = {
			"Java T-shirt",
			"Java Mug",
			"Duke Juggling Dolls",
			"Java Pin",
			"Java Key Chain"
	};

	public static void main(String[] args) throws IOException {

		DataOutputStream out = null;
		DataInputStream in = null;

		try {
			out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));

			for (int i = 0; i < prices.length; i++) {
				out.writeDouble(prices[i]);
				out.writeInt(units[i]);
				out.writeUTF(descs[i]);
			}
		}
		finally {
			if (out != null) {
				out.close();
			}
		}

		double price;
		int unit;
		String desc;
		double total = 0.0;

		try {
			in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));

			while (true) {
				price = in.readDouble();
				unit = in.readInt();
				desc = in.readUTF();
				System.out.format("You ordered %d" + " units of %s at $%.2f%n", unit, desc, price);
				total += unit * price;
			}
		}
		catch (EOFException e) {
			System.out.println(total);

			Files.deleteIfExists(new File(dataFile).toPath());
		}
		finally {
			if (in != null) {
				in.close();
			}
		}
	}

}
