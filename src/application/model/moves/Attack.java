package application.model.moves;

import application.model.Person;

public class Attack implements Fireable {
	

	@Override
	public void fire(Person personAttacking, Person personBeingAttacked) {
		personBeingAttacked.setHp(personBeingAttacked.getHp() - personAttacking.getAtkEffect());
		System.out.println("Attack");
	}

	@Override
	public String getId() {
		return "ATTACK";
	}


}
