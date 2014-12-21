package javarmi.project;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CourseListServerTest {

	@Test
	public void addCourseListTest() {
		CourseListServer test1=new CourseListServer();
		test1.addCourses();//adding courses for 3 departments
		assertEquals(3,test1.deptcourses.size());//checking whether all courses are added to departments
		//fails when number of values added is not equal to size
	}
	
	@Test
	public void getCourseListTest(){
	CourseListServer test2=new CourseListServer();
	test2.addCourses();//adding courses
	int id=2;
	ArrayList<String> courselist=test2.getCourseList(id);//Retrieving courses and assigning to variable
	//check for the retrieval of courselist has contents or not
	assertNotNull(courselist);//fails when it has null
	}
	
}
