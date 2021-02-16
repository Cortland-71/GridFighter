package application;

import application.model.Person;

public class Heal implements Fireable {

	@Override
	public int getMoveId() {
		return 4;
	}

	@Override
	public void fire(Person person1, Person person2) {
		System.out.println("Heal");
	}

}
