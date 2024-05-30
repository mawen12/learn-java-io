package com.mawen.learn.basic.io.book.chapter4;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Refactoring {@link Employee} to support Externalization.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-13
 * @since 2024/5/30
 */
public class ExternalizationDemo {

	private static final String FILE_NAME = "employee.dat";

	public static void main(String[] args) throws IOException {

		try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
			 ObjectOutputStream oos = new ObjectOutputStream(fos);
			 FileInputStream fis = new FileInputStream(FILE_NAME);
			 ObjectInputStream ois = new ObjectInputStream(fis);
		) {

			Employee e = new Employee("mawen", 30);
			oos.writeObject(e);

			e = (Employee) ois.readObject();
			System.out.println(e.getName());
			System.out.println(e.getAge());
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		Files.deleteIfExists(Paths.get(FILE_NAME));
	}

	static class Employee implements Externalizable {

		private String name;
		private int age;

		public Employee() {
			System.out.println("Employee() called");
		}

		public Employee(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public int getAge() {
			return age;
		}

		@Override
		public void writeExternal(ObjectOutput out) throws IOException {
			System.out.println("writeExternal() called");
			out.writeUTF(name);
			out.writeInt(age);
		}

		@Override
		public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
			System.out.println("readExternal() called");
			name = in.readUTF();
			age = in.readInt();
		}
	}
}
