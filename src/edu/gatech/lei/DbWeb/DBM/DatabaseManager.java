package edu.gatech.lei.DbWeb.DBM;

import java.sql.*;
import java.util.ArrayList;

import edu.gatech.lei.DbWeb.DataObjs.Course;
import edu.gatech.lei.DbWeb.DataObjs.Student;

public class DatabaseManager {
	//JDBC driver name and database url
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=convertToNull";
	static final String DB_URL2 = "jdbc:mysql://localhost:3306/ARMS?zeroDateTimeBehavior=convertToNull";

	//Database credentials
	static final String USER = "arms";
	static final String PASSWORD = "arms";
	
	//Connection to DB
	Connection conn = null;
	
	public DatabaseManager(){
		
		Statement stmt = null;
		try{
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("CONNECTED");			
			//Check if this database already exists. If exists returns a name for a column, then it exists.
			ResultSet exists = conn.getMetaData().getCatalogs();
			boolean dbExists = false;
			while(exists.next()){
				String dbName = exists.getString(1);
				if(dbName.equals("ARMS")){
					dbExists = true;
				}
			}
			
			//If nothing was returned, the db hasn't been created yet and we need to initialize it.
			if(!dbExists){
				System.out.println("CREATING DATABASE");
				stmt = conn.createStatement();
				String sql = "CREATE DATABASE ARMS";
				stmt.execute(sql);
				stmt.close();
				System.out.println("INITIALIZING...");
				initializeDB();
			}
			conn.close();
			conn = DriverManager.getConnection(DB_URL2, USER, PASSWORD);
			System.out.println("CONNECTED TO ARMS DB");
			exists.close();
		} catch(SQLException sqlErr){
			sqlErr.printStackTrace();
		} catch(Exception err){
			err.printStackTrace();
		}
	}
	void initializeDB(){
		try{
			Statement stmt = conn.createStatement();
			stmt.execute("USE ARMS");
			//Build the structure of the DB
			stmt.execute("CREATE TABLE Users(UserName VARCHAR(255) NOT NULL, Password VARCHAR(255), PRIMARY KEY (UserName));");
			stmt.execute("CREATE TABLE Administrators(AdminId INT NOT NULL, UserName VARCHAR(255), PRIMARY KEY (AdminId), FOREIGN KEY (UserName) REFERENCES Users(UserName));");
			stmt.execute("CREATE TABLE Policy(PolicyId INT NOT NULL, MaxClassSize INT, MaxCourseSem INT, PRIMARY KEY (PolicyId));");
			stmt.execute("CREATE TABLE Courses(CourseId INT NOT NULL, CourseName VARCHAR(255), MaxSize INT, PRIMARY KEY (CourseId));");
			stmt.execute("CREATE TABLE Terms(TermId INT NOT NULL, TermName VARCHAR(255), PRIMARY KEY (TermId));");
			stmt.execute("CREATE TABLE CourseConstraints(CourseId INT NOT NULL, CourseAvailability VARCHAR(255), CoursePrerequisites VARCHAR(255), PolicyId INT, FOREIGN KEY (CourseId) REFERENCES Courses(CourseId), FOREIGN KEY (PolicyId) REFERENCES Policy (PolicyId));");
			stmt.execute("CREATE TABLE Transcript(TranscriptId INT NOT NULL, TranscriptDetail VARCHAR(2048), PRIMARY KEY (TranscriptId));");
			stmt.execute("CREATE TABLE CourseRequests(RequestId INT NOT NULL, StudentId INT, CourseId INT, TermId INT, TimeStamp INT, PRIMARY KEY (RequestId), FOREIGN KEY (CourseId) REFERENCES Courses(CourseId), FOREIGN KEY (TermId) REFERENCES Terms(TermId));"); //FOREIGN KEY (StudentId) REFERENCES Student(StudentId)
			stmt.execute("CREATE TABLE Students(StudentId INT NOT NULL, StudentName VARCHAR(255), TranscriptId INT, UserName VARCHAR(255), RequestId INT, PRIMARY KEY (StudentId), FOREIGN KEY (TranscriptId) REFERENCES Transcript(TranscriptId), FOREIGN KEY (UserName) REFERENCES Users (UserName), FOREIGN KEY (RequestId) REFERENCES CourseRequests(RequestId));");
			stmt.execute("CREATE TABLE LoginEvent(LogId INT NOT NULL, UserName VARCHAR(255), EventTime INT, EventType VARCHAR(255), EventDetail VARCHAR(255), PRIMARY KEY (LogId) );");
			stmt.execute("CREATE TABLE Schedules(ScheduleId INT NOT NULL, RequestId INT, PolicyId INT, Schedule VARCHAR(2048), PRIMARY KEY (ScheduleId), FOREIGN KEY (PolicyId) REFERENCES Policy(PolicyId));"); // FOREIGN KEY (RequestId) REFERENCES CourseRequests (RequestId)
			
			//Populate with initial data
			//Courses
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (1, 'CS 6210 Advanced Operating Systems', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (2, 'CS 6250 Computer Networks', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (3, 'CS 6300 Software Development Process', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (4, 'CS 7641 Machine Learning', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (5, 'CS 6290 High Performance Computer Architecture', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (6, 'CS 6310 Software Architecture and Design', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (7, 'CS 6440 Intro to Health Informatics', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (8, 'CS 6505 Computability, Complexity and Algorithms', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (9, 'CS 7637 Knowledge-Based Artificial Intelligence', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (10, 'CS 4495 Computer Vision', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (11, 'CS 6475 Computational Photography', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (12, 'CS 8803 Special Topics: Operating Systems', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (13, 'CS 8803 Special Topics: AI for Robotics', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (14, 'CS 6035 Introduction to Information Security', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (15, 'CS 6220 High-Performance Computing', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (16, 'CS 7646 Machine Learning for Trading', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (17, 'CS 7646 Special Topics: Reinforcement Learning', 250);");
			stmt.execute("INSERT INTO Courses(CourseId, CourseName, MaxSize) VALUES (18, 'CS 8803 Special Topics: Big Data', 250);");

			//Policy
			stmt.execute("INSERT INTO Policy (PolicyId, MaxClassSize, MaxCourseSem) VALUES (0, 250, 2);");

			//CourseConstraints
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (1, '1', 'NONE', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (2, '1,2,3', 'NONE', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (3, '1,2,3', 'NONE', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (4, '1,2,3', 'NONE', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (5, '2', '1,2', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (6, '1,2,3', '3', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (7, '1', 'NONE', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (8, '1,2,3', 'NONE', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (9, '1,2,3', 'NONE', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (10, '2', '1', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (11, '1', '10', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (12, '1,2,3', '1,8', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (13, '1,2,3', '4,9,10', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (14, '2', '2', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (15, '1', '1,5,6', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (16, '2', '4', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (17, '1', '13,16', 0);");
			stmt.execute("INSERT INTO CourseConstraints(CourseId, CourseAvailability, CoursePrerequisites, PolicyId) VALUES (18, '2', '9', 0);");

			//Users
			stmt.execute("INSERT INTO Users (UserName, Password) VALUES ('Administrator', 'adminPassword');");
			stmt.execute("INSERT INTO Users (UserName, Password) VALUES ('John', 'password');");
			stmt.execute("INSERT INTO Users (UserName, Password) VALUES ('Mary', 'password');");
			stmt.execute("INSERT INTO Users (UserName, Password) VALUES ('Susan', 'password');");
			stmt.execute("INSERT INTO Users (UserName, Password) VALUES ('Steve', 'password');");
			stmt.execute("INSERT INTO Users (UserName, Password) VALUES ('Matthew', 'password');");

			//Administrator
			stmt.execute("INSERT INTO Administrators (AdminId, UserName) VALUES (0, 'Administrator');");
			
			//Terms
			stmt.execute("INSERT INTO Terms (TermId, TermName) VALUES (1, 'Fall');");
			stmt.execute("INSERT INTO Terms (TermId, TermName) VALUES (2, 'Spring');");
			stmt.execute("INSERT INTO Terms (TermId, TermName) VALUES (3, 'Summer');");
			
			//Transcript
			stmt.execute("INSERT INTO Transcript (TranscriptId, TranscriptDetail) VALUES (1, '1_0_0');");
			stmt.execute("INSERT INTO Transcript (TranscriptId, TranscriptDetail) VALUES (2, '1_0_0');");
			stmt.execute("INSERT INTO Transcript (TranscriptId, TranscriptDetail) VALUES (3, '1_0_0');");
			stmt.execute("INSERT INTO Transcript (TranscriptId, TranscriptDetail) VALUES (4, '1_0_0');");
			stmt.execute("INSERT INTO Transcript (TranscriptId, TranscriptDetail) VALUES (5, '1_0_0');");
			
			//CourseRequests
			stmt.execute("INSERT INTO CourseRequests (RequestId, StudentId, CourseId, TermId, TimeStamp) VALUES (1, 1, 1, 1, 1);");
			stmt.execute("INSERT INTO CourseRequests (RequestId, StudentId, CourseId, TermId, TimeStamp) VALUES (2, 2, 1, 1, 1);");
			stmt.execute("INSERT INTO CourseRequests (RequestId, StudentId, CourseId, TermId, TimeStamp) VALUES (3, 3, 1, 1, 1);");
			stmt.execute("INSERT INTO CourseRequests (RequestId, StudentId, CourseId, TermId, TimeStamp) VALUES (4, 4, 1, 1, 1);");
			stmt.execute("INSERT INTO CourseRequests (RequestId, StudentId, CourseId, TermId, TimeStamp) VALUES (5, 5, 1, 1, 1);");
			
			
			//LoginEvent
			stmt.execute("INSERT INTO LoginEvent (LogId, UserName, EventTime, EventType, EventDetail) VALUES (0, 'Administrator', 0, 'Successful Login', 'Administrator successfully logged in at 0.');");
			
			//Students
			stmt.execute("INSERT INTO Students (StudentId, StudentName, TranscriptId, UserName, RequestId) VALUES (1, 'John', 1, 'John', 1);");
			stmt.execute("INSERT INTO Students (StudentId, StudentName, TranscriptId, UserName, RequestId) VALUES (2, 'Mary', 2, 'Mary', 2);");
			stmt.execute("INSERT INTO Students (StudentId, StudentName, TranscriptId, UserName, RequestId) VALUES (3, 'Susan', 3, 'Susan', 3);");
			stmt.execute("INSERT INTO Students (StudentId, StudentName, TranscriptId, UserName, RequestId) VALUES (4, 'Steve', 4, 'Steve', 4);");
			stmt.execute("INSERT INTO Students (StudentId, StudentName, TranscriptId, UserName, RequestId) VALUES (5, 'Matthew', 5, 'Matthew', 5);");

			System.out.println("INITIALIZE COMPLETE!");
			stmt.close();
		} catch(SQLException sqlErr){
			sqlErr.printStackTrace();
		} catch(Exception err){
			err.printStackTrace();
		}
		return;
	}
}