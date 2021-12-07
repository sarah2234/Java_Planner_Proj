package database;

import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		DBConnection connection = new DBConnection();
		connection.deleteRoadmap("R4");
	}
}

