package lf2.flap.models.entity;

public class Transition {
	private String value;
	private State startState, endState;
	
	public Transition(String value, State start, State end) {
		this.value = value;
		this.startState = start;
		this.endState = end;
	}
	
	public Transition(String value, State state) {
		this.value = value;
		this.startState = state;
		this.endState = state;
	}
	
	public boolean isRecursive() {
		return this.endState == this.startState;
	}
	
	public String getValue() {
		return value;
	}
	
	public State getStartState() {
		return startState;
	}
	
	public State getEndState() {
		return endState;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
