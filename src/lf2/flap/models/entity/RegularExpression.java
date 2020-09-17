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
			ret += auxs.selfTransitions.get(0).value + "*";
		} else {
		}

		ret += auxs.outTransitions.get(0).value;

		auxs = auxs.outTransitions.get(0).endState;

		if (auxs.selfTransitions.size() == 1) {
			ret += auxs.selfTransitions.get(0).value + "*";
		} else {
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
		Automaton red = new Automaton();
		reduce(aut.initialState, red);

		if (red.getStates().size() > 0)
			red.getStates().get(0).setInit(true);

		return red;
	}

	public static void reduce(State auxs, Automaton red) {
		List<Transition> auxts = auxs.outTransitions;
		List<Transition> to, ti, ts, tsb;
		Transition t, prev = null, prev2 = null;
		State[] ss;
		String loopStr = "";

		for (int i = 0; i < auxts.size(); i++) {
			t = auxts.get(i);
			to = t.endState.outTransitions;
			ti = t.endState.inTransitions;
			ts = t.endState.selfTransitions;
			loopStr = "";

			if (to.size() == 1 && ti.size() == 1) {
				ss = generateNewStates(auxs, to.get(0).endState, red);

				tsb = t.endState.selfTransitions;

				if (tsb.size() == 1) {
					loopStr = tsb.get(0).value + "*";
				}

				tsb = to.get(0).endState.selfTransitions;

				if (tsb.size() == 1) {
					red.addTransition(new TransitionFigure(tsb.get(0).value, ss[1]));
				}

				if (t.value.contains("+")) {
					if (t.value.charAt(t.value.length() - 1) != ')' && t.value.charAt(0) != '(')
						t.value = "(" + t.value + ")";
				}

				if (to.get(0).value.contains("+")) {
					if (to.get(0).value.charAt(to.get(0).value.length() - 1) != ')' && to.get(0).value.charAt(0) != '(')
						to.get(0).value = "(" + to.get(0).value + ")";
				}

				// o-o-o
				red.addTransition(new TransitionFigure(t.value + loopStr + to.get(0).value, ss[0], ss[1]));
				reduce(to.get(0).endState, red);

			} else if (to.size() == 1 && ti.size() > 1) {
				// o > o-o
				ss = generateNewStates(auxs, t.endState, to.get(0).endState, red);
				tsb = t.endState.selfTransitions;

				if (tsb.size() == 1) {
					// ó
					red.addTransition(new TransitionFigure(tsb.get(0).value, ss[1]));
				}

				if (prev2 != null && prev2.startState == ss[0] && prev2.endState == ss[1]) {
					prev2.value += "+" + t.value;
				} else {
					red.addTransition(new TransitionFigure(t.value, ss[0], ss[1]));
					prev2 = prev = red.getTransitions().get(red.getTransitions().size() - 1);
					red.addTransition(new TransitionFigure(to.get(0).value, ss[1], ss[2]));
				}
			} else if (to.size() > 1) {
				// o-o < o
				ss = generateNewStates(auxs, t.endState, red);

				if (ts.size() == 1) {
					loopStr = ts.get(0).value + "*";
				} else if (ts.size() > 1) {

				}

				red.addTransition(new TransitionFigure(t.value + loopStr, ss[0], ss[1]));

				reduce(t.endState, red);
			} else {
				ss = generateNewStates(auxs, t.endState, red);
				ss[1].isFinal = true;
				tsb = t.endState.selfTransitions;

				if (tsb.size() == 1) {
					// ó
					red.addTransition(new TransitionFigure(tsb.get(0).value, ss[1]));
				}

				if (prev != null && prev.startState == ss[0] && prev.endState == ss[1]) {
					prev.value += "+" + t.value;
				} else {
					// -o
					red.addTransition(new TransitionFigure(t.value, ss[0], ss[1]));
					prev = red.getTransitions().get(red.getTransitions().size() - 1);
				}
			}
		}
	}

	public static State[] generateNewStates(State auxs, State auxe, Automaton red) {
		State[] ss = new State[2];

		for (State s : red.getStates()) {
			if (s.label.contentEquals(auxs.label))
				ss[0] = s;

			if (s.label.contentEquals(auxe.label))
				ss[1] = s;
		}

		if (ss[0] == null) {
			ss[0] = new StateFigure(red, auxs.label);
			red.addState(ss[0]);
		}

		if (ss[1] == null) {
			ss[1] = new StateFigure(red, auxe.label);
			red.addState(ss[1]);
		}

		return ss;
	}

	public static State[] generateNewStates(State auxs, State auxc, State auxe, Automaton red) {
		State[] ss = new State[3];

		for (State s : red.getStates()) {
			if (s.label.contentEquals(auxs.label))
				ss[0] = s;

			if (s.label.contentEquals(auxc.label))
				ss[1] = s;

			if (s.label.contentEquals(auxe.label))
				ss[2] = s;
		}

		if (ss[0] == null) {
			ss[0] = new StateFigure(red, auxs.label);
			red.addState(ss[0]);
		}

		if (ss[1] == null) {
			ss[1] = new StateFigure(red, auxc.label);
			red.addState(ss[1]);
		}

		if (ss[2] == null) {
			ss[2] = new StateFigure(red, auxe.label);
			red.addState(ss[2]);
		}

		return ss;
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
