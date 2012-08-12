package de.schiemenz.stackcalc.math;

import java.util.Stack;

import de.schiemenz.stackcalc.core.Operation;
import de.schiemenz.stackcalc.core.StackOperator;

@Operation(author = "Frank Schiemenz")
public class StackPi extends StackOperator {

    public StackPi() {
    }

    @Override
    public Stack<Double> getResult(Stack<Double> stack) {
	stack.push(Math.PI);

	return stack;
    }

    @Override
    public String toString() {
	return new String("pi");
    }
}
