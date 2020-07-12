package servlets;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
 * Servlet implementation class SupprimerEtudiantServlet
 */
public class SupprimerEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService;
	private CourseService courseService;
	private RequestDispatcher dispatcher = null;
	private User user = null;

	/**
	 * Default constructor.
	 */
	public SupprimerEtudiantServlet() {
		studentService = new StudentService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		methode(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void methode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		String object = request.getParameter("id");
		int id = Integer.valueOf(object);
				
		studentService.deleteStudent(id);

		dispatcher = request.getRequestDispatcher("etudiant.jsp");
		
		user = (User) session.getAttribute("user");
		session.setAttribute("students", lister());
		session.setAttribute("courses", getAllCourses());
		session.setAttribute("student", null);
		
		
		session.setAttribute("message", "Suppression effectuée avec succès !!! ");
		dispatcher.forward(request, response);
	}

	private List<Student> lister() {

		List<Student> students = Collections.emptyList();
		try {
			if(user.getProfil().equalsIgnoreCase("D")) {
			students = studentService.readAllStudent();
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
