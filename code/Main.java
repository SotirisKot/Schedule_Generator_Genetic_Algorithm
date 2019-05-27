//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.*;

public class Main {
	private static ArrayList<LessonsClass> mainLessons;
	private static ArrayList<TeachersClass> mainTeachers;
	private static LessonClassParser mainLessonParser;
	private static TeachersClassParser mainTeacherParser;
	private static String mainLessonsJsonPath = "src\\Lessons.json";
	private static String mainTeachersJsonPath = "src\\Teachers.json";
	private static JsonReader mainJsonReader;
	private static JsonObject mainJsonObject;
	
	public static JsonObject getJsonObjectFromPath(String jsonPath){
		try {
			mainJsonReader = Json.createReader(new FileReader(jsonPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		mainJsonObject = mainJsonReader.readObject();
		mainJsonReader.close();
		return mainJsonObject;
		
	}
	public static void main(String[] args) {
        mainLessons = new ArrayList<>();
        mainTeachers = new ArrayList<>();
        mainLessonParser = new LessonClassParser(getJsonObjectFromPath(mainLessonsJsonPath));
        mainTeacherParser = new TeachersClassParser(getJsonObjectFromPath(mainTeachersJsonPath));
        mainLessons = mainLessonParser.getLessons();
        mainTeachers = mainTeacherParser.getTeachers();
        Population population = null;
        String[][][] SecondBestSchedule = null;
        boolean found = false;
        for(int i=0; i < 6; i++){//we try 6 times to find the best schedule.
            population = new Population(GeneticAlgorithmClass.POPULATION_SIZE).initializePopulation(mainTeachers, mainLessons);//we initialize our population.
            GeneticAlgorithmClass ga = new GeneticAlgorithmClass();
            System.out.println("-----------------------------------------------------------");
            System.out.println("Generation # 0" + "| Best score: " + population.getState()[0].getFitness());//the best score of the first population.
            int generationNumber=0;
            while(population.getState()[0].getFitness() < GeneticAlgorithmClass.SCORE_TO_ACHIEVE) {//we search for the SCORE_TO_ACHIEVE = 9.
                generationNumber++;
                System.out.println("--------------------------------------------------------");
                population = ga.evolvePopulation(population);//we evolve our population.
                population.sortStatesByFitness();//we sort our states(schedules) based on fitness.
                System.out.println("Generation # "+ generationNumber + "| Best score: " + population.getState()[0].getFitness() + " round: " + i);

                if(population.getState()[0].getFitness() >= 9){
                    found = true;
                }else if(population.getState()[0].getFitness() == 8){
                    SecondBestSchedule = population.getState()[0].getSchedule();
                }

                if(generationNumber == 100000){//if we reach 100000 generations we stop and we initiliaze the problem again.
                    break;
                }

            }
            if(found){
                break;
            }else {
                System.out.println("-------------INITIALIZING PROBLEM AGAIN-------------");
            }
        }
        System.out.println("\n");
        System.out.println("----------A TXT FILE WILL BE CREATED CONTAINING THE SCHEDULE----------" + "\n");
        if(!found){
            System.out.println("We could not find a perfect score..So the second best schedule is in the txt file:  " + "\n");
            if(population.getState()[0].getFitness() == 8){
                System.out.println("    CREATED FinalSchedule.txt CHECK IT FOR THE SCHEDULE" + "\n");
                printFinalSchedule(SecondBestSchedule);

            }else{
                System.out.println("    CREATED FinalSchedule.txt CHECK IT FOR THE SCHEDULE" + "\n");
                printFinalSchedule(population.getState()[0].getSchedule());

            }
        }else{
            String[][][] FinalSchedule = population.getState()[0].getSchedule();
            System.out.println("The final Schedule is in the txt file:  " + "\n");
            System.out.println("    CREATED FinalSchedule.txt CHECK IT FOR THE SCHEDULE"+ "\n");
            printFinalSchedule(FinalSchedule);

        }
    }

    private static void printFinalSchedule(String[][][] Schedule) {
        char classroom = 'A';
        int helper = 0;
        try {
            PrintStream out = new PrintStream(new FileOutputStream("FinalSchedule.txt"));
            System.setOut(out);
            for (int k = 0; k < 9; k++) {
                if (k % 3 == 0 && k != 0) {
                    classroom++;
                    helper = helper + 3;
                }

                System.out.print("Program " + classroom + ((k - helper) + 1) + "  |");
                System.out.println("               MONDAY              		" + "          TUESDAY              " + "             	 WEDNESDAY                 " + "          	    THURSDAY               " + "              	FRIDAY      ");
                for (int i = 0; i < 7; i++) {
                    if (i == 0) {
                        System.out.println("8:00-9:00   |" + " " + Schedule[i][0][k] + "    |    " + Schedule[i][1][k] + "    |    " + Schedule[i][2][k] + "    |    " + Schedule[i][3][k] + "    |    " + Schedule[i][4][k]);
                    } else if (i == 1) {
                        System.out.println("9:00-10:00  |" + " " + Schedule[i][0][k] + "    |    " + Schedule[i][1][k] + "    |    " + Schedule[i][2][k] + "    |    " + Schedule[i][3][k] + "    |    " + Schedule[i][4][k]);
                    } else if (i == 2) {
                        System.out.println("10:00-11:00 |" + " " + Schedule[i][0][k] + "    |    " + Schedule[i][1][k] + "    |    " + Schedule[i][2][k] + "    |    " + Schedule[i][3][k] + "    |    " + Schedule[i][4][k]);
                    } else if (i == 3) {
                        System.out.println("11:00-12:00 |" + " " + Schedule[i][0][k] + "    |    " + Schedule[i][1][k] + "    |    " + Schedule[i][2][k] + "    |    " + Schedule[i][3][k] + "    |    " + Schedule[i][4][k]);
                    } else if (i == 4) {
                        System.out.println("12:00-13:00 |" + " " + Schedule[i][0][k] + "    |    " + Schedule[i][1][k] + "    |    " + Schedule[i][2][k] + "    |    " + Schedule[i][3][k] + "    |    " + Schedule[i][4][k]);
                    } else if (i == 5) {
                        System.out.println("13:00-14:00 |" + " " + Schedule[i][0][k] + "    |    " + Schedule[i][1][k] + "    |    " + Schedule[i][2][k] + "    |    " + Schedule[i][3][k] + "    |    " + Schedule[i][4][k]);
                    } else {
                        System.out.println("14:00-15:00 |" + " " + Schedule[i][0][k] + "    |    " + Schedule[i][1][k] + "    |    " + Schedule[i][2][k] + "    |    " + Schedule[i][3][k] + "    |    " + Schedule[i][4][k]);
                        System.out.println();
                    }

                }

            }
            printTeachers(mainTeachers);
        } catch (Exception e) {
            System.out.println("Error printing the programme");

        }
    }

    private static void printTeachers(ArrayList<TeachersClass> Teachers){
        System.out.println();
        System.out.println("Class :  " + "                A                " + "                       B                " + "                      C             ");
        for (int i = 0; i < Teachers.size(); i += 3) {
        if (i < 56)
            System.out.println("        " + Teachers.get(i).getTeacher_code() + " --> " + Teachers.get(i).getTeacher_name() + "  |    " + Teachers.get(i + 57).getTeacher_code() + " --> " + Teachers.get(i + 57).getTeacher_name() + "  |    " + Teachers.get(i + 117).getTeacher_code() + " --> " + Teachers.get(i + 117).getTeacher_name() + "   | " );
        else if (i < 58)
            System.out.println("                                            |    " + Teachers.get(i + 57).getTeacher_code() + " --> " + Teachers.get(i + 57).getTeacher_name() + "  |    " + "                                     | ");

        }
    }

}
