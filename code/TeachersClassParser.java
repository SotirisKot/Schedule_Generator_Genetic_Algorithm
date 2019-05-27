//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196

import java.util.ArrayList;
import javax.json.JsonArray;
import javax.json.JsonObject;

public class TeachersClassParser {
	private ArrayList<TeachersClass> teachers;


	public TeachersClassParser() {}

	//creates a json array and then the teachers array.
	public TeachersClassParser(JsonObject json){
		this.teachers=new ArrayList<>();
		JsonArray ProfessorsArray = json.getJsonArray("Teachers");
		if (null != ProfessorsArray) {
            int numOfProf = ProfessorsArray.size();
            for (int i = 0; i < numOfProf; i++) {
                JsonObject item = ProfessorsArray.getJsonObject(i);
                if (null != item) {
                    this.teachers.add(new TeachersClass(item));
                }
            }
        }
        else {
            JsonObject item = json.getJsonObject("Teachers");
            if (null != item) {
            	teachers.add(new TeachersClass(item));
            }
        }

	}
	
	public ArrayList<TeachersClass> getTeachers() {
        return this.teachers;
    }//return the teachers array.

    public void setTeachers(ArrayList<TeachersClass> teachers) {
        this.teachers = teachers;
    }//setter.

}
