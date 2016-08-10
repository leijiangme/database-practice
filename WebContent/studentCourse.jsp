<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student: Main Page</title>
</head>
<body>
	<div style="width: 800px; margin: 0 auto;">
		<h2>Student: Main Page</h2>
		<div style="float: right">
			<form align="right" name="logout" method="post" action="index.jsp">
				<input
					style="font-size: 10pt; color: white; background-color: #ff5733; padding: 3px"
					type="submit" value="Logout">
			</form>
		</div>
		<br>
		<br>
		<hr>
		<div align="center">
			<fieldset style="width: 500px background: #F9EECF">
				<legend>Click the button to view:</legend>
				<br>
				<form name="coursesForm" method="post" action="ShowCourses">
					<input type="submit"
						style="height: 50px; font-size: 20px; width: 495px; color: white; background-color: #669999; padding: 3px"
						value="Course Catalog" />
				</form>
				<br>
				<hr>
				<br>
				<form action="studentRequest.jsp">
					<input type="submit"
						style="height: 50px; font-size: 20px; width: 495px; color: white; background-color: #669999; padding: 3px"
						value="Schedule Requests" />
				</form>
				<br>
				<hr>
				<br>
				<form name="latestRSForm" method="post" action="ShowLatestRS">
					<input type="submit"
						style="height: 50px; font-size: 20px; width: 495px; color: white; background-color: #669999; padding: 3px"
						value="View Schedule Request History">
				</form>
				<br>
		</div>
		</fieldset>
	</div>


	</div>
</body>
</html>