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
		try {
			if (state.isInit())
				if (this.initialState != null) {

					throw new Exception("Ya existe un estado inicial");
				} else {
					this.initialState = state;
				}

		} catch (Exception e) {
			e.printStackTrace();
		}

		this.states.add(state);
	}

	public void createTransition(String value, State start, State end) {
		Transition t = new Transition(value, start, end);
		start.getOutTransitions().add(t);
		end.getInTransitions().add(t);
		this.transitions.add(t);
	}

	public void createTransition(String value, State state) {
		Transition t = new Transition(value, state);
		state.getOutTransitions().add(t);
		state.getInTransitions().add(t);
		this.transitions.add(t);
	}

	public String getRegex() {
		String regex = exam(this.initialState);

		return regex;
	}

	private String exam(State auxs) {
		String regex = "";
		List<Transition> auxol = auxs.getOutTransitions();

		if (auxol.isEmpty())
			return regex;

		for (Transition t : auxol) {
			if (t.isRecursive()) {
				regex += t.getValue() + "*";
				
				if(auxol.size() > 1)
					regex +="(";
			}else {
				regex += t.getValue() + exam(t.getEndState()) + "+";
			}
		}
		

		if (regex.charAt(regex.length() - 1) == '+')
			regex = regex.substring(0, regex.length() - 1);
		else if (regex.charAt(regex.length() - 1) == '(')
			regex = regex.substring(0, regex.length() - 1);

		if(auxol.size() > 1)
			regex +=")";

		return regex;
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

}