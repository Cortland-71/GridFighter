package application;

import application.model.Person;

public interface Fireable {
	
	public abstract String getId();

	public abstract void fire(Person person1, Person person2);

}
