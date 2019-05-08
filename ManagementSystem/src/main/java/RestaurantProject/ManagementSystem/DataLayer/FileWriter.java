package RestaurantProject.ManagementSystem.DataLayer;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class FileWriter {

	public Writer createFile(String fileName) {
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			System.out.println("Error createFile1!");
		} catch (FileNotFoundException e) {
			System.out.println("Error createFile2!");
		}
		return writer;
	}

	public void closeFile(Writer writer) {
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println("Error closing a file!");
		}
	}

}
