package book.jeepatterns.decorator;

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
public abstract class AcademicDecorator implements AcademicFacadeLocal {
	@Inject
    @Delegate
    @Any
    AcademicFacadeLocal academicFacade;
	
	public List<Discipline> getDisciplinesByCourse (Course course) {
		List<Discipline> disciplines = academicFacade.getDisciplinesByCourse (course);
		Comparator<Discipline> comp = Comparator.comparing (d->d.getName());
		disciplines.sort (comp);
		return disciplines;
	}
}
