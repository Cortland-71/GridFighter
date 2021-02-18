package application;

import application.model.Person;

public class Defend implements Fireable {


	@Override
	public void fire(Person person1, Person person2) {
		System.out.println("Defend");
		
	}

	@Override
	public String getId() {
		return "DEFEND";
	}

}
