package lf2.flap.models.entity;

import java.util.List;

public class RegularExpresion {
	private Automaton automaton;
	private String regex;
	
	public RegularExpresion(String regex) {
		this.regex = regex;
		this.automaton = new Automaton();
	}
	
	public RegularExpresion(Automaton automaton) {
		this.automaton = automaton;
		this.regex = "";
	}
	
	public Automaton getAutomaton() {
		return getAutomaton(this.regex);
	}
	
	public String getRegex() {
		return getRegex(this.automaton);
	}
	
	public static Automaton getAutomaton(String regex) {
		Automaton automaton= new Automaton();
		return automaton;
	}
	
	public static String getRegex(Automaton automaton) {
		String regex = exam(automaton.initialState);

		return regex;
	}

	private static String exam(State auxs) {
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
}
