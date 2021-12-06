package database;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		
		DBConnection connection = new DBConnection();
		System.out.println(connection.completeRatio("P6", 2021, 10, 20, 2022, 3, 10));
	}
}
