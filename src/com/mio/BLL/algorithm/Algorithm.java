/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mio.BLL.algorithm;

import com.mio.GUI.dialog.MakeScheduleDialog;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mio
 */
public final class Algorithm {

    public float getAverageFitness() {
        return averageFitness;
    }

    public int getIsekaiCount() {
        return isekaiCount;
    }

    public int getGenerationCount() {
        return generationCount;
    }
    
    private final ArrayList<GASchedule> population;
    
    private final Random rand;
    
    private int mutationProb;
    
    private int crossoverProb;
    
    private boolean stop;
    
    private boolean isekai;
    
    private float isekaiFitness;
    
    private int generationCount;
    
    private int isekaiCount;
    
    public boolean globalCompetition(GASchedule child) {
        int i = fitnessRatio.length - 1;
        if(child.getFitness() > population.get(i).getFitness())
            population.set(i, child);
        else
            return false;
        int j = i - 1;
        while(j != -1 && child.getFitness() > population.get(j).getFitness()) {
            population.set(j + 1, population.get(j));
            --j;
        }
        population.set(j + 1, child);
        return true;
    }
    
    private final float[] fitnessRatio;
    
    private float averageFitness;
    
    public void calcFitness() {
        float sum = 0;
        for(int i = 0; i != fitnessRatio.length; ++i) {
            sum += population.get(i).getFitness();
        }
        averageFitness = sum / (float)fitnessRatio.length;
        for(int i = 0; i != fitnessRatio.length; ++i) {
            fitnessRatio[i] = population.get(i).getFitness() / sum;
        }
    }
    
    public GASchedule select() {
        float sumRatio = 0;
        float prob = rand.nextFloat();
        int i = 0;
        for(; i != fitnessRatio.length; ++i) {
            sumRatio += fitnessRatio[i];
            if(1 - sumRatio <= prob)
                break;
        }
        return population.get(i == fitnessRatio.length ? i - 1 : i);
    }
    
    public void eliteIsekai() {
        ++isekaiCount;
        for(int i = 1; i != 100; ++i) {
            GASchedule newS = new GASchedule(false);
            newS.calcFitness();
            population.set(i, newS);
            int j = i - 1;
            while(j != -1 && newS.getFitness() > population.get(j).getFitness()) {
                population.set(j + 1, population.get(j));
                --j;
            }
            population.set(j + 1, newS);
        }
        calcFitness();
    }

    private Algorithm() {
        this.isekaiCount = 0;
        this.generationCount = 0;
        this.isekaiFitness = (float) 0.99;
        this.isekai = false;
        this.crossoverProb = 80;
        this.mutationProb = 20;
        this.rand = new Random();
        stop = false;
        population = new ArrayList<>();
        fitnessRatio = new float[100];
        
        for(int i = 0; i != 100; ++i) {
            GASchedule newS;
            population.add(newS = new GASchedule(false));
            newS.calcFitness();
            int j = i - 1;
            while(j != -1 && newS.getFitness() > population.get(j).getFitness()) {
                population.set(j + 1, population.get(j));
                --j;
            }
            population.set(j + 1, newS);
        }
        calcFitness();
    }
    
    public boolean isConverge() {
        return averageFitness / population.get(0).getFitness() >= isekaiFitness;
    }
    
    public void nextGeneration() {
        ++generationCount;
        for(int i = 0; i != 20; ++i) {
            GASchedule child;
            if(rand.nextInt(100) < crossoverProb) {
                child = select().crossover(select());
            } else {
                child = select().makeCopy();
            }
            if(rand.nextInt(100) < mutationProb) {
                child.mutation();
            }
            if(globalCompetition(child)) {
                calcFitness();
            }
        }
    }
    
    private static Algorithm instance;
    
    public void setStop(boolean stop) {
        this.stop = stop;
    }
    
    public GASchedule getFittest() {
        return population.get(0);
    }
    
    public static void clear() {
        instance = null;
    }
    
    public static Algorithm getInstance() {
        if(instance == null) {
            instance = new Algorithm();
        }
        return instance;
    }
    
    public int getMutationProb() {
        return mutationProb;
    }

    public void setMutationProb(int mutationProb) {
        this.mutationProb = mutationProb;
    }

    public int getCrossoverProb() {
        return crossoverProb;
    }

    public void setCrossoverProb(int crossoverProb) {
        this.crossoverProb = crossoverProb;
    }

    public boolean isIsekai() {
        return isekai;
    }

    public void setIsekai(boolean isekai) {
        this.isekai = isekai;
    }

    public float getIsekaiFitness() {
        return isekaiFitness;
    }

    public void setIsekaiFitness(float isekaiFitness) {
        this.isekaiFitness = isekaiFitness;
    }
    
    public boolean isNoViolate() {
        return population.get(0).getFitness() >= 1;
    }
}
