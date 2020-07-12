package com.ensup.master.dao;

import java.util.List;

import com.ensup.master.metier.Course;

/**
 * Interface CourseDao
 * 
 * @author DANON
 *
 */
public interface ICourseDao {

	public void associateCourse(String  course,int id);

	public List<Course> getAllCourses();
}
