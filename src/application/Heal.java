package application;

import application.model.Person;

public class Heal implements Fireable {


	@Override
	public void fire(Person person1, Person person2) {
		System.out.println("Heal");
	}

	@Override
	public String getId() {
		return "HEAL";
	}

}
