package com.ensup.master.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.ensup.master.metier.Student;

public class StudentDao {

	private String url = "jdbc:mysql://localhost/dougschool?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private String sql_login = "root";
	private String sql_password = "";

	/**
	 * Create a student
	 * 
	 * @param student
	 */
	public void createStudent(Student student) {
		Connection cn = null;
		Statement st = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();

			String sql = "INSERT INTO person (firstName, lastName, mailAdresse,adress,numberPhone,dateOfBirth) VALUES ('"
					+ student.getFirstName() + "', '" + student.getLastName() + "', '" + student.getMailAdresse()
					+ "', '" + student.getAdress() + "', '" + student.getNumberPhone() + "', '"
					+ student.getDateOfBirth() + "')";

			st.executeUpdate(sql);

			System.out.println("Etudiant créer");
			cn.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * read informations student by id
	 * 
	 * @param id
	 * @return student
	 */
	public Student getStudent(int id) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();

			String sql = "SELECT * FROM person WHERE id = '" + id + "'";

			rs = st.executeQuery(sql);

			if (rs.next()) {
				return new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("mailAdresse"), rs.getString("adress"), rs.getString("numberPhone"),
						rs.getString("dateOfBirth"));
			}
			cn.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * read informations student by email
	 * 
	 * @param email
	 * @return student
	 */
	public Student getStudentByEmail(String email) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();

			String sql = "SELECT * FROM person WHERE mailAdresse= '" + email + "'";

			rs = st.executeQuery(sql);

			if (rs.next()) {
				return (new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("mailAdresse"), rs.getString("adress"), rs.getString("numberPhone"),
						rs.getString("dateOfBirth")));
			}
			cn.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * read all students
	 * 
	 * @return list of students
	 */
	public ArrayList<Student> readAllStudent() {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		ArrayList<Student> students = new ArrayList<Student>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();

			String sql = "SELECT * FROM person";
			rs = st.executeQuery(sql);

			while (rs.next()) {
				students.add(new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("mailAdresse"), rs.getString("adress"), rs.getString("numberPhone"),
						rs.getString("dateOfBirth")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return students;
	}

	/**
	 * delete a student
	 * 
	 * @param id
	 */
	public void deleteStudent(int id) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null, rs1 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();
			
			String slq = "select * from studentcourse where idStudent = '"+id+"' ";
			
			rs = st.executeQuery(slq);
			if (rs.next()) {
				System.out.println("On ne peut pas supprimer");
			} else {
				String sql = "DELETE FROM person WHERE id = '" + id + "'";
				st.executeUpdate(sql);
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * update informations student
	 * 
	 * @param student
	 * @return student
	 */
	public void updateStudent(Student student) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			int resultat;
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();

			String sql = "UPDATE person set firstName ='" + student.getFirstName() + "', lastName= '"
					+ student.getLastName() + "', mailAdresse='" + student.getMailAdresse() + "'," + "	adress= '"
					+ student.getAdress() + "',numberPhone = '" + student.getNumberPhone() + "',dateOfBirth='"
					+ student.getLastName() + "' where id='" + student.getId() + "'";
			resultat = st.executeUpdate(sql);
			if (resultat == 0) {
				System.out.println("Aucune modification n'a était faite");
			} else {
				System.out.println("Modification éffectuée");

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				cn.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Research a student
	 * 
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public List<Student> getStudentByResearch(String firstName, String lastName) {
		Connection cn = null;
		Statement st = null;
		ResultSet rs = null;

		List<Student> students = new ArrayList<Student>();

		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, sql_login, sql_password);
			st = cn.createStatement();

			String sql = "SELECT * FROM person ";
			String sql1 = " firstName like '%" + firstName + "%' ";
			String sql2 = " lastName like '%" + lastName + "%' ";

			if (firstName != "" && lastName != "") {
				sql = sql + " where " + sql1 + " and " + sql2;
			} else if (firstName != "" && lastName == "") {
				sql = sql + " where " + sql1;
			} else if (firstName == "" && lastName != "") {
				sql = sql + " where " + sql2;
			}

			rs = st.executeQuery(sql);

			while (rs.next()) {

				students.add(new Student(rs.getInt("id"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("mailAdresse"), rs.getString("adress"), rs.getString("numberPhone"),
						rs.getString("dateOfBirth")));
			}
			cn.close();
			st.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return students;
	}
}
