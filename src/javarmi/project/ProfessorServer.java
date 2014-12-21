package javarmi.project;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

//Professor Server acts as Client to Department Server and as Server to CourseList Server and StudentClient
public class ProfessorServer implements ListofProfessors{
	//to store the list of professors according to courses
	public HashMap<String,ArrayList<String>> Proflist = new HashMap<String,ArrayList<String>>();

	public static void main(String[] args) {
		try {
			//creating, exporting CourseListServer remote object
		    ProfessorServer prof = new ProfessorServer();
		    prof.addProf();
		    ListofProfessors stub = (ListofProfessors) UnicastRemoteObject.exportObject(prof, 0);

		    //Binding to registry
		    Registry reg = LocateRegistry.getRegistry();
		    reg.bind("ListofProfessors", stub);

		    System.out.println("Professor Server is Ready");
		} catch (Exception e) {
		    System.err.println("Professor Server Exception: " + e.toString());
		    e.printStackTrace();
		}
		

	}

	//get the list of professors teaching the course selected
	public ArrayList<String> getProfessorList(String coursename) {
		
		return Proflist.get(coursename);
	}
	
	//adding professors
	public void addProf(){
		
		//add professors to Algorithms-2
		ArrayList<String> algos =new ArrayList<String>();
		algos.add("1.Gyorgy Turan");
		algos.add("2.Bhaskar DasGupta");
		Proflist.put("1.Algorithms-2", algos);
		
		//add professors to Database Systems
		ArrayList<String> db =new ArrayList<String>();
		db.add("1.Prasad Sistla");
		db.add("2.Ouir Wolfson");
		Proflist.put("2.Database Systems", db);
		
		//add professors to BioChemistry-2
		ArrayList<String> biochem =new ArrayList<String>();
		biochem.add("1.Constance Jeffery");
		biochem.add("2.Hua Jin");
		Proflist.put("1.BioChemistry-2", biochem);
		
		//add Professors to Analysis of Biological Structures
		ArrayList<String> biostruct =new ArrayList<String>();
		biostruct.add("1.M Simonovic");
		biostruct.add("2.A Lavie");
		Proflist.put("2.Analysis of Biological Stuctures", biostruct);
		
		//add Professors to Operations Management
		ArrayList<String> opmgmt =new ArrayList<String>();
		opmgmt.add("1.Aris M Oulcsel");
		opmgmt.add("2.Doug E Lundquist");
		Proflist.put("1.Operations Management", opmgmt);
		
		//add Professors to DataMining for Business
		ArrayList<String> dmb =new ArrayList<String>();
		dmb.add("1.Siddharth BhattaCharyya");
		dmb.add("2.Fan Wang");
		Proflist.put("2.Datamining for Business", dmb);
		
		
	}


	public ArrayList<String> getDepartmentList() {
		ArrayList<String> deptlist=new ArrayList<String>();
		try{
			//lookups for the DepartmentList Server stub from registry for invoking getDepartmentList() remote method
			Registry deptreg = LocateRegistry.getRegistry(null);
		    DepartmentList deptstub = (DepartmentList) deptreg.lookup("DepartmentList");
		    deptlist = deptstub.getDepartmentList();
		}
		catch(Exception e) {
		    System.err.println("Client Exception: " + e.toString());
		    e.printStackTrace();
		}

		return deptlist;
	}
	
	

}
