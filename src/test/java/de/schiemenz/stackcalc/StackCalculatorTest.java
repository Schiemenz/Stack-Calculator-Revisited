package de.schiemenz.stackcalc;

import static org.junit.Assert.assertEquals;

import java.util.EmptyStackException;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("static-access")
public class StackCalculatorTest {

    StackCalculator calculator = new StackCalculator();

    @Before
    public void init() {
	calculator.registerOperatorsWithReflectionsAndAnnotations();
	// calculator.registerOperatorsWithReflections();
	// calculator.registerOperators();
    }

    @Test
    public void testSummation() {
	calculator.processInput("2");
	calculator.processInput("3");
	calculator.processInput("2.9999999");
	calculator.processInput("sum");

	assertEquals(5, calculator.getTop(), 0.00001);
    }

    @Test
    public void testAdditionSubtractionSqrt() {
	calculator.clearStack();
	calculator.processInput("12");
	calculator.processInput("1");
	calculator.processInput("3");
	calculator.processInput("+");
	calculator.processInput("-");
	calculator.processInput("sqrt");

	assertEquals(2.8284271, calculator.getTop(), 0.00001);
    }

    @Test(expected = EmptyStackException.class)
    public void testAdditionSubtractionWithErrors() {
	calculator.clearStack();
	calculator.processInput("1");
	calculator.processInput("+");
	// assertEquals(1, calculator.getTop(), 0.00001);

	calculator.clearStack();
	calculator.processInput("1");
	calculator.processInput("-");
	// assertEquals(0, calculator.getTop(), 0.00001);
    }

    @Test
    public void testArithmeticMean() {
	calculator.clearStack();
	calculator.processInput("12");
	calculator.processInput("11");
	calculator.processInput("1");
	calculator.processInput("3");
	calculator.processInput("avg");

	assertEquals(8, calculator.getTop(), 0.00001);
    }

    @Test
    public void testArithmeticMeanWithIllegalArgument() {
	calculator.clearStack();
	calculator.processInput("11");
	calculator.processInput("1");
	calculator.processInput("666");
	// should throw an exception here
	calculator.processInput("avg");

	assertEquals(666, calculator.getTop(), 0.00001);
    }

    @Test
    public void testArithmeticMeanWithDivisionByZero() {
	calculator.clearStack();
	calculator.processInput("11");
	calculator.processInput("1");
	calculator.processInput("0");
	// should throw an exception here
	calculator.processInput("avg");

	assertEquals(0, calculator.getTop(), 0.00001);
    }

    @Test
    public void testDivisionWithDivisionByZero() {
	calculator.clearStack();
	calculator.processInput("1");
	calculator.processInput("0");
	// it's expected to throw an exception
	calculator.processInput("/");

	assertEquals(0, calculator.getTop(), 0.00001);
    }

    @Test
    public void testDivision() {
	calculator.clearStack();
	calculator.processInput("12");
	calculator.processInput("11");
	calculator.processInput("2");
	calculator.processInput("/");

	assertEquals(5.5, calculator.getTop(), 0.00001);
    }

    @Test
    public void testPiAndHelperMethods() {
	calculator.clearStack();
	calculator.processInput("pi");
	calculator.processInput("pi");
	calculator.processInput("pi");

	assertEquals(3, calculator.getStackSize());
	assertEquals(3 * Math.PI, calculator.getStackValue(), 0.00001);
    }
}
