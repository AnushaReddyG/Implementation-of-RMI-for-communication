package javarmi.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//Used by CourseList Server for invoking RemoteMethods below
public interface CourseList extends Remote {
		
	public ArrayList<String> getCourseList(int id) throws RemoteException;
	
	public ArrayList<String> getProfessorList(String coursename) throws RemoteException;


}
