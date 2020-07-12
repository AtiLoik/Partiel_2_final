package com.ensup.master.serviceImpl;

import java.util.List;

import com.ensup.master.dao.ICourseDao;
import com.ensup.master.daoImpl.CourseDao;
import com.ensup.master.metier.Course;

/**
 * CourseService
 * 
 * @author DANON
 *
 */
public class CourseService {

	ICourseDao idao;
	CourseDao dao;
	
	public CourseService(CourseDao dao) {
		this.dao = dao;
	}

	public CourseService() {
		super();
		dao = new CourseDao();
	}

	/**
	 * associate course 
	 * @param course
	 * @param id
	 */
	public void associateCourse(String course, int id) {
		dao.associateCourse(course, id);
	}

	public List<Course> getAllCourses() {		
		return dao.getAllCourses();
	}

}
