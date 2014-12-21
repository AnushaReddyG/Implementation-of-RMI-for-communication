package javarmi.project;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

//Used by Department Server for invoking RemoteMethods below
public interface DepartmentList extends Remote {
	
	ArrayList<String> getDepartmentList() throws RemoteException;
    
    ArrayList<String> getCourseList(int id) throws RemoteException;
    
    ArrayList<String> testReferentialIntegrity(StudentClient obj1, StudentClient obj2) throws RemoteException;

}
