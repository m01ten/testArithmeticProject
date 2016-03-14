package ru.sber.operandtest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

@Title("��������� ������ � ���������� �������")
public class TestDiv {

	private int oper1;
	private int oper2;
	private final String OPERATION = "/";
	private double summFromFile;
	ArrayList<HashMap<String, String>> allStringFromFile;

	@Before
	public void setUp() throws Exception {
		LoadFromFile stringFromFile = new LoadFromFile();
		allStringFromFile = stringFromFile.loadStringForOperation(OPERATION);
	}

	@Title("���� �������")
	@Test
	public void test() {

		for (int i = 0; i < allStringFromFile.size(); i++) {

			Map<String, String> map = allStringFromFile.get(i);

			summFromFile = Double.parseDouble(map.get("result"));
			oper1 = Integer.parseInt(map.get("oper1"));
			oper2 = Integer.parseInt(map.get("oper2"));

			getString(oper1, oper2, summFromFile);
			double expectedSumm = getResult(oper1, oper2);
			validate(expectedSumm, summFromFile);
		}
	}

	@Step("��������� ������ {0}" + OPERATION + "{1}={2}")
	public void getString(int oper1, int oper2, double summFromFile) {
		// empty. For Allure report.
	}

	@Step("��������� ������� {0} " + OPERATION + " {1}")
	public double getResult(double oper1, double oper2) {
		assertFalse("������� �� 0.", oper2 == 0);
		double result = oper1 / oper2;
		return result;
	}

	@Step("��������� ��������� = {0} ���������� �� ��������� �� ����� = {1}")
	public void validate(double expectedSumm, double summFromFile) {
		assertTrue("��������� ������ �����������: " + expectedSumm
				+ " �� ������������ " + summFromFile,
				(Double.compare(expectedSumm, summFromFile) == 0));
	}
}