package lf2.flap.views;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.State;
import lf2.flap.models.entity.Transition;
import lf2.flap.views.figures.StateFigure;
import lf2.flap.views.figures.TransitionFigure;

public interface Grapher {
	/**
	 * Transforma las entidades internas del automatas a Figuras para poder ser dubujadas
	 * @param a Automata simple
	 * @return Automata grafico
	 */
	public static Automaton toGraph(Automaton a) {
		Automaton g = new Automaton();
		State s;
		State[] ss;

		for (Transition t : a.getTransitions()) {
			if (!t.isRecursive()) {
				ss = searchStates(t.getStartState(), t.getEndState(), g);
				g.addTransition(new TransitionFigure(t.getValue(), ss[0], ss[1]));
			} else {
				s = searchState(t.getStartState(), g);
				g.addTransition(new TransitionFigure(t.getValue(), s));
			}
		}
		
		return g;
	}
	
	
	public static State searchState(State state, Automaton red) {
		State aux = null;

		for (State s : red.getStates()) {
			if (s.getLabel().contentEquals(state.getLabel()))
				aux = s;
		}

		if (aux == null) {
			aux = new StateFigure(red, state.getLabel());
			aux.setFinal(state.isFinal());
			aux.setInit(state.isInit());
			red.addState(aux);
		}

		return aux;
	}

	public static State[] searchStates(State stateS, State stateF, Automaton red) {
		State[] aux = new State[2];

		for (State s : red.getStates()) {
			if (s.getLabel().contentEquals(stateS.getLabel()))
				aux[0] = s;

			if (s.getLabel().contentEquals(stateF.getLabel()))
				aux[1] = s;
		}

		if (aux[0] == null) {
			aux[0] = new StateFigure(red, stateS.getLabel());
			aux[0].setFinal(stateS.isFinal());
			aux[0].setInit(stateS.isInit());
			red.addState(aux[0]);
		}

		if (aux[1] == null) {
			aux[1] = new StateFigure(red, stateF.getLabel());
			aux[1].setFinal(stateF.isFinal());
			aux[1].setInit(stateF.isInit());
			red.addState(aux[1]);
		}

		return aux;
	}

}
