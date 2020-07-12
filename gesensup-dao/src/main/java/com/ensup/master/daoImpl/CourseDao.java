package com.ensup.master.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ensup.master.metier.Course;
import com.ensup.master.metier.User;

public class CourseDao {

	
	private String url = "jdbc:mysql://localhost/dougschool?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String sql_login = "root";
	private String sql_password = "";
	
	
	/**
	 * associate course 
	 * @param course
	 * @param id
	 */
	public void associateCourse(String course,int id) {
		
		Connection cn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			
			st = cn.createStatement();
			
			String sql = "INSERT INTO studentcourse (idStudent, themeCourse) VALUES ('"+ id +"', '"+course +"')";
		
			st.executeUpdate(sql);
			System.out.println("Cours associé");
			cn.close();
			st.close();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	public List<Course> getAllCourses() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		List<Course> courses = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			
			st = cn.createStatement();
			
			String sql = "SELECT * FROM `course`";
		
			rs = st.executeQuery(sql);
			courses = new ArrayList<Course>();
			while(rs.next()) {
				Course course = new Course(rs.getString("themeCourse"), rs.getInt("numberHours"));
				courses.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return courses;
	}
	
	
}
