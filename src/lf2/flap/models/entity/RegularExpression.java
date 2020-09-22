package lf2.flap.models.entity;

import java.util.ArrayList;
import java.util.List;

import lf2.flap.views.figures.StateFigure;
import lf2.flap.views.figures.TransitionFigure;

public interface RegularExpression {

	public static String getRegex(Automaton aut) {
		String ret = "";
		Automaton a = reduceFull(aut);
		State auxs = a.initialState;

		if (auxs.selfTransitions.size() == 1) {
			ret += "(" + auxs.selfTransitions.get(0).value + ")*";
		}

		if (auxs.outTransitions.get(0).value != "")
			ret += "(" + auxs.outTransitions.get(0).value + ")";

		auxs = auxs.outTransitions.get(0).endState;

		if (auxs.selfTransitions.size() == 1) {
			ret += "(" + auxs.selfTransitions.get(0).value + ")*";
		}

		if (auxs.outTransitions.size() == 1) {
			ret = "(" + ret + auxs.outTransitions.get(0).value + ")*" + ret;
		}

		return ret;
	}

	public static Automaton reduceFull(Automaton aut) {
		Automaton red = reduce(aut);

		while (red.getStates().size() > 2)
			red = reduce(red);

		return red;
	}

	public static Automaton reduce(Automaton aut) {
		Automaton red = cloneAutomaton(aut);
		
		if (red.finalStatesCount() != 1 || red.getStates().size() == 1) {
			State finalState = new StateFigure(aut, "Final");
			finalState.setFinal(true);

			for (State s : red.getStates()) {
				if (s.isFinal) {
					s.setFinal(false);
					red.addTransition(new TransitionFigure("", s, finalState));
				}
			}

			red.addState(finalState);
		}

		List<State> states = red.getStates(), remove = new ArrayList<>();

		for (int i = 0; i < states.size(); i++) {
			for (int j = 0; j < states.size(); j++) {
				mergeTransitions(states.get(i), states.get(j));
			}
		}

		for (State s : states) {
			if (!s.isFinal && !s.isInit) {
				minimize(s, red);
				remove.add(s);
			}
		}

		for (State s : remove) {
			states.remove(s);
		}

		return red;
	}

	public static void minimize(State aux, Automaton a) {
		List<Transition> transitions = new ArrayList<>();
		String loopS;

		for (Transition ti : aux.inTransitions) {
			for (Transition to : aux.outTransitions) {
				loopS = "";

				if (aux.selfTransitions.size() == 1) {
					if (aux.selfTransitions.get(0).value.contains("+"))
						aux.selfTransitions.get(0).value = "(" + aux.selfTransitions.get(0).value + ")";

					loopS = aux.selfTransitions.get(0).value + "*";
				}

				a.addTransition(new TransitionFigure(ti.value + loopS + to.value, ti.startState, to.endState));
				transitions.add(ti);
				transitions.add(to);
			}
		}

		for (Transition t : transitions) {
			a.removeTransition(t);
		}
	}

	public static Transition searchTransition(State s, State f, Automaton a) {
		List<Transition> transitions = a.getTransitions();

		for (Transition t : transitions) {
			if (t.startState == s && t.endState == f)
				return t;
		}

		return null;
	}

	public static void mergeTransitions(State s, State f) {
		boolean isAppear = false;
		List<Transition> ts = new ArrayList<>();
		Transition aux = null;

		for (Transition t : s.outTransitions) {
			if (t.endState == f) {
				if (!isAppear) {
					isAppear = true;
					aux = t;
				} else {
					aux.value += (t.value != null ? (t.value == "" ? "" : "+") + t.value : "");
					ts.add(t);
				}
			}
		}

		for (Transition t : ts) {
			f.automaton.removeTransition(t);
		}
	}

	public static Automaton cloneAutomaton(Automaton aut) {
		Automaton red = new Automaton();
		State s;
		State[] ss;

		for (Transition t : aut.getTransitions()) {
			if (!t.isRecursive()) {
				ss = searchStates(t.startState, t.endState, red);
				red.addTransition(new TransitionFigure(t.value, ss[0], ss[1]));
			} else {
				s = searchState(t.startState, red);
				red.addTransition(new TransitionFigure(t.value, s));
			}
		}

		return red;
	}

	public static State searchState(State state, Automaton red) {
		State aux = null;

		for (State s : red.getStates()) {
			if (s.label.contentEquals(state.label))
				aux = s;
		}

		if (aux == null) {
			aux = new StateFigure(red, state.label);
			aux.isFinal = state.isFinal;
			aux.setInit(state.isInit);
			red.addState(aux);
		}

		return aux;
	}

	public static State[] searchStates(State stateS, State stateF, Automaton red) {
		State[] aux = new State[2];

		for (State s : red.getStates()) {
			if (s.label.contentEquals(stateS.label))
				aux[0] = s;

			if (s.label.contentEquals(stateF.label))
				aux[1] = s;
		}

		if (aux[0] == null) {
			aux[0] = new StateFigure(red, stateS.label);
			aux[0].isFinal = stateS.isFinal;
			aux[0].setInit(stateS.isInit);
			red.addState(aux[0]);
		}

		if (aux[1] == null) {
			aux[1] = new StateFigure(red, stateF.label);
			aux[1].isFinal = stateF.isFinal;
			aux[1].setInit(stateF.isInit);
			red.addState(aux[1]);
		}

		return aux;
	}

