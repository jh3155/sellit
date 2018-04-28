package com.sellit.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import javafx.scene.control.TextField;

public class ValidateUtil {

	public static final String BIG_DECIMAL_REGEX = "^[0-9]*\\.[0-9]{2}$";
	public static final Pattern bigDecimalPattern;

	public static final String INT_REGEX = "^[0-9]*$";
	public static final Pattern intPattern;

	static {
		bigDecimalPattern = Pattern.compile(BIG_DECIMAL_REGEX);
		intPattern = Pattern.compile(INT_REGEX);
	}

	public static boolean blankText(TextField textField) {

		if (StringUtils.isBlank(textField.getText())) {
			return true;
		}

		return false;

	}

	public static boolean invalidLength(TextField textField, int maxLength) {

		if (StringUtils.length(textField.getText()) > maxLength) {
			return true;
		}

		return false;

	}

	public static boolean blankTextOrInvalidLength(TextField textField, int maxLength) {

		if (blankText(textField)) {
			return true;
		} else if (invalidLength(textField, maxLength)) {
			return true;
		}

		return false;

	}

	public static boolean invalidBigDecimal(TextField textField) {

		if (blankText(textField)) {
			return true;
		}

		Matcher matcher = bigDecimalPattern.matcher(textField.getText());

		if (!matcher.matches()) {
			return true;
		}

		return false;

	}

	public static boolean invalidInteger(TextField textField) {

		if (blankText(textField)) {
			return true;
		}

		Matcher matcher = intPattern.matcher(textField.getText());

		if (!matcher.matches()) {
			return true;
		}

		return false;

	}

}
