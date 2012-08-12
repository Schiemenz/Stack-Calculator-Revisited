package de.schiemenz.stackcalc;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import org.reflections.Reflections;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperationException;
import de.schiemenz.stackcalc.core.StackOperator;
import de.schiemenz.stackcalc.math.StackAddition;
import de.schiemenz.stackcalc.math.StackArithmeticMean;
import de.schiemenz.stackcalc.math.StackDivision;
import de.schiemenz.stackcalc.math.StackMultiplication;
import de.schiemenz.stackcalc.math.StackPower;
import de.schiemenz.stackcalc.math.StackSquare;
import de.schiemenz.stackcalc.math.StackSquareRoot;
import de.schiemenz.stackcalc.math.StackSubtraction;
import de.schiemenz.stackcalc.math.StackSummation;

public class StackCalculator {

    private static Stack<Double> m_stack = new Stack<Double>();
    private static Map<String, StackOperator> m_operators = new HashMap<String, StackOperator>();

    /**
     * Main
     */
    public static void main(String[] args) {

	/* using Reflections to scan for additional operators */
	// registerOperatorsWithReflections();
	/* using Reflections to scan for additional annotated operators */
	registerOperatorsWithReflectionsAndAnnotations();
	/* Deprecated: the manual way */
	// registerOperators();

	Scanner scanner = new Scanner(System.in);

	String next;

	while ((next = scanner.nextLine()) != null) {
	    if ("quit".equals(next)) {
		break;
	    } else {
		processInput(next);
		printStack();
	    }
	}

	scanner.close();

	System.exit(0);
    }

    /**
     * Parses user input and makes use of the stack operators accordingly
     */
    @SuppressWarnings("unchecked")
    public static void processInput(String input) {

	if (m_operators.containsKey(input)) {
	    Stack<Double> tmp = (Stack<Double>) m_stack.clone();

	    try {
		m_stack = m_operators.get(input).getResult(m_stack);

	    } catch (StackOperationException e) { // testing the @expects
						  // annotation in jUnit -
						  // change to 'Exception' if
						  // needed
		/*
		 * System.out.println("Operand '" +
		 * m_operators.get(input).toString() + "' threw exception: " +
		 * e);
		 */
		m_stack = tmp;
	    }
	} else {
	    try {
		NumberFormat nf = NumberFormat.getInstance(Locale.ENGLISH);

		double myNumber = nf.parse(input).doubleValue();

		m_stack.push(myNumber);
	    } catch (NumberFormatException e) {
	    } catch (ParseException e) {
	    }
	}
    }

    /**
     * DEPRICATED Registers the mathematical functions - can be used to include
     * external stack operators.
     */
    public static void registerOperators() {
	m_operators.put(new StackAddition().toString(), new StackAddition());
	m_operators.put(new StackSubtraction().toString(), new StackSubtraction());
	m_operators.put(new StackMultiplication().toString(), new StackMultiplication());
	m_operators.put(new StackDivision().toString(), new StackDivision());
	m_operators.put(new StackPower().toString(), new StackPower());
	m_operators.put(new StackSquare().toString(), new StackSquare());
	m_operators.put(new StackSquareRoot().toString(), new StackSquareRoot());
	m_operators.put(new StackSummation().toString(), new StackSummation());
	m_operators.put(new StackArithmeticMean().toString(), new StackArithmeticMean());
    }

    /**
     * Registers the mathematical functions via Reflections
     * http://code.google.com/p/reflections/
     * 
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void registerOperatorsWithReflections() {

	try {
	    Reflections reflections = new Reflections("de.schiemenz.stackcalc");

	    // sub-types
	    Set<Class<? extends StackOperator>> operations = reflections.getSubTypesOf(StackOperator.class);

	    for (Class<? extends StackOperator> op : operations) {

		// System.out.println("Found operator: " +
		// op.newInstance().toString());
		m_operators.put(op.newInstance().toString(), op.newInstance());

	    }
	} catch (InstantiationException ie) {
	} catch (IllegalAccessException iae) {
	}
    }

    /**
     * Registers the mathematical functions via Reflections and @Operation
     * 
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static void registerOperatorsWithReflectionsAndAnnotations() {

	try {
	    Reflections reflections = new Reflections("de.schiemenz.stackcalc");

	    // annotated
	    Set<Class<?>> operations = reflections.getTypesAnnotatedWith(Operation.class);

	    for (Class<?> op : operations) {

		m_operators.put(op.newInstance().toString(), (StackOperator) op.newInstance());

		// Operation annotation =
		// op.newInstance().getClass().getAnnotation(Operation.class);

		// System.out.println("Found operator '" +
		// op.newInstance().toString() + "' from " +
		// op.getAnnotation(Operation.class).author() );

	    }
	} catch (InstantiationException ie) {
	} catch (IllegalAccessException iae) {
	}
    }

    /**
     * Returns the top element of the stack
     */
    public static double getTop() {
	try {
	    return m_stack.peek();
	} catch (EmptyStackException e) {
	    return 0.0d;
	}
    }

    /**
     * Returns the element count of the stack
     */
    public static int getStackSize() {
	return m_stack.size();
    }

    /**
     * Returns the element count of the stack
     */
    public static double getStackValue() {

	double stackValue = 0.0d;

	for (int index = 0; index < m_stack.size(); index++) {
	    stackValue += m_stack.elementAt(index);
	}

	return stackValue;
    }

    /**
     * Removes all items from the stack
     */
    public static void clearStack() {
	m_stack.clear();
    }

    /**
     * Prints the stack to console
     */
    public static void printStack() {
	System.out.println("Stack " + m_stack.toString());
    }
}