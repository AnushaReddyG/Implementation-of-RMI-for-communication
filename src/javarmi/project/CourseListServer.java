package javarmi.project;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

//CourseList Server acts as Server for Department Server and StudentClient and Client for Professor Server
public class CourseListServer implements CourseList {
	//storing courses according to department
	HashMap<Integer, ArrayList<String>> deptcourses=new HashMap<Integer,ArrayList<String>>();

	public static void main(String[] args) {
		
		try {
			//creating, exporting CourseListServer remote object
		    CourseListServer courselist = new CourseListServer();
		    courselist.addCourses();
		    CourseList stub = (CourseList) UnicastRemoteObject.exportObject(courselist, 0);
		    //Binding to registry
		    LocateRegistry.createRegistry(1099);
		    Registry registry = LocateRegistry.getRegistry();
		    registry.bind("CourseList", stub);

		    System.out.println("Course Server is Ready");
		} catch (Exception e) {
		    System.err.println("Course Server Exception: " + e.toString());
		    e.printStackTrace();
		}


	}

	//method to get list of courses department wise
	public ArrayList<String> getCourseList(int id) {
		return deptcourses.get(id);
	}
	
	public void addCourses(){
		//add courses related to Computer Science
		ArrayList<String> cscourses=new ArrayList<String>();
		cscourses.add("1.Algorithms-2");
		cscourses.add("2.Database Systems");
		deptcourses.put(1, cscourses);
		
		//add courses related to Biology
		ArrayList<String> biocourses = new ArrayList<String>();
		biocourses.add("1.BioChemistry-2");
		biocourses.add("2.Analysis of Biological Stuctures");
		deptcourses.put(2, biocourses);
		
		//add courses related to MIS
		ArrayList<String> miscourses = new ArrayList<String>();
		miscourses.add("1.Operations Management");
		miscourses.add("2.Datamining for Business");
		deptcourses.put(3,miscourses);
	}
	
	public ArrayList<String> getProfessorList(String coursename){
		ArrayList<String> proflist=new ArrayList<String>();
		
		try{
			//lookups for the Professor Server stub from registry for invoking getProfessorList(String ) remote method
			Registry profreg = LocateRegistry.getRegistry(null);
		    ListofProfessors profstub = (ListofProfessors) profreg.lookup("ListofProfessors");
		    proflist = (ArrayList<String>) profstub.getProfessorList(coursename);
		}
		catch(Exception e) {
		    System.err.println("Client Exception: " + e.toString());
		    e.printStackTrace();
		}
		return proflist;
	}

}
