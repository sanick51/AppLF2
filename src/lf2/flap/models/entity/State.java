package lf2.flap.models.entity;

import java.util.ArrayList;
import java.util.List;

public class State {
	protected boolean isInit, isFinal;
	protected String label;
	protected Automaton automaton;
	protected List<Transition> inTransitions;
	protected List<Transition> outTransitions;

	public State(Automaton automaton) {
		this.automaton = automaton;
		this.inTransitions = new ArrayList<Transition>();
		this.outTransitions = new ArrayList<Transition>();
	}

	public State(Automaton automaton, String label) {
		this.label = label;
		this.automaton = automaton;
		this.inTransitions = new ArrayList<Transition>();
		this.outTransitions = new ArrayList<Transition>();
	}

	public List<Transition> getInTransitions() {
		return inTransitions;
	}

	public List<Transition> getOutTransitions() {
		return outTransitions;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public void setInit(boolean isInit) {
		if (isInit) {
			if (this.automaton.initialState == null) {
				this.isInit = isInit;
				this.automaton.initialState = this;
			}
		} else {
			if (this.automaton.initialState == this) {
				this.isInit = isInit;
				this.automaton.initialState = null;
			}
		}
	}

	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}

	public boolean isInit() {
		return isInit;
	}

	public boolean isFinal() {
		return isFinal;
	}
}
