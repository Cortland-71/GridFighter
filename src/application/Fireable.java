package application;

import application.model.Person;

public interface Fireable {
	
	public abstract int getMoveId();

	public abstract void fire(Person person1, Person person2);

}
