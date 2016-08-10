package edu.gatech.lei.DbWeb.DataObjs;

import java.util.ArrayList;
import java.util.Collections;

public class Student {
	
	private final static int MAX_STUDENTS = 400;
	private int studentId;
	private ArrayList<Integer> courseRequest = new ArrayList<Integer>();
	private int latestRequestId;
	private ArrayList<Integer> oldRequestIds = new ArrayList<Integer>();
	private String studentName;
	private int transcriptId;
	private ArrayList<String> transcript = new ArrayList<String>();
	private int requestPriority;
	
	/*
	 * Constructor for Student class
	 */
	public Student(int id){
		if(id >= 0 && id <= MAX_STUDENTS){
			studentId = id;
		} else {
			System.out.println("***ERROR***: Invalid student Id.");
		}
	}
	
	//Setter methods
	public void requestCourses(ArrayList<Integer> request){
		if(!request.isEmpty()){
			courseRequest = request;
		} else{
			courseRequest = new ArrayList<Integer>();
		}
	}

	public void addRequest(int courseId){
		courseRequest.add(courseId);
		
		requestPriority = (int) System.currentTimeMillis();
	}
	
	//Getter methods
	public int getStudentId(){
		return studentId;
	}
	
	public String getCourseRequestsAsString(){
		String ret = "";
		for(int i = 0; i < courseRequest.size(); i++){
			ret = ret + "S" + this.getStudentId() + "C" + courseRequest.get(i) + ",";
		}
		ret = ret + ";";
		return ret;
	}
	
	public ArrayList<Integer> getCourseRequest(){
		return courseRequest;
	}
	
	//Subtracter methods
	public void removeRequest(int courseId){
		if(courseRequest.contains(courseId)){
			courseRequest.remove(courseId);
		} else {
			System.out.println("***ERROR***: Invalid cannot remove designated course from course request because it is not currently requested.");
		}
	}
	
	public void removeAllRequests(){
		courseRequest.clear();
	}
	
	public void sortRequest(){
		Collections.sort(courseRequest);
	}
	
	public void setName(String name){
		studentName = name;
	}
	
	public void setTranscriptId(int id){
		transcriptId = id;
	}
	
	public String getName(){
		return studentName;
	}
	
	public int getTranscriptId(){
		
		return transcriptId;
	}
	
	public int getRequestPriority(){
		return requestPriority;
	}
	
	public void setRequestPriority(int priority){
		requestPriority = priority;
	}

	public String toString(){
		String output = "id: " + this.getStudentId() +"\n";
		output += "name: " + this.getName() +"\n";
		output += "requests: " + this.getCourseRequestsAsString() +"\n";
		output += "priority: " + this.getRequestPriority() + "\n";
		return output;
	}
	
	public void setTranscript(ArrayList<String> transcript){
		this.transcript = transcript;
	}
	
	public ArrayList<String> getTranscript(){
		return this.transcript;
	}
	
	public void addToTranscript(String entry){
		this.transcript.add(entry);
	}

	public ArrayList<Integer> getTranscriptAsIntegers(){
		ArrayList<Integer> ret = new ArrayList<Integer>();

		for(String entry : this.transcript){
			String course[] = entry.split("_");
			if(course.length > 2) {
				ret.add(Integer.parseInt(course[1]));
			}
			else{
				System.out.printf("Warnign, empty transcript for student " +  studentId);
			}
		}

		return ret;
	}

	public int getLatestRequestId(){
		return latestRequestId;
	}

	public ArrayList<Integer> getOldRequestIds(){
		return oldRequestIds;
	}
	
	public void addToOldRequestIds(int requestId){
		oldRequestIds.add(requestId);
	}
	
	public void updateLatestRequestId(int requestId){
		oldRequestIds.add(this.latestRequestId);
		this.latestRequestId = requestId;
	}

	public void setRequestId(int requestId){
		this.latestRequestId = requestId;
	}
}