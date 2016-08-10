<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator: Course Catalog</title>
</head>
<body>
 <div style="width:1000px; margin:0 auto;">
            <h2>Administrator: Course Catalog</h2>
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
                    <form action="adminStudent.jsp">
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
            
            <div style="width:800px;float:right;">
                <form>
                    <label for="search">Search:</label>
                    <input type="search" name="searchFor" STYLE="background:#d6eaf8">
                    <label for="search">By:</label>
                    <select name="searchType">
                        <option value="so1">Course ID</option>
                        <option value="so2">Course Name</option>
                        <option value="so3">Semester</option>
                    </select>
                    <input type="submit">
                </form>
                <br>

                <form>
                    <table border="1" style="font-family:sans-serif; font-size:9pt;">
                        <tr>
                            <th>Course ID</th>
                            <th>Course Name</th>
                            <th>Semester Offered</th>
                            <th>Classroom Size</th>
                            <th>Remaining Seats</th>
                            <th></th>
                        </tr>
                        <tr>
                            <td width="20%" align="center"></td>
                            <td width="40%" align="center"></td>
                            <td width="20%" align="center"></td>
                            <td width="10%" align="center"></td>
                            <td width="10%" align="center"></td>
                            <td>
                                <input type="submit" value="Edit" formaction="adminConfigure.html">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
</body>
</html>