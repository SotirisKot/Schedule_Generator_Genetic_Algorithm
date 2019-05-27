//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196

import javax.json.JsonObject;

public class LessonsClass {
	private String course_code;
	private String course_name;
	private String Classroom;
	private int hours;
	
	public LessonsClass(){}
	
	public LessonsClass(JsonObject json){
		this.course_code=json.getString("course_code");
		this.course_name=json.getString("course_name");
		this.Classroom=json.getString("Classroom");
		this.hours= json.getInt("hours");
		
	}
	
	//GETTERS FOR EACH LESSON
	//course code
	public  String getCourseCode(){
		return this.course_code;
	}
	//course name
	public String getCourseName(){
		return this.course_name;
	}
	//course classroom
	public  String getClassroom(){
		return this.Classroom;
	}
	//course hours
	public int getHours(){
		return this.hours;
	}
	//SETTERS FOR EACH LESSON
	//course code
	public void setCourseCode(String course_code){
		this.course_code=course_code;
	}
	//course name
	public void setCourseName(String course_name){
		this.course_name=course_name;
	}
	//course classroom
	public void setClassroom(String Classroom){
		this.Classroom=Classroom;
	}
	//course hours
	public void setHours(int hours){
		this.hours=hours;
	} 
	
}
