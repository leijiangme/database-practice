package edu.gatech.lei.DbWeb.DataObjs;

//Imports
import java.util.ArrayList;
import java.util.Collections;

//Course class
public class Course {
	
	private final static int MAX_SEMESTERS = 12;
	private final static int MAX_COURSES = 18;
	
	//Course global variables
	private int courseId;
	private String courseName;
	private ArrayList<Integer> coursePrereqs;
	private ArrayList<Integer> courseSemesters;

	/*
	 * Class Constructor
	 */	
	public Course(int id){
		setCourseId(id);
		setCourseName("");
		courseSemesters = new ArrayList<Integer>();
		coursePrereqs  = new ArrayList<Integer>();
	}
	
	// Getter methods
	public int getCourseId(){
		return courseId;
	}
	
	public String getCourseName(){
		return courseName;
	}
	
	public ArrayList<Integer> getAvailableSemesters(){
		return courseSemesters;
	}
	
	public ArrayList<Integer> getCoursePrereqs(){
		return coursePrereqs;
	}
	
	//Setter methods
	public void setCourseId(int newId){
		if(newId > 0 && newId <= MAX_COURSES){
			courseId = newId;
		}
	}
	
	public void setCourseName(String newName){
		if(!newName.equalsIgnoreCase("") && !newName.isEmpty()){
			courseName = newName;
		}
	}

	//Adder methods
	public void addAvailableSemester(int newSemester){
		if((newSemester >= 0) && (newSemester <= MAX_SEMESTERS) && (!courseSemesters.contains(newSemester))){
			courseSemesters.add(newSemester);
		}
	}

	public void addCoursePrereq(int newPrereq){
		if(newPrereq > 0 && newPrereq <= MAX_COURSES && !coursePrereqs.contains(newPrereq)){
			coursePrereqs.add(newPrereq);
		}
	}

	//Subtracter methods
	public void removeAvailableSemester(int semester){
		if(courseSemesters.contains(semester)){
			courseSemesters.remove(semester);
		}
	}

	public void removeCoursePrereq(int prereqs){
		if(coursePrereqs.contains(prereqs)){
			coursePrereqs.remove(prereqs);
		}
	}
	
	public void removeAllCoursePrereqs(){
		coursePrereqs.clear();
	}

	public void sortCourseInfo(){
		Collections.sort(coursePrereqs);
		Collections.sort(courseSemesters);
	}

	public String toString(){
		String output = "id: " + this.getCourseId() +"\n";
		output += "name: " + this.getCourseName() +"\n";
		ArrayList<Integer> prereqs = new ArrayList<Integer>();
        for (Integer pr : this.getCoursePrereqs()) {
            prereqs.add(pr);
        }
		if(prereqs.size()>0) {
			output += "prereqs: " + prereqs.toString() + "\n";
		} else {
			output += "prereqs: None";
		}
		ArrayList<Integer> semesters = new ArrayList<Integer>();
		for(Integer pr: this.getAvailableSemesters()){
			semesters.add(pr);
		}
		output += ": " + semesters.toString() + "\n";
		return output;
	}
}