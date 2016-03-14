package ru.sber.operandtest;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LoadFromFile {

	public ArrayList<HashMap<String, String>> loadStringForOperation(
			String operation) {

		String csvFile = "src/test/resources/file.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ";";
		ArrayList<HashMap<String, String>> allStringByOperation = new ArrayList<HashMap<String, String>>();

		try {
			Map<String, String> maps = new HashMap<String, String>();
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);

				if (country[2].equals(operation)) {
					maps.put("oper1", country[0]);
					maps.put("oper2", country[1]);
					maps.put("operation", country[2]);
					maps.put("result", country[3]);
					allStringByOperation.add(new HashMap<String, String>(maps));
					maps.clear();
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return (ArrayList<HashMap<String, String>>) allStringByOperation;
	}
}
