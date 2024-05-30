package com.mawen.learn.basic.io.book.chapter4;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Serializing and Deserializing an Employee Object
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 4-10
 * @since 2024/5/30
 */
public class SerializationDemo {

	final static String FILE_NAME = "employee.dat";

	public static void main(String[] args) throws IOException {
		try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
			 ObjectOutputStream oos = new ObjectOutputStream(fos);
			 FileInputStream fis = new FileInputStream(FILE_NAME);
			 ObjectInputStream ois = new ObjectInputStream(fis)) {

			// write
			Employee employee = new Employee("John Doe", 36);
			oos.writeObject(employee);

			// read
			Employee e = (Employee) ois.readObject();

			System.out.println(e.getName());
			System.out.println(e.getAge());
		}
		catch (IOException e) {
			System.err.println(e.getMessage());
		}
		catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}

		Files.deleteIfExists(Paths.get(FILE_NAME));
	}
}
