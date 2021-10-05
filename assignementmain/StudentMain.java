package assignementmain;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import assignment.Student;
public class StudentMain{
	public static void main(String[] args) {
		Student student= new Student();
		student.setRollno(104);
		student.setName("Vismith");
		student.setPhoneNo(9977447778l);
		student.setStandard(2);
		EntityManagerFactory factory=null;
		EntityManager manager= null;
		EntityTransaction transaction =null;
		try {
			factory=Persistence.createEntityManagerFactory("emp");
			manager=factory.createEntityManager();
			transaction= manager.getTransaction();
			transaction.begin();
			manager.persist(student);
			transaction.commit();
		}
		catch (Exception e) {
			
		}
		
	}
}
