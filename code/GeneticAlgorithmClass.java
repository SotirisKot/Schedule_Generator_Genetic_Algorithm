//KOTITSAS SOTIRIS : p3150077
//ANASTASIOS LEPIPAS: p3150091
//GEORGIOS CHATZOPOULOS: p3150196

import java.util.ArrayList;
import java.util.Random;

public class GeneticAlgorithmClass {
	public static final int POPULATION_SIZE = 10;
	public static final int SCORE_TO_ACHIEVE = 9;
	private static final double MUTATION_RATE = 0.3;
	
	//returns the new evolved population
	public Population evolvePopulation(Population population) {
		return NewPopulation(population);
	}

    //creates the new population
    private Population NewPopulation(Population population) {
        Population NewPopulation = new Population(POPULATION_SIZE);
        Random r = new Random();

        //FitnessBounds represents the possibility of each chromosome to be picked.
        //Each chromosome has a number of positions in the arraylist based on its fitness.
        ArrayList<Integer> FitnessBounds = new ArrayList<>();
        for(int i=0; i < POPULATION_SIZE; i++) {
            for(int j=0; j < population.getState()[i].getFitness(); j++) {
                FitnessBounds.add(i);
            }
        }
        String temp;
        int index = 0;

        //we create the new population.Each time we reproduce two new chromosomes from two old ones.
        //we choose the parents based on the FitnessBounds arraylist.
        //then we mutate the new reproduced chromosomes and we add them to the new population.
        for(int i=0; i < POPULATION_SIZE/2; i++) {
            int xIndex = FitnessBounds.get(r.nextInt(FitnessBounds.size()));
            State chromosome1 = population.getState()[xIndex];
            int yIndex = FitnessBounds.get(r.nextInt(FitnessBounds.size()));
            while(yIndex == xIndex)//the indexes of the two selected chromosomes must not be the same.
            {
                yIndex = FitnessBounds.get(r.nextInt(FitnessBounds.size()));
            }
            State chromosome2 = population.getState()[yIndex];
            int intersectionPoint = r.nextInt(8) + 1;//we select the intersection point. we split the chromosomes on the third dimension
                                                            //which is the departments.
            //based on the intersection point the chromosome1 gets the k to 9 departments of the chromosome2 and the opposite
            for(int x=0; x < chromosome1.getRowLength(); x++){
                for(int j=0; j < chromosome1.getColumnLength(); j++){
                    for(int k=intersectionPoint; k < chromosome1.getThirdDim(); k++){
                        temp = chromosome1.getSchedule()[x][j][k];
                        chromosome1.getSchedule()[x][j][k] = chromosome2.getSchedule()[x][j][k];
                        chromosome2.getSchedule()[x][j][k] = temp;
                    }
                }
            }
            //mutating the two new chromosomes and adding them to the new population.
            chromosome1 = mutateChromosome(chromosome1);
            chromosome2 = mutateChromosome(chromosome2);
            NewPopulation.getState()[index] = chromosome1;
            NewPopulation.getState()[++index] = chromosome2;
            index++;
        }
        FitnessBounds.clear();//we empty the arraylist so it can be updated based on the next population.
        return NewPopulation;
    }

    //mutates a chromosome with 30/100 possibility
	private State mutateChromosome(State chromosome) {
		State mutateChromosome = chromosome;
		Random random = new Random();
		String temp;
		int day,hour;
		//it tries 5 times to mutate(in that way we have more changes in the schedule).
		for(int x = 0; x < 5; x++){
            int i =random.nextInt(6);
            int j = random.nextInt(4);
            int k = random.nextInt(8);
            if(Math.random() < MUTATION_RATE){//it changes a random lesson in a random hour of the same department.
                temp = mutateChromosome.getSchedule()[i][j][k];
                day = random.nextInt(4);
                hour = random.nextInt(6);
                mutateChromosome.getSchedule()[i][j][k]=chromosome.getSchedule()[hour][day][k];
                mutateChromosome.getSchedule()[hour][day][k] = temp;
                mutateChromosome.CalculateFitness();
            }
		}
       return mutateChromosome;
	}
}
