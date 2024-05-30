package com.mawen.learn.basic.io.book.chapter4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Problematic Deserialization.
 *
 * <p>Although you can serialize subclass objects, you cannot deserialize these serialized objects
 * when the superclass doesn't declare a noargument constructor.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-11
 * @since 2024/5/30
 */
public class ProblematicSerializationDemo {

	private static final String FILE_NAME = "employee.dat";

	public static void main(String[] args) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
			 ObjectOutputStream oos = new ObjectOutputStream(fos);
			 FileInputStream fis = new FileInputStream(FILE_NAME);
			 ObjectInputStream ois = new ObjectInputStream(fis)) {

			SerEmployee e = new SerEmployee("John Doe");
			System.out.println(e);
			oos.writeObject(e);

			System.out.println("se object written to file");
			e = (SerEmployee) ois.readObject();
			System.out.println("se object read from file");
			System.out.println(e);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Files.deleteIfExists(Paths.get(FILE_NAME));
	}

	static class Employee {

		private String name;

		Employee(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	static class SerEmployee extends Employee implements Serializable {

		SerEmployee(String name) {
			super(name);
		}
	}
}
