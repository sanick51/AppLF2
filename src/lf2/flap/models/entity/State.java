package lf2.flap.models.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * State - Clase que se encarga de crear el estado con una etiqueta, lista de transiciones de
 * salida, lista de transiciones de entrada al estado, lista de transiciones propias
 * @author Felipe Chaparro - Yohan Caro - Fabian Cristancho
 *	
 * 22 sep. 2020
 */
public class State {
	protected boolean isInit, isFinal;
	protected String label;
	protected Automaton automaton;
	protected List<Transition> inTransitions;
	protected List<Transition> outTransitions;
	protected List<Transition> selfTransitions;

	/**
	 * Constructor que recibe el automata a cual pertenece
	 * @param automaton autómata
	 */
	public State(Automaton automaton) {
		this.automaton = automaton;
		this.inTransitions = new ArrayList<Transition>();
		this.outTransitions = new ArrayList<Transition>();
		this.selfTransitions = new ArrayList<Transition>();
	}

	/**
	 * Constructor que crea un estado con el autómata al cual pertenece y la etiqueta del autómata
	 * @param automaton autómata
	 * @param label etiqueta
	 */
	public State(Automaton automaton, String label) {
		this.label = label;
		this.automaton = automaton;
		this.inTransitions = new ArrayList<Transition>();
		this.outTransitions = new ArrayList<Transition>();
		this.selfTransitions = new ArrayList<Transition>();
	}
	
	/**
	 * Obtiene las transiciones de entrada al estado
	 * @return inTransitions lista
	 */
	public List<Transition> getInTransitions() {
		return inTransitions;
	}
	
	/**
	 * Obtiene las transiciones de salida del estado
	 * @return outTransitions lista
	 */
	public List<Transition> getOutTransitions() {
		return outTransitions;
	}
	
	/**
	 * Obtiene la lista de transiones bucle del estado
	 * @return selfTransitions lista
	 */
	public List<Transition> getSelfTransitions() {
		return selfTransitions;
	}

	/**
	 * Obtiene la etiqueta del estado
	 * @return label etiqueta
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Cambia el valor de la etiqueta del estado
	 * @param label etiqueta
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Cambia el valor del estado incial
	 * @param isInit true / false
	 */
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

	/**
	 * Cambiar el valor de si es un estado final
	 * @param isFinal true / false
	 */
	public void setFinal(boolean isFinal) {
		this.isFinal = isFinal;
	}
	
	/**
	 * Verifica se es el estado inicial
	 * @return isInit true / false
	 */
	public boolean isInit() {
		return isInit;
	}

	/**
	 * Verifica si es un estado final
	 * @return isFinal true / false
	 */
	public boolean isFinal() {
		return isFinal;
	}
	
	/**
	 * Obtiene el autómata en el que está el estado
	 * @return automaton a
	 */
	public Automaton getAutomaton() {
		return automaton;
	}
	
	/**
	 * Verifica si un estado tiene una transición de salida
	 * @param s Estado
	 * @return true / false
	 */
	public boolean hasTransition(State s) {
		for (Transition t : outTransitions) {
			if(t.endState == s)
				return true;
		}
		
		return false;
	}
}
