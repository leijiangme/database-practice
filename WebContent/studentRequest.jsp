<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student: Schedule Request</title>
</head>
<body>
  <div style="width:1000px; margin:0 auto;">
            <h2>Student: Schedule Request</h2>
            <div style="float: right">
                <form align="right" name="logout" method="post" action="index.jsp">
                    <input
                        style="font-size: 10pt; color: white; background-color: #ff5733; padding: 3px"
                        type="submit"
                        value="Logout">
                </form>
            </div>
            <br>
            <hr>
            <div style="width: 160px; float: left; border: 1px">
                <fieldset style="width: 160px; background: #F9EECF">
                    <form action="studentCourse.jsp">
                        <input
                            type="submit"
                            style="height: 40px; width: 155px; color: white; background-color: #339966; padding: 3px"
                            value="Main Menu"/>
                    </form>
                    <form action="studentRequest.jsp">
                        <input
                            type="submit"
                            style="height: 40px; width: 155px; color: white; background-color: #339966; padding: 3px"
                            value="Schedule Requests"/>
                    </form>
                </fieldset>
            </div>


            <div style="width:800px;float:right;">
                  <form name="requestsForm" method="post" action="ShadowModel">
                Input Request String:<br>
                <span style="color:blue"><font size="2">[Input string as: StudentId_CourseId_TermId (e.g. 1_2_2)]</font></span><br>
				<input type="text" name="requestID" STYLE="background: #d6eaf8"/><br>
				<input type="submit" value="View System-generated Results">
			</form>
        </div>
</body>
</html>