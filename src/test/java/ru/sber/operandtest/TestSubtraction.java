package ru.sber.operandtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

@Title("��������� ������ � ���������� ���������")
public class TestSubtraction {

	private int oper1;
	private int oper2;
	private final String OPERATION = "-";
	private int summFromFile;
	ArrayList<HashMap<String, String>> allStringFromFile;

	@Before
	public void setUp() throws Exception {
		LoadFromFile stringFromFile = new LoadFromFile();
		allStringFromFile = stringFromFile.loadStringForOperation(OPERATION);
	}

	@Title("���� ���������")
	@Test
	public void test() {

		for (int i = 0; i < allStringFromFile.size(); i++) {

			Map<String, String> map = allStringFromFile.get(i);

			summFromFile = Integer.parseInt(map.get("result"));
			oper1 = Integer.parseInt(map.get("oper1"));
			oper2 = Integer.parseInt(map.get("oper2"));

			getString(oper1, oper2, summFromFile);
			int expected = getResult(oper1, oper2);
			validate(expected, summFromFile);
		}
	}

	@Step("��������� ������ {0}" + OPERATION + "{1}={2}")
	public void getString(int oper1, int oper2, int summFromFile) {
		// empty. For Allure report.
	}

	@Step("��������� ������� {0} " + OPERATION + " {1}")
	public int getResult(int oper1, int oper2) {
		int result = oper1 - oper2;
		return result;
	}

	@Step("��������� ��������� = {0} ���������� �� ��������� �� ����� = {1}")
	public void validate(int expectedSumm, int summFromFile) {
		assertEquals("��������� ������ �����������: " + expectedSumm
				+ " �� ������������ " + summFromFile, expectedSumm,
				summFromFile);
	}

}