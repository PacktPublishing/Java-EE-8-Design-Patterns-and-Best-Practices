package book.jeepatterns.decorator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import book.jeepatterns.facade.AcademicFacadeLocal;
import book.jeepatterns.model.Course;
import book.jeepatterns.model.Discipline;

@Decorator
public abstract class AcademicDecorator2 implements AcademicFacadeLocal {
	@Inject
    @Delegate
    @Any
    AcademicFacadeLocal academicFacade;
	
	public List<Discipline> getDisciplinesByCourse (Course course) {
		List<Discipline> disciplines = academicFacade.getDisciplinesByCourse (course);
		List<Discipline> disciplinesAux = new ArrayList<>(disciplines);
		disciplinesAux.add(new Discipline("D111", "DISCIPLINE 1"));
		Comparator<Discipline> comp = Comparator.comparing (d->d.getName());
		disciplinesAux.sort (comp);
		return disciplinesAux;
	}
}
