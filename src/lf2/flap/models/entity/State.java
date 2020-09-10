package lf2.flap.models.entity;

import java.util.ArrayList;
import java.util.List;

public class State {
	private boolean isInit, isFinal;
	private int id;
	private String label;
	private List<Transition> inTransitions;
	private List<Transition> outTransitions;
	
	public State(int id, String label) {
		this.id = id;
		this.label = label;
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
	
	public int getId() {
		return id;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setInit(boolean isInit) {
		this.isInit = isInit;
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
