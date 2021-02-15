package application;

import application.model.Person;

public class Attack implements Fireable {
	
	private int id = 0;

	@Override
	public void fire(Person personAttacking, Person personBeingAttacked) {
		personBeingAttacked.setHp(personBeingAttacked.getHp() - .1);
		System.out.println("Attack: personAttacking: " + personAttacking + " damage: " + 
				" personBeingAttacked: " + personBeingAttacked + 
				"");
	}

	@Override
	public int getMoveId() {
		return id;
	}

}
