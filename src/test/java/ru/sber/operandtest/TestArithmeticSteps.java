package ru.sber.operandtest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ru.yandex.qatools.allure.annotations.Step;

public abstract class TestArithmeticSteps {

	private int oper1;
	private int oper2;
	private int summFromFile;
	ArrayList<HashMap<String, String>> allStringFromFile;
	final String OPERATION = "";

	@Step("Получаем данные и запускаем проверку...")
	public void start() {
		for (int i = 0; i < allStringFromFile.size(); i++) {

			Map<String, String> map = allStringFromFile.get(i);

			summFromFile = Integer.parseInt(map.get("result"));
			oper1 = Integer.parseInt(map.get("oper1"));
			oper2 = Integer.parseInt(map.get("oper2"));

			getString(oper1, oper2, summFromFile);
			int expectedSumm = getResult(oper1, oper2);
			validate(expectedSumm, summFromFile);
		}
	}

	@Step("Проверяем строку {0}" + OPERATION + "{1}={2}")
	abstract public void getString(int oper1, int oper2, int summFromFile);

	// empty. For Allure report.

	@Step("Вычисляем результ {0} " + OPERATION + " {1}")
	abstract public int getResult(int oper1, int oper2);

	@Step("Ожидаемый результат = {0} сравниваем со значением из файла = {1}")
	public void validate(int expectedSumm, int summFromFile) {
		assertEquals("Сравнение прошло некорректно: " + expectedSumm
				+ " не соответсвует " + summFromFile, expectedSumm,
				summFromFile);
	}
}
