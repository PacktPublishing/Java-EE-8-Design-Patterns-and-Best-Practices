package book.jeepatterns.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.jeepatterns.bo.ProfessorBO;
import book.jeepatterns.facade.AcademicFacadeLocal;
import book.jeepatterns.facade.FinancialFacadeImpl;
import book.jeepatterns.mdb.EmailSender;
import book.jeepatterns.model.Course;
import book.jeepatterns.model.Discipline;
import book.jeepatterns.model.Engineering;
import book.jeepatterns.model.Professor;
import book.jeepatterns.model.Student;
import book.jeepatterns.model.TestRevisionTO;
import book.jeepatterns.qualifier.Electronic;
import book.jeepatterns.qualifier.Mechanical;

/**
 * Servlet implementation class ServletClient
 */
@WebServlet("/ServletClient")
public class ServletClient extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Inject
	@Electronic
	private Engineering electronicEngineering;  
	
	@Inject
	@Mechanical
	private Engineering mechanicalEngineering; 
	
	@Inject
	private Engineering basicEngineering; 
	
	
	@Inject
	private FinancialFacadeImpl financialFacadeImpl;
	
	@Inject                                    
	Event<TestRevisionTO> event;
	
	//@Inject                                    
	//Event<String> event1;	
	
	
	@Inject
	private ProfessorBO professorBO;
	
	@Inject 
	private AcademicFacadeLocal academicFacadeLocal;
	
	@Inject
	private EmailSender emailSender;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClient() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println ("init");
		
		// check students pending:
		boolean ret = financialFacadeImpl.isStudentPending (new Student ("20010003"));
		System.out.println ("check student pending: " + ret);
		
		ret = financialFacadeImpl.isStudentPending (new Student ("20010001"));
		System.out.println ("check student pending: " + ret);
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// check if a student an do his or her enrollment: 
		Student student = new Student();
		student.setEnrollment("20010003");
		boolean ret = financialFacadeImpl.canStudentEnroll(student);
		student.setEnrollment("20010001");
		ret = financialFacadeImpl.canStudentEnroll(student);
		
		// check if a professor can teach a specific discipline:
		professorBO.setProfessor(new Professor ("professor cv"));
		ret = professorBO.canTeachDiscipline(new Discipline ("D1", ""));

		// get the schedule date for a test revision:
		TestRevisionTO testRevisionTO = new TestRevisionTO();
		testRevisionTO.setEnrollment ("20010003");
		testRevisionTO.setDisciplineCode("D3");
		LocalDate date = LocalDate.of(2017, 11, 21);
		LocalTime time = LocalTime.of (8, 30);
		LocalDateTime dateTime = LocalDateTime.of(date, time); 
		testRevisionTO.setTestDateTime (dateTime);
		
		//event1.fire(testRevisionTO.getEnrollment());
		
		event.fire (testRevisionTO);
		//event..fireAsync (testRevisionTO); // the async event is new in JEE8.
		System.out.println("tudo azul!");
		
		
		List<Discipline> disciplines =  academicFacadeLocal.getDisciplinesByCourse (new Course ("C03", ""));
		
		disciplines
		.stream()
		.map (Discipline::getName)
		.forEach(System.out::println);
		
		
		System.out.println(disciplines);
		
		
		System.out.println("========================================================");
		
		
		List<String>disciplineNames = basicEngineering.getDisciplines();
		disciplineNames = electronicEngineering.getDisciplines();
		System.out.println("disciplines names : " + disciplineNames);
		
		
		/****************************************/
		
		emailSender.sendMessageNew("Ola Galerararararararaararar");
		
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
/*		String param = request.getParameter("job");
		if (param.equals("canEnroll")) {
			
		}
*/
		System.out.println("doGetttttttttttttttttt");
		
		Student student = new Student();
		student.setEnrollment("20010003");
		boolean ret = financialFacadeImpl.canStudentEnroll (student);
		student.setEnrollment("20010001");
		ret = financialFacadeImpl.canStudentEnroll(student);
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
