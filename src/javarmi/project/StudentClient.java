package javarmi.project;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentClient implements Serializable {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		
		try {
		//lookups for Department Server in registry for invoking remote methods	 
		Registry registry = LocateRegistry.getRegistry(null);
	    DepartmentList deptstub = (DepartmentList) registry.lookup("DepartmentList");
	    
	    //gets the department list from remote method
	    ArrayList<String> deptlist  = (ArrayList<String>) deptstub.getDepartmentList();
		
	    //Prints List of Departments and takes department id as input from user
	    int deptid = deptList(deptlist);
	    
	    // If the user specified department id is not valid, the application returns and closes
	    if(deptid==0)
	    	return;
			
		//gets the list of courses from remote method
		ArrayList<String> courselist = (ArrayList<String>) deptstub.getCourseList(deptid);
		
		//Displays the list of courses and takes course number as user input 	
		int coursenum = courseList(courselist);
		
		// If the user specified course number is not valid, the application returns and closes
		if(coursenum==0)
		    	return;
		
		String coursename=courselist.get(coursenum-1);
		//lookups registry for Professor server to invoke remote methods(can also be done using CourseList Server)	
		ListofProfessors profstub = (ListofProfessors) registry.lookup("ListofProfessors");
		ArrayList<String> proflist = (ArrayList<String>) profstub.getProfessorList(coursename);
		 
		//Displays the list of Professors
		prof(proflist);
		
		//select option to test Referential Integrity or not
		System.out.println("Testing Referential Integrity");
		System.out.print("Enter Y to test Referential Integrity or N to exit application and press enter ");
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String refint = input.next();
		
		//if Y is selected
		if(refint.equalsIgnoreCase("Y")){
		
		//Objects having Same Reference
		StudentClient stu1=new StudentClient();
		StudentClient stu2= stu1;
		
		//object id's at client side
		String id1=Integer.toString(System.identityHashCode(stu1));
		String id2=Integer.toString(System.identityHashCode(stu2));
		
		System.out.println("The Object Id's at Client Side are : ");
		System.out.println(id1);
		System.out.println(id2);
		
		//Sending the objects to remote server and Printing the Object Ids of server
		ArrayList<String> objids=deptstub.testReferentialIntegrity(stu1, stu2);
		System.out.println("The ObjectId's at Server Side are : ");
		for (String value: objids) {
			System.out.println(value);
		}
		System.out.println("The Objects with same reference has same Object Id but different Id's in Client and Server ");
		}
		//if N is selected
		else if(refint.equalsIgnoreCase("N")){
			System.out.println("Application Completed");
		}
		else{
			System.out.println("Application Terminated");
		}

				
	}
		 catch (Exception e) {
		    System.err.println(" Student Client Exception: " + e.toString());
		    e.printStackTrace();
		}


	}

	//method for listing course list depending on Department id given by user
	public static int courseList(ArrayList<String> courselist) {
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
		System.out.println("List of Courses Available for the Department Selected");
		for (String value: courselist) {
			System.out.println(value);
		}
		
		System.out.println("Please enter a Number Specific to the Course to get the list of Professors teaching and press enter");
		String coursenum = input.next();
		//Number Input can be only 1 or 2.. for other numbers the application returns null and closes
		if(Integer.parseInt(coursenum)!=1 && Integer.parseInt(coursenum)!=2){
			System.out.println("Not a valid number");
			System.out.println("Please Return to Application");
			return 0;
		}
			// returning the number specified by the user
			return Integer.parseInt(coursenum);
		
	}

		//method to display list of departments
	public static int deptList(ArrayList<String> deptlist) {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		
	    System.out.println("List of Departments Available in College");
			for (String value: deptlist) {
				System.out.println(value);
			}
			System.out.println("Please enter a Number Specific to the Department to get Course list and press enter");
			String id = input.next();
			//Number Input can be only 1,2 or 3.. for other numbers the application returns null and closes
			if(Integer.parseInt(id)!=1 && Integer.parseInt(id)!=2 && Integer.parseInt(id)!=3){
				System.out.println("Not a valid number");
				System.out.println("Please Return to Application");
				return 0;
			}

			// returning the number specified by the user
			
			return Integer.parseInt(id);

	}
	
	//method to display list of Professors depending on the course selected
	public static void prof(ArrayList<String> listofprof){
		System.out.println("List of Prof Available for the Course Selected");
		for (String value: listofprof) {
			System.out.println(value);
		}
	}


}
