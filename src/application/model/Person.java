package application.model;

public class Person {

	private double hp = .5;
	private double cash = 500;
	
	public double getCash() {
		return cash;
	}

	public void setCash(double cash) {
		this.cash = cash;
	}

	public void setHp(double hp) {
		this.hp = hp;
	}

	public double getHp() {
		return hp;
	}

}
