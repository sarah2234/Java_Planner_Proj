package database;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		
		DBConnection connection = new DBConnection();
		String[] array = connection.bringRoadmap("R1");
		System.out.println(array[0]);
		System.out.println(array[1]);
		System.out.println(array[2]);
		System.out.println(array[3]);
	}
}
