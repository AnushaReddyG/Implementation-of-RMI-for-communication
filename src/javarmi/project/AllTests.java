package javarmi.project;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ CourseListServerTest.class, DepartmentServerTest.class,
		ProfessorServerTest.class, StudentClientTest.class })
public class AllTests {

}
