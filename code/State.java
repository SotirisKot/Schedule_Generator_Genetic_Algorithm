//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196

import java.util.*;

public class State
{
	private int RowLength;
	private int ColumnLength;
	private int thirdDim;
	private String [][][] Schedule;
	private int fitness;//each state(schedule) has a fitness.
	private ArrayList<LessonsClass> AvailableLessons = new ArrayList<>();
	private ArrayList<Integer> AvailableHours = new ArrayList<>();
	private Vector<String> ExceptLessons = new Vector<>();

	//constructor
	public State( int RowLength, int ColumnLength, int thirdDim)
	{
		this.RowLength = RowLength;
		this.ColumnLength = ColumnLength;
		this.thirdDim = thirdDim;
		Schedule = new String[RowLength][ColumnLength][thirdDim];
	}

	//copies a schedule to a new 3d-array and calculates its fitness.
	private State(String [][][] Schedule)
	{
		this.RowLength=7;
		this.ColumnLength=5;
		this.thirdDim=9;

		this.Schedule = new String[this.RowLength][this.ColumnLength][this.thirdDim];
		for(int i=0; i<this.RowLength; i++)
		{
			for(int j=0; j<this.ColumnLength; j++)
			{
				for(int k=0; k < this.thirdDim; k++) {
					this.Schedule[i][j][k] = Schedule[i][j][k];

				}
				
			}
		}
		this.CalculateFitness();
	}
	
	
    //initializes a random State(schedule)
	public State initializeState(ArrayList<TeachersClass> Teachers,ArrayList<LessonsClass> Lessons) {
		Random random = new Random();
		int RandomIndex;
		
		for(int i=0; i<Lessons.size(); i++){
			if(Lessons.get(i).getHours()>2 && !(ExceptLessons.contains(Lessons.get(i).getCourseName()+", "+Teachers.get(i).getTeacher_code()))){
				ExceptLessons.addElement(Lessons.get(i).getCourseName() +", " + Teachers.get(i).getTeacher_code());
			}
		}

		for(int i = 0; i < Lessons.size(); i++){
			AvailableLessons.add(Lessons.get(i));
			AvailableHours.add(Lessons.get(i).getHours());
		}		
		String lesson;
		int TotalHours;
		String dep;

		//it runs until all hours are full and all lessons are teached.
        //if a lesson is selected we reduce its hours.
        //if its hours are not 0 we add it to the schedule.
		for(int i=0; i < RowLength; i++) {
			for(int j=0; j < ColumnLength; j++) {
				RandomIndex = random.nextInt(AvailableLessons.size());//random lesson.
				dep = AvailableLessons.get(RandomIndex).getClassroom();//random department.

                //we check the department and if the particular hour is vacant.
				if(dep.equals("A1") && Schedule[i][j][0] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();//we get the name
					TotalHours=AvailableHours.get(RandomIndex);//we get the hours.
					if(TotalHours>0) {
						Schedule[i][j][0] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][0] is A1.
						AvailableHours.set(RandomIndex, --TotalHours);//reducing hours.
					
					}
				}else if(dep.equals("A2") && Schedule[i][j][1] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][1] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][1] is A2.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}else if(dep.equals("A3") && Schedule[i][j][2] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][2] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][2] is A3.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}else if(dep.equals("B1") && Schedule[i][j][3] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][3] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][3] is B1.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}else if(dep.equals("B2") && Schedule[i][j][4] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][4] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][4] is B2.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}else if(dep.equals("B3") && Schedule[i][j][5] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][5] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][5] is B3.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}else if(dep.equals("C1") && Schedule[i][j][6] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][6] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][6] is C1.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}else if(dep.equals("C2") && Schedule[i][j][7] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][7] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][7] is C2.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}else if(dep.equals("C3") && Schedule[i][j][8] == null) {
					lesson = AvailableLessons.get(RandomIndex).getCourseName();
					TotalHours=AvailableHours.get(RandomIndex);
					if(TotalHours>0) {
						Schedule[i][j][8] = lesson +", " + Teachers.get(RandomIndex).getTeacher_code();//Schedule[i][j][8] is C3.
						AvailableHours.set(RandomIndex, --TotalHours);
						
					}
				}
				//if an hour is null we reduce the j so it can be checked again and filled.
				if(Schedule[i][j][0] == null || Schedule[i][j][1] == null || Schedule[i][j][2] == null ||Schedule[i][j][3] == null
						|| Schedule[i][j][4] == null ||Schedule[i][j][5] == null || Schedule[i][j][6] == null || Schedule[i][j][7] == null
						|| Schedule[i][j][8] == null) {
					
                    j--;
				}
			}
		}
		AvailableLessons.clear();
		AvailableHours.clear();
		this.CalculateFitness();//we calculate the fitness of the random initialized schedule.
		return this;
	}
	

		
	//RESTRICTIONS
	public void CalculateFitness() {
		boolean NoLesson=true;//checks if there are null positions(meaning vacant hours).
		boolean ThreeHourConsecutive = false;//checks is a lesson is teached 3 consecutive hours
		int NumOfConflicts = 1;//our random schedules have 7-hours every day so the third restriction is always satisfied.
	
		//first restriction.
		for(int i=0; i < RowLength; i++) {
			for(int j=0; j < ColumnLength; j++) {
				for(int k=0; k < thirdDim; k++) {
					if(Schedule[i][j][k] == null) {
						NoLesson=false;
					}
				}
				
				
			}
		}
		if(NoLesson){
			NumOfConflicts++;//no vacant hours.
		}
		
		//second restriction.
		for(int j = 0; j < ColumnLength; j++) {
			for(int k=0; k < thirdDim; k++) {
				for(int i = 0; i < RowLength; i++) {
					if(i != RowLength - 2 && i != RowLength-1) {
						if(Schedule[i][j][k].equals(Schedule[i+1][j][k]) && Schedule[i][j][k].equals(Schedule[i+2][j][k])){
							ThreeHourConsecutive = true;

						}
					}
				}				
			}
		}
		if(!ThreeHourConsecutive) {
			NumOfConflicts+=2;//no 3 consecutive hours..plus 2 points(important restriction).

		}
		
		//extra restriction...checks if a teacher has a lesson in two different departments at the same time.
        boolean sameT=true;
		String temp;
		for(int j=0; j< ColumnLength; j++){
            for(int i=0; i<RowLength; i++){
                for(int k=0; k<thirdDim; k++){
                    for(int x=k+1; x < thirdDim; x++){
                        if(Schedule[i][j][k].equals(Schedule[i][j][x])){
                            if(i == 0){
                                temp = Schedule[i][j][k];
                                Schedule[i][j][k] = Schedule[i+1][j][k];
                                Schedule[i+1][j][k] = temp;
                            }else if(i == 6){
                                temp = Schedule[i][j][k];
                                Schedule[i][j][k] = Schedule[i-1][j][k];
                                Schedule[i-1][j][k] = temp;
                            }else{
                                temp = Schedule[i][j][k];
                                Schedule[i][j][k] = Schedule[i-1][j][k];
                                Schedule[i-1][j][k] = temp;
                            }
                            sameT=false;
                        }
                    }
                }
            }
        }
		if(sameT) {
			NumOfConflicts+=3;//no teaching hour in two different departments for the same teacher in the same hour...plus 3 points(very important restriction).
		}
		
		//fourth restriction..fair distribution in the hours of each lesson.
		int counter=0;
		for(int j=0; j < ColumnLength; j++) {
			for(int k=0; k<thirdDim; k++) {
				for(int i=0; i <RowLength; i++) {
					for(int l=i+1; l<RowLength; l++){
						if(Schedule[i][j][k].equals(Schedule[l][j][k]) && !Excepted(ExceptLessons,Schedule,i,j,k)){
							counter++;
						}
					}
				}				
			}
		}
		if(counter <= 6){//we allow max 6 lessons to have all their hours in the same day(Mathimatika kai Arxaia Ellhnikh Glwssa den elegxontai).
			NumOfConflicts++;//plus 1..(weak restriction).
		}

		//last we add +1 because the hours of each teacher are equally distributed..(we created the input files that way).
		NumOfConflicts++;

        this.fitness = NumOfConflicts;//last we update the fitness.

	}
	
	
	public boolean Excepted(Vector<String> ExceptLessons,String[][][] schedule,int i,int j,int k) {
		boolean check=false;
			for(int l=0; l<ExceptLessons.size(); l++){
				if(ExceptLessons.elementAt(l).equals(schedule[i][j][k])){
					check=true;
				}
			}
		return check;
	}
	
	//GETTERS!!

	public String[][][] getSchedule(){
		return this.Schedule;
	}
	
	public int getFitness() {//returns the fitness of each state.
		return this.fitness;
	}

	public int getRowLength() {
		return this.RowLength;
	}//returns the row length
	
	public int getColumnLength() {
		return this.ColumnLength;
	}//returns the column length
	
	public int getThirdDim() {
		return this.thirdDim;
	}//returns the third dimension's length

    //fitness setter.
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

}
