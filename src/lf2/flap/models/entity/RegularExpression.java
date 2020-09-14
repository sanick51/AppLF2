package lf2.flap.models.entity;

import java.util.List;

import lf2.flap.views.figures.StateFigure;
import lf2.flap.views.figures.TransitionFigure;

public interface RegularExpression {

	public static Automaton getAutomaton(String regex) {
		Automaton automaton = new Automaton();
		return automaton;
	}
	
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

				System.out.println("\to-o-o\t" + t.value);
				red.addTransition(new TransitionFigure(t.value + loopStr + to.get(0).value, ss[0], ss[1]));
				reduce(to.get(0).endState, red);

			} else if (to.size() == 1 && ti.size() > 1) {
				System.out.println("\to>o-o\t" + t.value);
				ss = generateNewStates(auxs, t.endState, to.get(0).endState, red);
				tsb = t.endState.selfTransitions;

				if (tsb.size() == 1) {
					System.out.println("\tó\t" + tsb.get(0).value);
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
				System.out.println("\to-o<o\t" + t.value);
				ss = generateNewStates(auxs, t.endState, red);

				if (ts.size() == 1) {
					loopStr = ts.get(0).value + "*";
				} else if (ts.size() > 1) {

				}

				red.addTransition(new TransitionFigure(t.value + loopStr, ss[0], ss[1]));

				reduce(t.endState, red);
			} else {
				ss = generateNewStates(auxs, t.endState, red);

				tsb = t.endState.selfTransitions;

				if (tsb.size() == 1) {
					System.out.println("\tó\t" + tsb.get(0).value);
					red.addTransition(new TransitionFigure(tsb.get(0).value, ss[1]));
				}

				if (prev != null && prev.startState == ss[0] && prev.endState == ss[1]) {
					prev.value += "+" + t.value;
				} else {
					System.out.println("\t-o\t" + t.value);
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

}
