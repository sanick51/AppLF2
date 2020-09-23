package lf2.flap.models.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Automaton - Clase encaraga da de manejar el aut�mata 
 * Cuenta con una lista de estados, una lista de transiciones y un estado inicial.
 * @author Felipe Chaparro - Yohan Caro - Fabian Cristancho
 *	
 * 22 sep. 2020
 */
public class Automaton {
	private List<State> states;
	private List<Transition> transitions;
	protected State initialState;
	
	/**
	 * Constructor que crea la clase Automaton
	 */
	public Automaton() {
		this.states = new ArrayList<State>();
		this.transitions = new ArrayList<Transition>();
	}

	/**
	 * A�ade un estado al aut�mata
	 * @param state estado
	 */
	public void addState(State state) {
		this.states.add(state);
	}

	/**
	 * A�ade una transici�n al aut�mata
	 * @param t transici�n
	 */
	public void addTransition(Transition t) {
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
				if (t.value != null) {
					if (tt.value.contentEquals(t.value) && tt.endState == t.endState) {
						exists = true;
						break;
					}
				} else {
					if(tt.value == null) {
						exists=true;
						break;
					}
						
				}
			}

			if (!exists) {
				t.startState.outTransitions.add(t);
				t.endState.inTransitions.add(t);
				this.transitions.add(t);
			}
		}
	}

	/**
	 * Crea una transici�n con una etiqueta, un estado de inicio y un estado de fin
	 * @param value etiqueta de la transici�n
	 * @param start inicio
	 * @param end fin
	 */
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
	
	/**
	 * Crea una transici�n bucle para una estado
	 * @param value etiqueta de la transici�n
	 * @param state estado con bucle
	 */
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

	/**
	 * Elimina una transici�n de la lista de transiciones
	 * @param t transici�n (objeto)
	 */
	public void removeTransition(Transition t) {
		this.transitions.remove(t);
		t.startState.outTransitions.remove(t);
		t.endState.inTransitions.remove(t);
		t.startState.selfTransitions.remove(t);
	}
	
	/**
	 * Elimina un estado y sus transiciones
	 * @param s estado (objeto)
	 */
	public void removeState(State s) {
		this.states.remove(s);
		
		for(Transition t : s.outTransitions) {
			this.removeTransition(t);
		}
		
		for(Transition t : s.inTransitions) {
			this.removeTransition(t);
		}
		
		for(Transition t : s.selfTransitions) {
			this.removeTransition(t);
		}
	}
	
	/**
	 * Obtiene la lista de transiciones
	 * @return states lista
	 */
	public List<State> getStates() {
		return states;
	}
	
	/**
	 * Obtiene la lista de transiciones
	 * @return transitions lista
	 */
	public List<Transition> getTransitions() {
		return transitions;
	}
	
	/**
	 * Verifica si existe un estado inicial en el aut�mata
	 * @return true / false
	 */
	public boolean isThereInitialState() {
		return initialState != null;
	}
	
	/**
	 * Obtiene el estado inicial
	 * @return initialState estado
	 */
	public State getInitialState() {
		return initialState;
	}
	
	/**
	 * Cuenta el n�mero de estados finales en el aut�mata
	 * @return v contador
	 */
	public int finalStatesCount() {
		int v= 0;
		for (State s : states) {
			if(s.isFinal)
				v++;
		}
		
		return v;
	}
}