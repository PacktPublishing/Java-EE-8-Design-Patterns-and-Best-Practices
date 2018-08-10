package book.jeepatterns.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import book.jeepatterns.model.Discipline;
import book.jeepatterns.model.Professor;

public class DisciplineDAO {
	private static Map<Discipline, List<Discipline>> disciplineXPreReqDisciplines = new HashMap<>();
	private static Map<Professor, List<Discipline>> professorXDisciplines = new HashMap<>();
	private static Map<Discipline, List<String>> disciplineXBooks = new HashMap<>();
	private static List<Discipline> disciplines;
	
	static {
		Discipline d1 = new Discipline("D1", "discipline 1");
		Discipline d2 = new Discipline("D2", "discipline 2");
		Discipline d3 = new Discipline("D3", "discipline 3");
		Discipline d4 = new Discipline("D4", "discipline 4");
		disciplines = Arrays.asList(d1, d2, d3, d4);
		
		disciplineXPreReqDisciplines.put (d3, Arrays.asList (d1, d2));
		disciplineXPreReqDisciplines.put (d4, Arrays.asList (d2));
		
		professorXDisciplines.put (new Professor ("professor a"), Arrays.asList (d1, d2));
        professorXDisciplines.put (new Professor ("professor b"), Arrays.asList (d3));
        professorXDisciplines.put (new Professor ("professor cv"), Arrays.asList (d1, d3, d4));
        
        disciplineXBooks.put (d1, Arrays.asList ("book x", "book y"));
        disciplineXBooks.put (d2, Arrays.asList ("book x", "book a", "book w"));
        disciplineXBooks.put (d3, Arrays.asList ("book x", "book b"));
        disciplineXBooks.put (d4, Arrays.asList ("book z"));
	}
	
	public List<Discipline> getPreRequisiteDisciplines (Discipline discipline) {
		return disciplineXPreReqDisciplines.get (discipline);
	}
	
	public List<Discipline> getDisciplinesByProfessor(Professor professor) {
		return professorXDisciplines.get (professor);
	}

	public List<String> getBooksByDiscipline(Discipline discipline) {
		return disciplineXBooks.get (discipline);
	}
	
	public List<Professor> getProfessorByDiscipline (Discipline discipline) {
		return professorXDisciplines.keySet()
			   .stream()
			   .filter (p->professorXDisciplines.get(p).contains(discipline))
			   //.collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
			   .collect(Collectors.toList());
	}
	
/*	public List<Professor> getProfessorByDiscipline (Discipline discipline) {
		List<Professor> professors = new ArrayList<>();
		
		professorXDisciplines.forEach ((professor, disciplines)->{
			if (disciplines.contains (discipline)) {
				professors.add (professor);
			}
		});
		
		// ou
		
		List<Professor> professors1 =  professorXDisciplines.keySet()
				      .stream()
				      .filter (p->professorXDisciplines.get(p).contains(discipline))
				      .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
		
		// ou
		
		Set<Professor> keys = professorXDisciplines.keySet();
		keys.forEach (professor->{
			List<Discipline> disciplines = professorXDisciplines.get (professor);
			if (disciplines.contains (discipline)) {
				professors.add (professor);
			}
		});
		return professors;
	}
*/
	public Discipline getDisciplineByCode (String code) {
		return disciplines
		.stream()
		.filter(s->s.getCode().equals(code))
		.findAny()
		.get();
	}
	
}
