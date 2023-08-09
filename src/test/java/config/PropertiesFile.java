package config;

import java.io.FileInputStream;

import java.io.InputStream;
import java.util.Properties;



public class PropertiesFile {
	private static Properties prop = new Properties();

	public static String getProperty(String pro) {

		String valueProperty = null;
		try (InputStream input = new FileInputStream("./src/test/java/config/config.properties")) {

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			valueProperty = prop.getProperty(pro);
			System.out.println(valueProperty);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return valueProperty;
	}

}
