package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ensup.master.metier.Student;
import com.ensup.master.metier.User;
import com.ensup.master.serviceImpl.StudentService;

/**
 * Servlet implementation class RechercheEtudiantServlet
 */
public class RechercheEtudiantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user = null;
	private StudentService studentService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RechercheEtudiantServlet() {
		super();
		studentService = new StudentService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		session.setAttribute("student", null);
		user = (User) session.getAttribute("user");
		List<Student> students = studentService.getStudentByResearch(request.getParameter("firstNameR"), request.getParameter("lastNameR"));

		session.setAttribute("students", students);
		RequestDispatcher dispatcher = null;
		dispatcher = request.getRequestDispatcher("etudiant.jsp");
		dispatcher.forward(request, response);
	}

}