	public static Automaton toAutomaton(String regex) {
		Automaton a = new Automaton();
		List<State> aux = new ArrayList<State>();
		String value = "q";
		int count = 0;

		if (regex == null || regex.isEmpty()) {
			return null;
		}

		for (int i = 0; i < regex.length(); i++) { // Recorre la regex
			if (regex.charAt(i) == '*') { // Verifica si es bucle
				if (regex.charAt(i - 1) == ')') { // Verifica si en la anterior posición hay un )
					a.addTransition(new TransitionFigure("x",
							a.getTransitions().get(a.getTransitions().size() - 1).getEndState(), // Coge el último
																									// estado
							a.getTransitions().get(searchPositionParenthesis2(regex, i) - // Busca la transición después
																							// del ( y coge el estado
									countSymbols(regex, searchPositionParenthesis2(regex, i))).getStartState())); // de
																													// inicial
				} else { // Si no hay un ) antes, crea un bucle normal
					a.addTransition(new TransitionFigure(regex.substring(i - 1, i), aux.get(aux.size() - 1)));
				}
			} else if (regex.charAt(i) == '+') { // Si hay un +
				State s1 = new StateFigure(a, value + count);
				count++;
				a.addState(s1);
				aux.add(s1);
				State saux = a.getTransitions().get(a.getTransitions().size() - 1).getEndState(); // Crea un estado y lo
																									// une
				int y = i - 1;
				int c = 0;
				while (!isSymbol(regex.charAt(y))) { // Cuenta las concatenaciones antes del +
					c++;
					y--;
					if (y <= 0)
						break;
				}
				a.addTransition(new TransitionFigure(regex.substring(i + 1, i + 2),
						a.getTransitions().get(a.getTransitions().size() - c).getStartState(), s1));
				// Crea las transiciones antes del más
				int x = i + 2;

				if (x < regex.length())
					while (!isSymbol(regex.charAt(x))) { // Crea las concatenaciones depués del más hasta que haya un
															// bucle o ()
						State s3 = new StateFigure(a, value + count);
						count++;
						a.addTransition(new TransitionFigure(regex.substring(x, x + 1), aux.get(aux.size() - 1), s3));
						a.addState(s3);
						aux.add(s3);
						x++;
						if (x >= regex.length()) // Sale porque sí :p
							break;
					}
				State s2 = new StateFigure(a, value + count); // estado para las transiones vacías
				count++;
				a.addState(s2);
				a.addTransition(new TransitionFigure("", aux.get(aux.size() - 1), s2));
				aux.add(s2);
				a.addTransition(new TransitionFigure("", saux, s2));
				i = x;
			} else { // Si no es un + o *
				if (regex.charAt(i) != '(' && regex.charAt(i) != ')') { // Si tampoco es un ( y )
					if (aux.isEmpty()) { // Si es la primera concatenacion
						State s1 = new StateFigure(a, value + count);
						s1.setInit(true);
						count++;
						a.addState(s1);
						aux.add(s1);
						if (i + 1 < regex.length()) {
							if (regex.charAt(i + 1) != '*') { // Si en la siguiente posición no hay un bucle
								State s2 = new StateFigure(a, value + count);
								count++;
								a.addState(s2);
								aux.add(s2);
								a.addTransition(new TransitionFigure(regex.substring(i, i + 1), s1, s2));
							} else { // Si en la siguiente posición hay un bucle
								a.addTransition(
										new TransitionFigure(regex.substring(i, i + 1), aux.get(aux.size() - 1)));
							}
						} else {
							State s2 = new StateFigure(a, value + count);
							count++;
							a.addState(s2);
							aux.add(s2);
							a.addTransition(new TransitionFigure(regex.substring(i, i + 1), s1, s2));
						}
					} else { // si la posición es un ( o )
						if ((i + 1) < regex.length()) { // Si no es el último elemento
							if (regex.charAt(i + 1) != '*') {// si la siguiente posición no es bucle
								State s3 = new StateFigure(a, value + count);
								count++;
								a.addState(s3);
								a.addTransition(
										new TransitionFigure(regex.substring(i, i + 1), aux.get(aux.size() - 1), s3));
								aux.add(s3);
							}
						} else { // Si es el último elemento
							State s3 = new StateFigure(a, value + count);
							count++;
							a.addState(s3);
							a.addTransition(
									new TransitionFigure(regex.substring(i, i + 1), aux.get(aux.size() - 1), s3));
							aux.add(s3);
						}
					}
				}
			}

		}
		a.getTransitions().get(a.getTransitions().size() - 1).getEndState().isFinal = true;
		return a;
	}

	public static int searchPositionParenthesis2(String regex, int pos) {
		int j = pos - 2;
		int cont = 0;

		while (j >= 0 && cont >= 0) {
			if (regex.charAt(j) == ')') {
				cont++;
			} else if (regex.charAt(j) == '(') {
				cont--;
			}
			j--;
		}

		return j + 1;
	}

	/*
	 * Encontrar la posición del parentesis de apertura Contar la cantidad de
	 * simbolos antes de la posición del parentesis de apetura Restar la posición
	 * del parentesis con la cantidad de simbolos que han pasado (Complete)
	 */

	public static int countSymbols(String regex, int pos) {
		int count = 0;
		for (int i = (pos - 1); i >= 0; i--) {
			if (regex.charAt(i) == '(' || regex.charAt(i) == ')' || regex.charAt(i) == '*' || regex.charAt(i) == '+') {
				count++;
			}
		}
		return count;
	}

	public static boolean isSymbol(char c) {
		return (c == '(' || c == ')' || c == '*' || c == '+');
	}

}
