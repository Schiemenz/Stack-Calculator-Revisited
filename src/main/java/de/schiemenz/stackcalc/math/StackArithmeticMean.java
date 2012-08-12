package de.schiemenz.stackcalc.math;

import java.util.Stack;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperationException;
import de.schiemenz.stackcalc.core.StackOperator;

@Operation
public class StackArithmeticMean extends StackOperator {

    public StackArithmeticMean() {
    }

    @Override
    public Stack<Double> getResult(Stack<Double> stack) {
	int count = stack.pop().intValue();

	if (count > stack.size()) {
	    throw new StackOperationException("Stack is too small for the requested operation.");
	}

	if (count == 0) {
	    throw new StackOperationException("Division by zero is not defined.");
	}

	double sum = 0.0d;

	for (int i = 0; i < count; i++) {
	    sum += stack.pop();
	}

	stack.push(sum / count);

	return stack;
    }

    @Override
    public String toString() {
	return new String("avg");
    }
}
