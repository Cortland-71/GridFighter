package application;

import application.model.Person;

public class Insure implements Fireable {


	@Override
	public void fire(Person person1, Person person2) {
		System.out.println("Insure");
		
	}

	@Override
	public String getId() {
		return "INSURE";
	}

}
