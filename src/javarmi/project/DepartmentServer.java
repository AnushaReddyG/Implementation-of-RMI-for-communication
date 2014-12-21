package javarmi.project;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

//DepartmentServer is Client for CourseListServer and Server for StudentClient and Professor
public class DepartmentServer implements DepartmentList, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	//Used for Storing Departments
	public ArrayList<String> departments = new ArrayList<String>();
	
	public static void main(String[] args){
		try {
			//creating, exporting DepartmentServer remote object
		    DepartmentServer deptlist = new DepartmentServer();
		    deptlist.addDepartments();
		    DepartmentList stub = (DepartmentList) UnicastRemoteObject.exportObject(deptlist, 0);

		    //binding to Registry
		    Registry reg = LocateRegistry.getRegistry();
		    reg.bind("DepartmentList", stub);

		    System.out.println("Department Server is Ready");
		} catch (Exception e) {
		    System.err.println("Department Server Exception: " + e.toString());
		    e.printStackTrace();
		}
		
	}

	//getting list of departments
	public ArrayList<String> getDepartmentList() {
		
		return departments;
	}

	//list of courses by department
	public ArrayList<String> getCourseList(int id) {
		ArrayList<String> courselist = new ArrayList<String>();
		try{
			//lookups for the CourseList Server stub from registry for invoking getCourseList(id) remote method
			Registry deptreg = LocateRegistry.getRegistry(null);
		    CourseList deptstub = (CourseList) deptreg.lookup("CourseList");
		    courselist = (ArrayList<String>) deptstub.getCourseList(id);
		}
		catch(Exception e) {
		    System.err.println("Client Exception: " + e.toString());
		    e.printStackTrace();
		}

		return courselist;
	}
	
	//adding departments
	public void addDepartments(){
		departments.add("1.Computer Science");
		departments.add("2.Biology");
		departments.add("3.MIS");
	}
	
	//testing referential integrity of objects having same reference
	public ArrayList<String> testReferentialIntegrity(StudentClient obj1, StudentClient obj2){
		ArrayList<String> objsids=new ArrayList<String>();
		try{
			
			String id1=Integer.toString(System.identityHashCode(obj1));
			String id2=Integer.toString(System.identityHashCode(obj2));
			
			objsids.add(id1);
			objsids.add(id2);
			
		System.out.println("ObjectId of Student Client1 is " +id1);
		System.out.println("ObjectId of Student Client2 is "+id2);
		
	}catch(Exception e) {
	    System.err.println("Client Exception: " + e.toString());
	    e.printStackTrace();
	}
		return objsids;
	}
	

}
