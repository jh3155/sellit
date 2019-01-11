package com.sellit.util;

import java.text.DecimalFormat;

public class DoubleUtil {

	private static final DecimalFormat decimalFormat = new DecimalFormat("#.00");

	public static Double truncate(Double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	public static String formatDouble(Double value) {
		return decimalFormat.format(value);
	}

}
