package book.jeepatterns.bo;

import java.time.LocalDate;
import java.util.List;

import javax.inject.Inject;

import book.jeepatterns.dao.DisciplineDAO;
import book.jeepatterns.dao.ProfessorDAO;
import book.jeepatterns.interceptor.Logged;
import book.jeepatterns.model.Discipline;
import book.jeepatterns.model.Professor;

@Logged
public class ProfessorBO {
	
	private Professor professor; 
	private List<Discipline> disciplines;
	
	@Inject 
	private ProfessorDAO professorDAO;
	
	@Inject
	private DisciplineDAO disciplineDAO;
	
	public void setProfessor (Professor professor ) {
		this.professor = professorDAO.findByName (professor.getName());
	}
	
/*	public void setProfessor (Professor professor) {
		this.professor = professorDAO.findByName(professor.getName());
	}
*/	
	public boolean canTeachDiscipline (Discipline discipline) {
		if (disciplines == null) {
			disciplines = disciplineDAO.getDisciplinesByProfessor (professor);
		}
		return disciplines.stream().anyMatch(d->d.equals(discipline));
		//return disciplines.contains(discipline);
	}
	
	public LocalDate getInitDate () {
		return professor.getInitDate();
	}
	
	public String getName () {
		return professor.getName();
	}
	
}
