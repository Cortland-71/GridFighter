package application;

import application.model.Person;

public class Insure implements Fireable {

	@Override
	public int getMoveId() {
		return 3;
	}

	@Override
	public void fire(Person person1, Person person2) {
		System.out.println("Insure");
		
	}

}
