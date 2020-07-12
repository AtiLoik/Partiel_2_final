package com.ensup.master.dao;

import java.util.ArrayList;
import java.util.List;

import com.ensup.master.metier.Student;

public interface IStudentDao {

	public void createStudent(Student student);
	
	public Student getStudent(int  id);
	
	public Student getStudentByEmail(String email);
	
	public ArrayList<Student> readAllStudent();
	
	public void deleteStudent(int id);
	
	public void  updateStudent(Student student);
	
	public List<Student> getStudentByResearch(String firstName, String lastName);
}
