package javarmi.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//Used by Professor Server for invoking RemoteMethods below
public interface ListofProfessors extends Remote {
	
	ArrayList<String> getProfessorList(String coursename) throws RemoteException;
	ArrayList<String> getDepartmentList() throws RemoteException;

}
