package application.model;

public class Person {

	private double hp = 1;
	private double cash = 500;
	
	private double atkEffect = .05;
	private double defEffect = .05;
	private double stlEffect = .05;
	private double insEffect = .05;
	private double helEffect = .05;
	
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

	public double getAtkEffect() {
		return atkEffect;
	}

	public void setAtkEffect(double atkEffect) {
		this.atkEffect = atkEffect;
	}

	public double getDefEffect() {
		return defEffect;
	}

	public void setDefEffect(double defEffect) {
		this.defEffect = defEffect;
	}

	public double getStlEffect() {
		return stlEffect;
	}

	public void setStlEffect(double stlEffect) {
		this.stlEffect = stlEffect;
	}

	public double getInsEffect() {
		return insEffect;
	}

	public void setInsEffect(double insEffect) {
		this.insEffect = insEffect;
	}

	public double getHelEffect() {
		return helEffect;
	}

	public void setHelEffect(double helEffect) {
		this.helEffect = helEffect;
	}
	
	

}
