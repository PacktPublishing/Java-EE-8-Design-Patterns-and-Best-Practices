package book.jeepatterns.decorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import book.jeepatterns.model.Engineering;
import book.jeepatterns.qualifier.Mechanical;

@Decorator
public abstract class MechanicalDecorator implements Engineering {
	@Mechanical
	@Any
	@Inject
	@Delegate
	Engineering engineering;

	@Override
	public List<String> getDisciplines() {
		System.out.println("Decorating Mechanical");
		List<String> disciplines = new ArrayList<>(engineering.getDisciplines());
		disciplines.addAll (Arrays.asList("d31", "d37", "d33", "d34", "d32"));
		return disciplines;
	}
	
/*	public List<Discipline> getDisciplines() {
		return engineering.getDisciplines();
	}
*/}
