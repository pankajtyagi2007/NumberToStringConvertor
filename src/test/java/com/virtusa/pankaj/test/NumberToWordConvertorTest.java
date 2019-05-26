package com.virtusa.pankaj.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.virtusa.pankaj.Convertor;
import com.virtusa.pankaj.NumberToWordConvertor;

@RunWith(value = Parameterized.class)
public class NumberToWordConvertorTest {
	private static Convertor<String, Integer> convetor;
	private final int input;
	private final String expected;
	private Class errorClass;
	
	@Rule
    public ExpectedException exception = ExpectedException.none();

	@BeforeClass
	public static void initConverter() {
		convetor = new NumberToWordConvertor();
	}

	@Before
	public void beforeEachTest() {
		System.out.println("NumberToWordConvertorTest Started");
	}

	@After
	public void afterEachTest() {
		System.out.println("NumberToWordConvertorTest completed");
	}

	public NumberToWordConvertorTest(final int input, final String expected, final Class errorClass) {
		this.input = input;
		this.expected = expected;
		this.errorClass = errorClass;
	}

	@Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][] { { 0, "zero" , null}, { 1, "one" , null}, { 10, "ten" , null}, { 15, "fifteen" , null},
				{ 101, "one hundred one" , null}, { 2715, "two thousand seven hundred fifteen" , null},
				{ 35648, "thirty five thousand six hundred forty eight" , null},
				{ 999999, "nine hundred ninety nine thousand nine hundred ninety nine" , null},
				{ 9999999, "nine million nine hundred ninety nine thousand nine hundred ninety nine" , null},
				{ 10000000, "ten million" , null}, { 100000000, "one hundred million" , null},
				{ 999999999,
						"nine hundred ninety nine million nine hundred ninety nine thousand nine hundred ninety nine" , null},
				{ -35648, "minus thirty five thousand six hundred forty eight" , null},
				{ 1000000000, "",IllegalArgumentException.class }, { -1000000000, "",IllegalArgumentException.class } });

	}

	@Test
	public void test() {
		 if (errorClass != null) {
             exception.expect(IllegalArgumentException.class);
        }
		assertEquals(expected, convetor.execute(input));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testPrintMessage() {
		convetor.execute(1000000000);
	}

}
