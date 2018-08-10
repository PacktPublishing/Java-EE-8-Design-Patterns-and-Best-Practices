package book.jeepatterns.dao;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import book.jeepatterns.model.Professor;

public class ProfessorDAO {
	private static Set<Professor> professors;
	static  {
		Professor p1 = new Professor ("professor a", LocalDate.of (2001, 03, 22)),
				  p2 = new Professor ("professor b", LocalDate.of (1994, 07, 05)),
		          p3 = new Professor ("professor c", LocalDate.of (1985, 10, 12)),
		          p4 = new Professor ("professor cv", LocalDate.of (2005, 07, 17));
		
		professors = Arrays
					.stream (new Professor[]{p1, p2, p3, p4})
				    .collect (Collectors.toSet());
		
	}
	
	public Professor findByName (String name) {
		return professors
				.stream()
				.filter(p->p.getName().equals(name))
				.findAny()
				.get();
	}

}
