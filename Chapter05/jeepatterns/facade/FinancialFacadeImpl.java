package book.jeepatterns.facade;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import book.jeepatterns.dao.StudentDAO;
import book.jeepatterns.dao.StudentDAO.FINANCIAL_STATUS;
import book.jeepatterns.model.Student;

/**
 * Session Bean implementation class FinancialFacadeImpl
 */
@Stateless
@LocalBean
public class FinancialFacadeImpl {//implements FinancialFacadeRemote, FinancialFacadeLocal {
	
	@Inject
	private StudentDAO studentDAO;  

    /**
     * Default constructor. 
     */
    public FinancialFacadeImpl() {
    }
    
    public boolean canStudentEnroll (Student student) {
    	return studentDAO.getStudentFinancialStatus (student.getEnrollment()).isStatus();
    }
    
    public boolean isStudentPending (Student student) {
    	FINANCIAL_STATUS status = studentDAO.getStudentFinancialStatus (student.getEnrollment());
    	return (status.equals (FINANCIAL_STATUS.PENDING)) || (status.equals (FINANCIAL_STATUS.DOC_PENDING)); 
    }
    

}
