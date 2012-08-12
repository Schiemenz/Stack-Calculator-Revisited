package de.schiemenz.stackcalc.math;

import java.util.Stack;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperator;

@Operation
public class StackSquare extends StackOperator {

    public StackSquare() {
    }

    @Override
    public Stack<Double> getResult(Stack<Double> stack) {
	double operand = (Double) stack.pop();
	stack.push(Math.pow(operand, 2.0d));

	return stack;
    }

    @Override
    public String toString() {
	return new String("^2");
    }
}
