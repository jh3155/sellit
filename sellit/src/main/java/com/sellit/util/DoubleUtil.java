package com.sellit.util;

public class DoubleUtil {

	public static Double truncate(Double value) {
		return Math.round(value * 100.0) / 100.0;
	}

}
