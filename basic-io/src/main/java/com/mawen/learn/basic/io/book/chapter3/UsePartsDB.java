package com.mawen.learn.basic.io.book.chapter3;

import java.io.IOException;

/**
 * Experimenting with the Parts Flat File Database
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 3-2
 * @since 2024/5/28
 */
public class UsePartsDB {

	public static void main(String[] args) {
		PartsDB db = null;

		try {
			db = new PartsDB("parts.db");
			if (db.numRecs() == 0) {
				// Populate the database with records
				db.append("1-9009-3323-4x", "Wiper Blade Micro Edge", 30,
						2468);
				db.append("1-3233-44923-7j", "Parking Brake Cable", 5, 1439);
				db.append("2-3399-6693-2m", "Halogen Bulb H4 55/60W", 22, 813);
				db.append("2-599-2029-6k", "Turbo Oil Line O-Ring ", 26, 155);
				db.append("3-1299-3299-9u", "Air Pump Electric", 9, 20200);
			}
			dumpRecords(db);
			db.update(1, "1-3233-44923-7j", "Parking Brake Cable", 4, 1995);
			dumpRecords(db);
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}
		finally {
			if (db != null) {
				db.close();
			}
		}
	}

	static void dumpRecords(PartsDB db) throws IOException {
		for (int i = 0; i < db.numRecs(); i++) {
			PartsDB.Part part = db.select(i);
			System.out.print(format(part.getPartNum(), PartsDB.PNUM_LEN, true));
			System.out.print(" | ");
			System.out.print(format(part.getDesc(), PartsDB.DESC_LEN, true));
			System.out.print(" | ");
			System.out.print(format("" + part.getQty(), 10, false));
			System.out.print(" | ");
			String s = part.getCost() / 100 + "." + part.getCost() % 100;
			if (s.charAt(s.length() - 2) == '.') {
				s += "0";
			}
			System.out.println(format(s, 10, false));
		}
		System.out.println("Number of records = " + db.numRecs());
		System.out.println();
	}

	static String format(String value, int maxWidth, boolean leftAlign) {
		StringBuffer sb = new StringBuffer();
		int len = value.length();

		if (len > maxWidth) {
			len = maxWidth;
			value = value.substring(0, len);
		}

		if (leftAlign) {
			sb.append(value);
			for (int i = 0, dest = maxWidth - len; i < dest; i++) {
				sb.append(" ");
			}
		}
		else {
			for (int i = 0, dest = maxWidth - len; i < dest; i++) {
				sb.append(" ");
			}
			sb.append(value);
		}

		return sb.toString();
	}
}
