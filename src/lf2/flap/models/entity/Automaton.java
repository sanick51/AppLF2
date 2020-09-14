package lf2.flap.models.entity;

import java.util.ArrayList;
import java.util.List;

public class Automaton {
	private List<State> states;
	private List<Transition> transitions;
	protected State initialState;

	public Automaton() {
		this.states = new ArrayList<State>();
		this.transitions = new ArrayList<Transition>();
	}

	public void addState(State state) {
		this.states.add(state);
	}
	
	public void addTransition(Transition t){
		boolean exists = false;
		if (t.startState == t.endState) {
			for (Transition tt : t.startState.selfTransitions) {
				if (tt.value.contentEquals(t.value) && tt.endState == t.endState) {
					exists = true;
					break;
				}
			}

			if (!exists) {
				t.startState.selfTransitions.add(t);
				this.transitions.add(t);
			}
		} else {
			for (Transition tt : t.startState.outTransitions) {
				if (tt.value.contentEquals(t.value) && tt.endState == t.endState) {
					exists = true;
					break;
				}
			}

			if (!exists) {
				t.startState.outTransitions.add(t);
				t.endState.inTransitions.add(t);
				this.transitions.add(t);
			}
		}
	}

	public void createTransition(String value, State start, State end) {
		if (start == end)
			this.createTransition(value, start);
		else {
			boolean exists = false;

			for (Transition t : start.outTransitions) {
				if (t.value.contentEquals(value) && t.endState == end) {
					exists = true;
					break;
				}
			}

			if (!exists) {
				Transition t = new Transition(value, start, end);
				start.outTransitions.add(t);
				end.inTransitions.add(t);
				this.transitions.add(t);
			}
		}
	}

	public void createTransition(String value, State state) {
		boolean exists = false;

		for (Transition t : state.selfTransitions) {
			if (t.value.contentEquals(value)) {
				exists = true;
				break;
			}
		}

		if (!exists) {
			Transition t = new Transition(value, state);
			state.selfTransitions.add(t);
			this.transitions.add(t);
		}

	}

	public List<State> getStates() {
		return states;
	}

	public List<Transition> getTransitions() {
		return transitions;
	}

	public boolean isThereInitialState() {
		return initialState != null;
	}

	public State getInitialState() {
		return initialState;
	}
}