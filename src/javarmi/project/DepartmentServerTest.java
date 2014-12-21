package javarmi.project;

import static org.junit.Assert.*;

import org.junit.Test;

public class DepartmentServerTest {

	@Test
	public void addDepartmentTest() {
		DepartmentServer test1=new DepartmentServer();
		test1.addDepartments();//adding departments
		assertEquals(3,test1.departments.size());//fails when departments are not added properly
	}
	
	@Test
	public void getDeptTest(){
		DepartmentServer test2=new DepartmentServer();
		//test2.addDepartments();
		assertNotNull(test2.getDepartmentList());//fails when we have null in the list
	}

}
