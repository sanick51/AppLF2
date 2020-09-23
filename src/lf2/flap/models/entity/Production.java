package lf2.flap.models.entity;

public class Production {

	private String axiomaticSymbol;
	private String production;

	public Production(String axiomaticSymbol, String production) {
		this.setAxiomaticSymbol(axiomaticSymbol);
		this.setProduction(production);
	}

	public String getAxiomaticSymbol() {
		return axiomaticSymbol;
	}

	public void setAxiomaticSymbol(String axiomaticSymbol) {
		this.axiomaticSymbol = axiomaticSymbol;
	}

	public String getProduction() {
		return production;
	}

	public void setProduction(String production) {
		this.production = production;
	}

}