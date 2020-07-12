<%@page import="com.ensup.master.metier.Course"%>
<%@page import="com.ensup.master.metier.Student"%>

<%@page import="java.util.List"%>
<!-- header -->
<%@include file="header.jsp"%>
<!-- Navigation-->
<%@include file="menuhaut.jsp"%>

<div class="container">
	<%List<Course> listCours = (List<Course>) session.getAttribute("courses");%>
	<h1>Associé un cour a un etudiant</h1>
	<form action="EtudiantCoursServlet" method="post">
	<% Student student = (Student) session.getAttribute("student");%>
		<input type="text" id="mail" class="form-control" name="mail" value="<%=student.getMailAdresse()%>">
	  	<select name="listeCours" class="browser-default custom-select">
				<% 
				        for (Course course : listCours)
				        {
				         
				%>
					   <option name="cours" value="<%=course.getThemeCourse()%>"><%=course.getThemeCourse()%></option>
				<%
				        }
				%>
		</select>
			<br/> <br/>
			<div class="text-center mb-2">
					<button type="submit" class="btn btn-primary mb-4">Submit</button>
			</div>
</div>

<!-- footer -->
<%@include file="footer.jsp"%>