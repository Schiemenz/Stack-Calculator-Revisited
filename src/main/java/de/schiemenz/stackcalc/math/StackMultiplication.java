package de.schiemenz.stackcalc.math;

import java.util.Stack;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperator;

@Operation
public class StackMultiplication extends StackOperator {

    public StackMultiplication() {
    }

    @Override
    public Stack<Double> getResult(Stack<Double> stack) {
	double operandA = (Double) stack.pop();
	double operandB = (Double) stack.pop();
	stack.push(operandA * operandB);

	return stack;
    }

    @Override
    public String toString() {
	return new String("*");
    }
}
