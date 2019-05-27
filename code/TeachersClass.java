//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196

import javax.json.JsonObject;

public class TeachersClass {
	private String teacher_code;
	private String teacher_name;
	private String course_code;
	private int max_hours_per_day;
	private int max_hours_per_week;
	
	
	public TeachersClass(){}
	
	public TeachersClass(JsonObject json) {
		this.teacher_code=json.getString("teacher_code");
		this.teacher_name=json.getString("teacher_name");
		this.course_code=json.getString("course_code");
		this.max_hours_per_day=json.getInt("max_hours_per_day");
		this.max_hours_per_week=json.getInt("max_hours_per_week");
	}
	
	//GETTERS FOR EACH TEACHER
	
	//teacher code
	public String getTeacher_code() {
		return teacher_code;
	}
	
	//teacher name
	public String getTeacher_name() {
		return teacher_name;
	}
	
	//teacher's course
	public String getCourse_code() {
		return course_code;
	}
	
	//max_hours_per_day
	public int get_max_hours_per_day() {
		return max_hours_per_day;
	}
	
	//max_hours_per_week
	public int get_max_hours_per_week() {
		return max_hours_per_week;
	}
	
	//SETTERS FOR EACH TEACHER
	//teacher's code
	public void setTeacher_code(String professor_code) {
		this.teacher_code= professor_code;
	}
	
	//teacher's name
	public void setTeacher_name(String professor_name) {
		this.teacher_name= professor_name;
	}
	
	//teacher's id
	public void set(String Pcourse_id) {
		this.course_code= Pcourse_id;
	}
	
	//max_hours_per_day
	public void set_max_hours_per_day(int max_hours_per_day) {
		this.max_hours_per_day= max_hours_per_day;
	}
	
	//max_hours_per_day
	public void set_max_hours_per_week(int max_hours_per_week) {
		this.max_hours_per_week= max_hours_per_week;
	}
	
}
