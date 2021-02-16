package application;

import application.model.Person;

public class Attack implements Fireable {
	
	private int id = 0;

	@Override
	public void fire(Person personAttacking, Person personBeingAttacked) {
		personBeingAttacked.setHp(personBeingAttacked.getHp() - personAttacking.getAtkEffect());
		System.out.println("Attack");
	}

	@Override
	public int getMoveId() {
		return id;
	}

}
