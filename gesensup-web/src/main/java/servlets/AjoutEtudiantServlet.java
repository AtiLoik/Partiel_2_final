package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensup.master.metier.Course;
import com.ensup.master.metier.Student;
import com.ensup.master.metier.User;
import com.ensup.master.serviceImpl.CourseService;
import com.ensup.master.serviceImpl.StudentService;

/**
 * Servlet implementation class AjoutEtudiantServlet
 */
//@WebServlet("/AjoutEtudiant")
public class AjoutEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService;
	private RequestDispatcher dispatcher = null;
	private CourseService courseService;
	private User user = null;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjoutEtudiantServlet() {
		studentService = new StudentService();
		courseService = new CourseService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcher = request.getRequestDispatcher("etudiantAjout.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Student student = new Student(null, request.getParameter("firstName"), request.getParameter("lastName"),
				request.getParameter("mailAdresse"), request.getParameter("adress"),
				request.getParameter("numberPhone"), request.getParameter("dateOfBirth"));

		HttpSession session = request.getSession();
		session.setAttribute("student", null);
		user = (User) session.getAttribute("user");
		studentService.createStudent(student);

		session.setAttribute("students", lister(student));
		session.setAttribute("courses", getAllCourses());
		
		dispatcher = request.getRequestDispatcher("etudiant.jsp");
		dispatcher.forward(request, response);
	}

	private List<Student> lister(Student student) {

		List<Student> students = Collections.emptyList();
		try {
			if(user.getProfil().equalsIgnoreCase("D")) {
			students = studentService.readAllStudent();
			} else {
				students.add(student);
			}
		} catch (Exception e) {

		}
		return students;
	}

	private List<Course> getAllCourses() {

		List<Course> courses = Collections.emptyList();
		try {

			courses = courseService.getAllCourses();
		} catch (Exception e) {

		}
		return courses;
	}
}
