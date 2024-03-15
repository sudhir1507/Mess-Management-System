package org.mess.helper;

import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Properties;

public class PathHelper {
	// public static final String
	public static final String filePath="C:\\Users\\ACER\\eclipse-workspace\\WeekMenu\\menu.csv";
	public static final String username="admin";
	public static final String password="pass";
	public static String complatePath = "";
	public static Properties p = new Properties();

	public PathHelper() {
		try {
			Path currentDirectoryPath = FileSystems.getDefault().getPath("");
			String currentDirectoryName = currentDirectoryPath.toAbsolutePath().toString();
			complatePath = currentDirectoryName + "\\src\\Resource\\db.properties";
			FileInputStream finf = new FileInputStream(complatePath);
			p.load(finf);
		} catch (Exception e) {
			System.out.println("Error is " + e);
		}

	}
}
