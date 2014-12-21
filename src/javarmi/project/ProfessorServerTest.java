package javarmi.project;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfessorServerTest {

	@Test
	public void addProfTest() {
		ProfessorServer test1=new ProfessorServer();
		test1.addProf();//adding professors
		assertEquals(6,test1.Proflist.size());//fails when professor is not added properly
	}
		
		@Test
	public void getProfTest(){
		ProfessorServer test2=new ProfessorServer();
		test2.addProf();
		String coursename="Algos-1";//adding wrong course name
		assertNull(test2.getProfessorList(coursename));//fails when it contains any value
	}

}
