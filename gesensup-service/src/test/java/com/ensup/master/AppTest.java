package com.ensup.master;

import org.mockito.Mockito;
import com.ensup.master.daoImpl.CourseDao;
import com.ensup.master.daoImpl.StudentDao;
import com.ensup.master.daoImpl.UserDao;
import com.ensup.master.metier.Course;
import com.ensup.master.metier.Student;
import com.ensup.master.metier.User;
import com.ensup.master.serviceImpl.CourseService;
import com.ensup.master.serviceImpl.StudentService;
import com.ensup.master.serviceImpl.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	StudentDao dao = Mockito.mock(StudentDao.class);
	UserDao	userDao = Mockito.mock(UserDao.class);
	CourseDao courseDao = Mockito.mock(CourseDao.class);
	
	StudentService service = new StudentService(dao);
	UserService userService = new UserService(userDao);
	CourseService courseService = new CourseService(courseDao);

	@Test
	public void StudentAllListCheck() {

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15"));

		Mockito.when(dao.readAllStudent()).thenReturn(list);

		List<Student> students = service.readAllStudent();

		Assertions.assertEquals(students.get(0).getFirstName(), "OBERLE");
		Mockito.verify(dao).readAllStudent();

	}

	@Test
	public void studentCreationCheck() {

		Student student = new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15");

		Mockito.doNothing().when(dao).createStudent(Mockito.isA(Student.class));
		service.createStudent(student);

		Mockito.verify(dao).createStudent(student);
	}

	@Test
	public void studentUpdateCheck() {
		Student student = new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15");

		Mockito.doNothing().when(dao).updateStudent(Mockito.isA(Student.class));
		service.updateStudent(student);

		Mockito.verify(dao).updateStudent(student);
	}

	@Test
	public void studentDeleteCheck() {

		Student student = new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15");

		Mockito.doNothing().when(dao).deleteStudent(Mockito.isA(Integer.class));
		service.deleteStudent(student.getId());

		Mockito.verify(dao).deleteStudent(student.getId());
	}

	@Test
	public void studentSelectCheck() {

		Student student = new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15");

		Mockito.when(dao.getStudent(1)).thenReturn(student);

		Student student1 = service.getStudent(1);

		Assertions.assertEquals(student1.getId(), 1);
		Mockito.verify(dao).getStudent(student.getId());
	}

	@Test
	public void studentCourseCreationCheck() {

		CourseDao courseDao = Mockito.mock(CourseDao.class);
		CourseService courseService = new CourseService(courseDao);

		Student student = new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15");
		String course ="JAVA";

		Mockito.doNothing().when(courseDao).associateCourse(Mockito.isA(String.class), Mockito.isA(Integer.class));
		courseService.associateCourse(course, student.getId());

		Mockito.verify(courseDao).associateCourse(course, student.getId());
	}
	
	@Test
	public void getStudentEmailCheck() {

		Student student = new Student(1, "OZDOK", "Bertrand", "ozdok@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15");

		Mockito.when(dao.getStudentByEmail("ozdok@yahoo.fr")).thenReturn(student);

		Student student1 = service.getStudentByEmail("ozdok@yahoo.fr");

		Assertions.assertEquals(student1.getMailAdresse(), "ozdok@yahoo.fr");
		Mockito.verify(dao).getStudentByEmail("ozdok@yahoo.fr");

	}
	
	@Test
	public void StudentAllResearchFirstNameAndLastnameCheck() {

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15"));

		Mockito.when(dao.getStudentByResearch("OBERLE", "François")).thenReturn(list);

		List<Student> students = service.getStudentByResearch("OBERLE", "François");

		Assertions.assertEquals(students.get(0).getFirstName(), "OBERLE");
		Mockito.verify(dao).getStudentByResearch("OBERLE", "François");

	}
	
	@Test
	public void StudentAllResearchCheckFirstNameOnly() {

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15"));

		Mockito.when(dao.getStudentByResearch("OBERLE", "")).thenReturn(list);

		List<Student> students = service.getStudentByResearch("OBERLE", "");

		Assertions.assertEquals(students.get(0).getFirstName(), "OBERLE");
		Mockito.verify(dao).getStudentByResearch("OBERLE", "");

	}
	
	@Test
	public void StudentAllResearchCheckLastNameOnly() {

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15"));

		Mockito.when(dao.getStudentByResearch("", "François")).thenReturn(list);

		List<Student> students = service.getStudentByResearch("", "François");

		Assertions.assertEquals(students.get(0).getFirstName(), "OBERLE");
		Mockito.verify(dao).getStudentByResearch("", "François");

	}
	
	@Test
	public void StudentAllResearchCheckWithoutFirstNameAndLastName() {

		ArrayList<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "OBERLE", "François", "françois@yahoo.fr", "Saint cyr 10 rue des fermiers",
				"01 02 03 04 05", "1999-20-15"));

		Mockito.when(dao.getStudentByResearch("", "")).thenReturn(list);

		List<Student> students = service.getStudentByResearch("", "");

		Assertions.assertEquals(students.get(0).getFirstName(), "OBERLE");
		Mockito.verify(dao).getStudentByResearch("", "");

	}

	@Test
	public void getUserCheck() {

		User user = new User(1, "admin", "admin", "D");

		Mockito.when(userDao.getUser("admin", "admin")).thenReturn(user);

		User user1 = userService.getUser("admin", "admin");

		Assertions.assertEquals(user1.getLogin(), "admin");
		Mockito.verify(userDao).getUser("admin", "admin");

	}

	@Test
	public void StudentAllCourseCheck() {

		ArrayList<Course> list = new ArrayList<Course>();
		list.add(new Course("JAVA", 120));

		Mockito.when(courseDao.getAllCourses()).thenReturn(list);

		List<Course> courses = courseService.getAllCourses();

		Assertions.assertEquals(courses.get(0).getNumberHours(), 120);
		Mockito.verify(courseDao).getAllCourses();

	}
}
