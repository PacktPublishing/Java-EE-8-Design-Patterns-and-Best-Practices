package book.jeepatterns.model;

import java.util.Arrays;
import java.util.List;

public class BasicEngineering implements Engineering {
/*	@Override
	public List<Discipline> getDisciplines() {
		return Arrays.asList (new Discipline ("CA1", "Calculus 1"),new Discipline ("PH1", "Physics 1"));
	}
*/
	@Override
	public List<String> getDisciplines() {
		return Arrays.asList("d7", "d3");
	}
	
	}
