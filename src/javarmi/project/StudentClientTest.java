package javarmi.project;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class StudentClientTest {

	@Test
	public void deptListTest() {
		ArrayList<String> deptlist = new ArrayList<String>();
		//adding departments
		deptlist.add("1.Computer Science");
		deptlist.add("2.Biology");
		deptlist.add("3.MIS");
		//validating the method using user inputs
		int id = StudentClient.deptList(deptlist);
		assertNotNull(id);//check for not null
	}
	
	@Test
	public void courseListTest(){
		ArrayList<String> courselist = new ArrayList<String>();
		//adding courses related to computer science
		courselist.add("1.Algorithms-2");
		courselist.add("2.Database Systems");
		//validating the method using user inputs
		int num = StudentClient.courseList(courselist);
		assertNotNull(num);//check for not null
	}
	
	@Test
	public void profListTest(){
		ArrayList<String> proflist = new ArrayList<String>();
		//adding professors related to Database Systems
		proflist.add("1.Prasad Sistla");
		proflist.add("2.Ouir Wolfson");
		//validating the method for display
		StudentClient.prof(proflist);
		assertEquals(2,proflist.size());//check for addition of all professors to list
	}

}
