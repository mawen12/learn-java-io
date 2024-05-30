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
 * Solving Problematic Deserialization
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-12
 * @since 2024/5/30
 */
public class NonProblematicSerializationDemo {

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

		public Employee(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}

	static class SerEmployee implements Serializable {

		private Employee emp;

		private String name;

		public SerEmployee(String name) {
			this.name = name;
			this.emp = new Employee(name);
		}

		private void writeObject(ObjectOutputStream out) throws IOException {
			out.writeUTF(name);
		}

		private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
			name = in.readUTF();
			emp = new Employee(name);
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
