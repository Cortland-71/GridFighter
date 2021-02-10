package application;

import application.model.Person;

public class Attack implements Firable {

	@Override
	public void fire(Person personAttacking, Person personBeingAttacked) {
		personBeingAttacked.setHp(personBeingAttacked.getHp() - .1);
		
	}

}
