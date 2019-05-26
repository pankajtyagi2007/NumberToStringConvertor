package com.virtusa.pankaj;

/**
 * 
 * @author pankaj
 */
public final class NumberToWordConvertor implements Convertor<String, Integer> {
	private static final String INVALID_INPUT = "Invalid Input";

	public static final String[] SINGLE = { "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
			"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
			"nineteen" };

	public static final String[] TENS = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
			"ninety" };

	public String execute(final Integer aInput) {
		int input = aInput;
		if (input == 0) {
			return "zero";
		} else if (input < -999999999 || input > 999999999) {
			throw new IllegalArgumentException(INVALID_INPUT + ". Allowed number are from -999999999 to 999999999.");
		}

		return convert(input);
	}

	public static String convert(final int n) {
		if (n < 0) {
			return "minus " + convert(-n);
		}

		if (n < 20) {
			return SINGLE[n];
		}

		if (n < 100) {
			return TENS[n / 10] + ((n % 10 != 0) ? " " : "") + SINGLE[n % 10];
		}

		if (n < 1000) {
			return SINGLE[n / 100] + " hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
		}

		if (n < 1000000) {
			return convert(n / 1000) + " thousand" + ((n % 10000 != 0) ? " " : "") + convert(n % 1000);
		}

		if (n < 1000000000) {
			return convert(n / 1000000) + " million" + ((n % 1000000 != 0) ? " " : "") + convert(n % 1000000);
		}

		return "";
	}

	public static void main(String[] args) {
		NumberToWordConvertor convertor = new NumberToWordConvertor();
		System.out.println(convertor.execute(1000000000));
		
	}
}