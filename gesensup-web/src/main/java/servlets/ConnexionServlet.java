package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

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
import com.ensup.master.serviceImpl.UserService;

/**
 * Servlet implementation class ConnexionServlet
 */
@WebServlet("/Connexion")
public class ConnexionServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService;
	private StudentService studentService;
	private CourseService courseService;
	private RequestDispatcher dispatcher = null;

	/**
	 * Default constructor.
	 */
	public ConnexionServlet() {
		userService = new UserService();
		courseService = new CourseService();
		studentService = new StudentService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatcher = request.getRequestDispatcher("index.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		methode(request, response);
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void methode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		User user = userService.getUser(request.getParameter("login"), request.getParameter("password"));

		// page de retour
		
		if (user != null && user.getLogin().equalsIgnoreCase(request.getParameter("login"))
				&& user.getPassword().equalsIgnoreCase(request.getParameter("password"))) {
			
			dispatcher = request.getRequestDispatcher("home.jsp");
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("students", lister());
			session.setAttribute("courses", getAllCourses());

		} else {
			dispatcher = request.getRequestDispatcher("index.jsp");
		}
		dispatcher.forward(request, response);
	}

	private List<Student> lister() {
		
		List<Student> students = Collections.emptyList();
		try {
			students = studentService.readAllStudent();
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
