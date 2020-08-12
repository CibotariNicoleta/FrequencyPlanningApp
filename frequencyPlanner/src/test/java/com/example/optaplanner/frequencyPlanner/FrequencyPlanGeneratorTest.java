package com.example.optaplanner.frequencyPlanner;


import org.junit.Test;

import com.example.frequencyPlanner.domain.FrequencyPlan;
import com.example.frequencyPlanner.domain.MAIO;
import com.example.frequencyPlanner.domain.Transmitter;
import com.example.frequencyPlanner.persistence.FrequencyPlanGenerator;

public class FrequencyPlanGeneratorTest {

	
	@Test
    public void createOneTransmitterPerSite() {
        FrequencyPlanGenerator frPlanGenerator = new FrequencyPlanGenerator();
        for(Transmitter transmitter : frPlanGenerator.createFrequencyPlan(10,3,1,3).getTransmitterList())
        {
            System.out.println(" Transmitter -> "+transmitter.getId()+", Site -> "+transmitter.getSite().getIdSite());
        }

    }


    @Test 
    public void createTwoTransmitterPerSite() {
        System.out.println("\n");
        FrequencyPlanGenerator frPlanGenerator = new FrequencyPlanGenerator();
        for(Transmitter transmitter : frPlanGenerator.createFrequencyPlan(10,3,2,3).getTransmitterList())
        {
            System.out.println(" Transmitter -> "+transmitter.getId()+", Site -> "+transmitter.getSite().getIdSite());
        }

    }


    @Test
    public void createFiveTransmitterPerSite() {
        System.out.println("\n");
        FrequencyPlanGenerator frPlanGenerator = new FrequencyPlanGenerator();
        for(Transmitter transmitter : frPlanGenerator.createFrequencyPlan(10,3,5,3).getTransmitterList())
        {
            System.out.println(" Transmitter -> "+transmitter.getId()+", Site -> "+transmitter.getSite().getIdSite());
        }


    }


    @Test
    public void transmitterWithOneNeighbour() {
        FrequencyPlanGenerator frPlanGenerator = new FrequencyPlanGenerator();
        FrequencyPlan frPlan = new FrequencyPlan();
        System.out.println("\n");
        frPlanGenerator.createMaioList(frPlan);

        for(MAIO maio: frPlan.getMaioList())
        {
            System.out.println(" MAIO position: "+maio.getPosition()+",  MAIO groupNumber: "+maio.getFrequencyGroupNumber());
        }
    }
}
