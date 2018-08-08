package book.jeepatterns.decorator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import book.jeepatterns.model.Engineering;
import book.jeepatterns.qualifier.Electronic;

@Decorator
public abstract class ElectronicDecorator implements Engineering {
	@Electronic
	@Any
	@Inject
	@Delegate
	Engineering engineering;

	@Override
	public List<String> getDisciplines() {
		System.out.println("Decorating Electronic");
		List<String> disciplines = new ArrayList<>(engineering.getDisciplines());
		disciplines.addAll (Arrays.asList("d21", "d27", "d23", "d24", "d22"));
		return disciplines;
	}
	
/*	public List<Discipline> getDisciplines() {
		return engineering.getDisciplines();
	}
*/
	
	
}
