package tests_regex;

import lf2.flap.models.entity.Automaton;
import lf2.flap.models.entity.RegularExpression;
import lf2.flap.models.entity.State;

public class TestAutomaton {
	public static void main(String[] args) {
		Automaton a = new Automaton();

		State s0 = new State(a, "q0");
		State s1 = new State(a, "q1");
		State s2 = new State(a, "q2");
		State s3 = new State(a, "q3");
		State s4 = new State(a, "q4");
		State s5 = new State(a, "q5");

		s0.setInit(true);
		s5.setFinal(true);

		a.addState(s0);
		a.addState(s1);
		a.addState(s2);
		a.addState(s3);
		a.addState(s4);
		a.addState(s5);

		a.createTransition("a", s0, s1);
		a.createTransition("b", s1);
		a.createTransition("c", s1, s2);
		a.createTransition("d", s2, s4);
		a.createTransition("e", s1, s3);
		a.createTransition("f", s3, s4);
		a.createTransition("g", s4);
		a.createTransition("h", s4,s5);

		System.out.println(RegularExpression.getRegex(a));
	}
}
