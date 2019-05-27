//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196


import java.util.ArrayList;
import java.util.Arrays;

public class Population {
	
	
	private State[] states;
	
	public Population(int capacity) {//creates a new "population" array.
		states = new State[capacity];
	}

	//we create our population
	public Population initializePopulation(ArrayList<TeachersClass> Teachers,ArrayList<LessonsClass> Lessons) {
		for(int i=0; i<GeneticAlgorithmClass.POPULATION_SIZE; i++) {
			states[i]=new State(7,5,9).initializeState(Teachers,Lessons);
			System.out.println("Creating States---- Currently in state # : " + i + "           " + states[i].getFitness());
			
		}				
		sortStatesByFitness();		
		return this;
	}
	
	public State[] getState() {
		return states;
	}//return the array states
	
	public Population sortStatesByFitness() {//we sort our states based on fitness using a lambda expression.
		Arrays.sort(states,(state1,state2) -> {
			int value=0;
			if(state1.getFitness() > state2.getFitness()) {
				value=-1;
			}else if(state1.getFitness() < state2.getFitness()) {
				value=1;
			}
			return value;
		});
		return this;
	}
	
	
	
}
