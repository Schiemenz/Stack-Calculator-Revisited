package de.schiemenz.stackcalc.core;

public class StackOperationException extends RuntimeException {

    private static final long serialVersionUID = -148565218415239896L;

    public StackOperationException() {

    }

    public StackOperationException(String msg) {
	super(msg);
    }
}
