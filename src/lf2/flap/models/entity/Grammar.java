package lf2.flap.models.entity;

import java.util.ArrayList;
import java.util.Comparator;

public class Grammar {

	private ArrayList<String> nonterminalVariables;
	private String result;
	private ArrayList<String> alphabet;
	private String initialAxiomaticSymbol;
	private ArrayList<Production> productions;
	private TreeNArio<String> nArio;
	private long startTime = 0;
	private long elapsedTime = 0;
	private long  elapsedSeconds = 0 ;
	
	public Grammar() {
		this.nonterminalVariables = new ArrayList<>();
		this.alphabet = new ArrayList<>();
		this.initialAxiomaticSymbol = "";
		this.productions = new ArrayList<>();
		this.result = "";
	}

	public boolean validate(String input) {
		startTime =  System.currentTimeMillis();
		return validateAccepted(input, changeText(initialAxiomaticSymbol));
	}

	public TreeNArio<String> treeNArio(String input) {
		TreeNArio<String> treeNArio = new TreeNArio<String>(input, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		NodeTree<String> root = nArio.search(input);
		while (root != nArio.root) {
			treeNArio.addSon(root.info, root.father.info);
			root = root.father;
		}

		TreeNArio<String> auxNArio = new TreeNArio<String>(initialAxiomaticSymbol, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		NodeTree<String> rootAux = treeNArio.search(initialAxiomaticSymbol);
		while (rootAux != treeNArio.root) {
			auxNArio.addSon(rootAux.info, rootAux.father.info);
			rootAux = rootAux.father;
		}

		showTree(auxNArio.root);
		result += "Es aceptado ";
		return auxNArio;
	}

	public void showTree(NodeTree<String> root) {
		if (root.info == initialAxiomaticSymbol) {
			result += root.info + ",";
		}
		for (int i = 0; i < root.sons.size(); i++) {
			result += root.sons.get(i).info + ",";
			showTree(root.sons.get(i));
		}
	}

	public boolean validateAccepted(String input, ArrayList<String> list) {
		boolean flag = false;
		boolean validate = false;
		nArio = new TreeNArio<String>(initialAxiomaticSymbol, new Comparator<String>() {	
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		for (String string2 : changeText(initialAxiomaticSymbol)) {
			nArio.addSon(initialAxiomaticSymbol, string2);
		}
		if (list.size() != 0) {
			while (!flag) {
				elapsedTime = System.currentTimeMillis() - startTime;
				elapsedSeconds = elapsedTime / 1000;
				if (elapsedSeconds >= 2) {
					flag = true;
					validate = false;
				}
				ArrayList<String> aux = new ArrayList<String>();
				for (String string : list) {
					if (isTerminal(string)) {
						if (string.equals(input)) {
							validate = true;
							flag = true;
							break;
						}
					} else {
						if (countVar(string) <= input.length()) {
							for (String string2 : changeText(string)) {
								nArio.addSon(string, string2);
								aux.add(string2);
							}
						} else {
							flag = true;
						}
	
					}
				}
				list = aux;
			}
		}
		if (validate)
			treeNArio(input);
		else
			result = "No pertenece al lenguaje";
		return validate;
	}


	public int countVar(String text) {
		int count = 0;
		for (int i = 0; i < text.length(); i++) {
			if (alphabet.contains(text.charAt(i) + "")) {
				count++;
			}
		}
		return count;
	}

	public boolean isTerminal(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (nonterminalVariables.contains(text.charAt(i) + "")) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<String> changeText(String text) {
		ArrayList<String> list = new ArrayList<>();
		for (Production production : productions) {
			if (isIn(production.getAxiomaticSymbol(), text)) {
				list.add(replaceAxiomatic(text, production.getAxiomaticSymbol(), production.getProduction()));
			}
		}
		return list;
	}

	public String replaceAxiomatic(String text, String axiomatic, String replace) {
		String result = "";
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == axiomatic.charAt(0)) {
				result += replace;
			} else {
				result += text.charAt(i);
			}
		}
		return result;
	}

	public boolean isIn(String axiomatic, String text) {
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == axiomatic.charAt(0)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<String> getNonterminalVariables() {
		return nonterminalVariables;
	}
	
	public void setNonterminalVariables(ArrayList<String> nonterminalVariables) {
		this.nonterminalVariables = nonterminalVariables;
	}
	
	public ArrayList<String> getAlphabet() {
		return alphabet;
	}
	
	public void setAlphabet(ArrayList<String> alphabet) {
		this.alphabet = alphabet;
	}
	
	public String getInitialAxiomaticSymbol() {
		return initialAxiomaticSymbol;
	}
	
	public void setInitialAxiomaticSymbol(String initialAxiomaticSymbol) {
		this.initialAxiomaticSymbol = initialAxiomaticSymbol;
	}
	
	public ArrayList<Production> getProductions() {
		return productions;
	}
	
	public void setProductions(ArrayList<Production> productions) {
		this.productions = productions;
	}
	
	public TreeNArio<String> getnArio() {
		return nArio;
	}
	
	public void setnArio(TreeNArio<String> nArio) {
		this.nArio = nArio;
	}
	public String getTreeDerivate() {
		return result;
	}

	public void setTreeDerivate(String treeDerivate) {
		this.result = treeDerivate;
	}
}