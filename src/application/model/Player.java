package application.model;

public class Player extends Person {

	private double eg = 1;
	private double atkCost = .05;
	private double defCost = .05;
	private double stlCost = .05;
	private double insCost = .05;
	private double helCost = 5.50;

	public void setEg(double eg) {
		this.eg = eg;
	}

	public double getEg() {
		return eg;
	}

	public double getAtkCost() {
		return atkCost;
	}

	public void setAtkCost(double atkCost) {
		this.atkCost = atkCost;
	}

	public double getDefCost() {
		return defCost;
	}

	public void setDefCost(double defCost) {
		this.defCost = defCost;
	}

	public double getStlCost() {
		return stlCost;
	}

	public void setStlCost(double stlCost) {
		this.stlCost = stlCost;
	}

	public double getInsCost() {
		return insCost;
	}

	public void setInsCost(double insCost) {
		this.insCost = insCost;
	}

	public double getHelCost() {
		return helCost;
	}

	public void setHelCost(double helCost) {
		this.helCost = helCost;
	}
	
	

}
