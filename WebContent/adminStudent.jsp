<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator: User Management</title>
</head>
<body>
<div style="width: 1000px; margin: 0 auto;">
		<h2>Administrator: User Management</h2>
		     <div style="float:right">
                <form align="right" name="logout" method="post" action="index.jsp">
                    <input
                        style="font-size:10pt;color:white;background-color:#ff5733;padding:3px"
                        type="submit"
                        value="Logout">
                </form>
            </div>
            <br>
            <hr>

            <div style="width:160px; float:left;border:1px">
                <fieldset style="width:160px; height: 100%; background:#F9EECF">
                    <form name="usersForm" method="post" action="ShowUsers">
                        <input
                            type="submit"
                            style="height:40px; width:155px;color:white;background-color:#339966;padding:3px"
                            value="Student Management">
                    </form>
                    <form name="coursesForm" method="post" action="ShowCourses">
                        <input
                            type="submit"
                            style="height:40px; width:155px;color:white;background-color:#339966;padding:3px"
                            value="View Course Catalog"/>
                    </form>
                    <form action="adminConfigure.jsp">
                        <input
                            type="submit"
                            style="height:40px; width:155px;color:white;background-color:#339966;padding:3px"
                            value="Configuration Setting"/>
                    </form>
                    <form action="adminShadow.jsp">
                        <input
                            type="submit"
                            style="height:40px; width:155px;color:white;background-color:#339966;padding:3px"
                            value="Enter Shadow Mode"/>
                    </form>
                    <form action="adminViewRequest.jsp">
                        <input
                            type="submit"
                            style="height:40px; width:155px;color:white;background-color:#339966;padding:3px"
                            value="View Schedule Requests"/>
                    </form>
                     <form name="summaryForm" method="post" action="SummaryReport">
                        <input
                            type="submit"
                            style="height:40px; width:155px;color:white;background-color:#339966;padding:3px"
                            value="Summary Report"/>
                    </form>
                </fieldset>
            </div>

		<div style="width: 800px; float: right;">
			<form name="studentForm" method="post" action="StudentRegister">
				<table>
					<tr>
						<td>UserName:</td>
						<td><input type="text" name="susername"
							STYLE="background: #d6eaf8"></input></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input type="text" name="spassword"
							STYLE="background: #d6eaf8"></input></td>
					</tr>
				</table>
				<input type="submit" value="Register">
			</form>


			<hr />
			<form name="studentForm" method="post" action="StudentDelete">
				UserName:<br>
				<input type="text" name="delname" STYLE="background: #d6eaf8"/>
				<input type="submit" value="Delete">
			</form>
		</div>
	</div>

</body>
</html>