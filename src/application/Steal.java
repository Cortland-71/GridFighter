package application;

import application.model.Person;

public class Steal implements Fireable {

	@Override
	public int getMoveId() {
		return 2;
	}

	@Override
	public void fire(Person person1, Person person2) {
		System.out.println("Steal");
		
	}

}
