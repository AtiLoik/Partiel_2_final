package com.ensup.master;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;

import com.ensup.master.daoImpl.StudentDao;
import com.ensup.master.metier.Student;
import com.ensup.master.serviceImpl.StudentService;


@TestInstance(Lifecycle.PER_CLASS)
public class TestStudentService {
	/**
	 * Test unitaire de la classe StudentService
	 */
	private StudentService studentService ;
	private Student student;
	private StudentDao dao;
	private ArrayList<Student> listeStudent =null;
	
	@BeforeAll
	
	public void SetUp() throws InterruptedException {
		System.out.println("On initialise les ressources");
		// 1 Mocker le Dao 
		 dao = Mockito.mock(StudentDao.class);
		// 2 Créer le Service 
		studentService = new StudentService(dao);
		// 3a. Imposer un comportement au mock (stubbing)
		Student stuTest = new Student(1, "Doublas", "Mbiandou", "douglas.mbiandou@gmail.com", "", "", null);
		
		// popup liste etudiant
		listeStudent = new ArrayList<Student>();
		listeStudent.add(stuTest);
		for (int i = 1; i < 5; i++) {
			listeStudent.add(new Student(i, " Student - " +i, " Name - "+i, null, null, null, null));
		}
		
		
		// Lecture 
		Mockito.when(dao.getStudent(1)).thenReturn(stuTest);
		// get User by Email
		Mockito.when(dao.getUser("douglas.mbiandou@gmail.com")).thenReturn(stuTest);
		
		// get List Etudiant 
		Mockito.when(dao.readAllStudent()).thenReturn(listeStudent);
		
		
	}

	
@Test
public void TestLectureStudent() {
	System.out.println("TestLectureStudent");
	
	// 3.b Appeler une m�thode du service (r�sultat r�el)

			student = studentService.getStudent(1);

			// 4. Comparer le r�sultat r�el avec le r�sultat attendu

			Assertions.assertEquals(student.getId(), 1);
			Mockito.verify(dao).getStudent(1);
	
}


@Test
public void TestGetUserByEmail() {
	
	System.out.println("TestGetUserByEmail");
	
	// 3.b Appeler une m�thode du service (r�sultat r�el)

			student = studentService.getUser("douglas.mbiandou@gmail.com");

			// 4. Comparer le r�sultat r�el avec le r�sultat attendu

			Assertions.assertEquals(student.getMailAdresse(), "douglas.mbiandou@gmail.com");
			Mockito.verify(dao).getUser("douglas.mbiandou@gmail.com");
	
}

@Test
public void TestReadAllStudent() {
	System.out.println("TestReadAllStudent");
	
	// 3.b Appeler une m�thode du service (r�sultat r�el)

			ArrayList<Student> listeTest = (ArrayList<Student>) studentService.readAllStudent();

			// 4. Comparer le r�sultat r�el avec le r�sultat attendu

			Assertions.assertEquals(listeTest.size(), 5);
			Mockito.verify(dao).readAllStudent();
	
}


@AfterAll
public void TearDown() {
	dao = null;
	student = null;
	studentService = null;
	System.out.println("On libère les ressources");
}
}
