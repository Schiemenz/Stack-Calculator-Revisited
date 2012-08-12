package de.schiemenz.stackcalc.math;

import java.util.Stack;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperationException;
import de.schiemenz.stackcalc.core.StackOperator;

@Operation
public class StackDivision extends StackOperator {

    public StackDivision() {
    }

    @Override
    public Stack<Double> getResult(Stack<Double> stack) {
	double operandA = stack.pop();
	double operandB = stack.pop();

	if ((int) operandA == 0) {
	    throw new StackOperationException("Division by zero is not defined.");
	}

	stack.push(operandB / operandA);

	return stack;
    }

    @Override
    public String toString() {
	return new String("/");
    }
}
