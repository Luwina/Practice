package assignementmain;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import assignment.Student;
public class CodeMain {
	public static void main(String[] args) throws InvalidRollnoException {
		char choice = 'y';
		int id;
		int flag=0, flag1=0;
		String rollno;
		EntityManagerFactory factory = null;
		EntityManager manager = null;
		EntityTransaction transaction = null;
		Scanner scanner;
		try {

			scanner = new Scanner(System.in);
			factory = Persistence.createEntityManagerFactory("emp");
			manager = factory.createEntityManager();
			transaction = manager.getTransaction();

			while (true) {
				int ch;
				Student stud = null;
				if (choice == 'y') {
					System.out.println(
							"Press 1 to see all the data \n Press 2 To see specific data \n Press 3 to Update any data \n Press 4 to delete any data");
					System.out.println("enter your choice");
					ch = scanner.nextInt();
					switch (ch) {
					case 1:
						String query1 = "from Student";
						Query query = manager.createQuery(query1);
						List<Student> list = query.getResultList();
						System.out.println(list);
						break;
					case 2:
						transaction.begin();
						String ids;
						System.out.println("enter the rollno");
						ids = scanner.next();
						String query2 = "from Student where rollno= :ids";
						Query query11 = manager.createQuery(query2);
						query11.setParameter("ids", Integer.parseInt(ids));

						try {
							List<Student> list1 = query11.getResultList();
							if(list1.isEmpty())
								throw new InvalidRollnoException("Invalid rollno");
							else
								System.out.println(list1);
						}																																												
						catch (InvalidRollnoException e) {
							System.out.println(e.getMessage());
						}
						transaction.commit();
						//System.out.println(stud);
						break;
					case 3:
						transaction.begin();
						String rollno1;
						System.out.println("enter the rollno");
						rollno1 = scanner.next();
						String queryu1 = "from Student where rollno= :rollno1";
						Query queryu11 = manager.createQuery(queryu1);
						queryu11.setParameter("rollno1", Integer.parseInt(rollno1));

						try {
							List<Student> list1 = queryu11.getResultList();
							if(list1.isEmpty())
								throw new InvalidRollnoException("Invalid rollno");
							else
								flag=1;
						}																																												
						catch (InvalidRollnoException e) {
							System.out.println(e.getMessage());
						}
						transaction.commit();
						//System.out.println(stud);

						if(flag==1) {
							char cho,cho1,cho2;

							System.out.println("do you want to change the name");
							cho=scanner.next().charAt(0);
							if(cho=='y') {
								System.out.println("enter the name");
								String name= scanner.next();
								transaction.begin();
								String update="update Student set name=:name   where rollno=:rollno1";
								Query queryp= manager.createQuery(update);
								queryp.setParameter("name",name);
								queryp.setParameter("rollno1",Integer.parseInt(rollno1));
								int r = queryp.executeUpdate();
								transaction.commit();
							}



							System.out.println("do you want to change the phone no");


							cho1=scanner.next().charAt(0);
							if(cho1=='y') {
								System.out.println("enter the phone number");
								String phoneNo= scanner.next();
								transaction.begin();
								String update="update Student set phoneNo=:phoneNo   where rollno=:rollno1";
								Query queryp= manager.createQuery(update);
								queryp.setParameter("phoneNo",Long.parseLong(phoneNo));
								queryp.setParameter("rollno1",Integer.parseInt(rollno1));
								int r = queryp.executeUpdate();
								transaction.commit();
							}




							System.out.println("do you want to change the standard");


							cho2=scanner.next().charAt(0);
							if(cho2=='y') {
								System.out.println("enter the standard");
								String standard= scanner.next();
								transaction.begin();
								String update="update Student set standard=:standard   where rollno=:rollno1";
								Query queryp= manager.createQuery(update);
								queryp.setParameter("standard",Integer.parseInt(standard));
								queryp.setParameter("rollno1",Integer.parseInt(rollno1));
								int r = queryp.executeUpdate();
								transaction.commit();
							}				
						}
						break;
					case 4:
						transaction.begin();
						String rollno2;
						System.out.println("enter the rollno");
						rollno2= scanner.next();
						String queryd = "from Student where rollno= :rollno2";
						Query queryd1 = manager.createQuery(queryd);
						queryd1.setParameter("rollno2", Integer.parseInt(rollno2));

						try {
							List<Student> list1 = queryd1.getResultList();
							if(list1.isEmpty())
								throw new InvalidRollnoException("Invalid rollno");
							else
								flag1=1;
						}																																												
						catch (InvalidRollnoException e) {
							System.out.println(e.getMessage());
						}
						transaction.commit();
						if(flag1==1) {

							transaction.begin();
							String update="delete from Student  where rollno=:rollno2";
							Query queryp= manager.createQuery(update);
							queryp.setParameter("rollno2",Integer.parseInt(rollno2));
							int r = queryp.executeUpdate();
							transaction.commit();	
						}

						break;
					}
				}

				else {
					System.exit(0);
				}

				System.out.println("do you want to continue");
				choice = scanner.next().charAt(0);
			}

		}
		catch (Exception e) {
			transaction.rollback();
		}
		finally {
			manager.close();
			factory.close();
		}
	}
}







