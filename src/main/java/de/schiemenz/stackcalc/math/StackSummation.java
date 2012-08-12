package de.schiemenz.stackcalc.math;

import java.util.Stack;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperator;

@Operation
public class StackSummation extends StackOperator {

    public StackSummation() {
    }

    @Override
    public Stack<Double> getResult(Stack<Double> stack) {
	int count = stack.pop().intValue();
	double sum = 0.0d;

	for (int i = 0; i < count; i++) {
	    sum += stack.pop();
	}

	stack.push(sum);

	return stack;
    }

    @Override
    public String toString() {
	return new String("sum");
    }
}
