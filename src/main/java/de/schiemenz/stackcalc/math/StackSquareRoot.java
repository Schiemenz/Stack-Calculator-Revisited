package de.schiemenz.stackcalc.math;

import java.util.Stack;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperator;

@Operation
public class StackSquareRoot extends StackOperator {

    public StackSquareRoot() {
    }

    @Override
    public Stack<Double> getResult(Stack<Double> stack) {
	double radicand = (Double) stack.pop();
	double squareRoot = Math.sqrt(radicand);

	if (Double.isNaN(squareRoot)) {
	    throw new ArithmeticException();
	} else {
	    stack.push(squareRoot);
	    return stack;
	}
    }

    @Override
    public String toString() {
	return new String("sqrt");
    }
}
