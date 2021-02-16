package application.model;

import java.util.List;

public class Person {

	private double hp = 1;
	private double cash = 500;
	
	private double atkEffect = .05;
	private double defEffect = .05;
	private double stlEffect = .05;
	private double insEffect = .05;
	private double helEffect = .05;
	
	public void setAllEffects(List<Double> amounts) {
		setAtkEffect(amounts.get(0));
		setDefEffect(amounts.get(1));
		setStlEffect(amounts.get(2));
		setInsEffect(amounts.get(3));
		setHelEffect(amounts.get(4));
		
//		System.out.println("atk effect:" + getAtkEffect());
//		System.out.println("def effect:" + getDefEffect());
//		System.out.println("stl effect:" + getStlEffect());
//		System.out.println("ins effect:" + getInsEffect());
//		System.out.println("hel effect:" + getHelEffect());
	}
	
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
