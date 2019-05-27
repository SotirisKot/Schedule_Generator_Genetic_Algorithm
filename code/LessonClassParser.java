//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196


import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;

public class LessonClassParser {
	private ArrayList<LessonsClass> lessons;


	public LessonClassParser(){}//empty constructor.

    //creates a json array and then the lessons array.
	public LessonClassParser(JsonObject json){
		this.lessons=new ArrayList<>();
		JsonArray LessonsArray = json.getJsonArray("Lessons");
		if (null != LessonsArray) {
            int coursesLength = LessonsArray.size();
            for (int i = 0; i < coursesLength; i++) {
                JsonObject item = LessonsArray.getJsonObject(i);
                if (null != item) {
                    this.lessons.add(new LessonsClass(item));
                }
            }
        }
        else {
            JsonObject item = json.getJsonObject("Lessons");
            if (null != item) {
                lessons.add(new LessonsClass(item));
            }
        }

	}

	public ArrayList<LessonsClass> getLessons() {
        return this.lessons;
    }//return the lessons array.

    public void setCourses(ArrayList<LessonsClass> lessons) {
        this.lessons = lessons;
    }//setter.
}
