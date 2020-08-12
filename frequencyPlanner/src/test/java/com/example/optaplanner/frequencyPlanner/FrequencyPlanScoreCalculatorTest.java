package com.example.optaplanner.frequencyPlanner;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.example.frequencyPlanner.domain.FrequencyPlan;
import com.example.frequencyPlanner.domain.MAIO;
import com.example.frequencyPlanner.domain.Site;
import com.example.frequencyPlanner.domain.Transmitter;
import com.example.frequencyPlanner.score.calculator.FrequencyEasyScoreCalculator;

public class FrequencyPlanScoreCalculatorTest {
  FrequencyPlan frequencyPlanTest = new FrequencyPlan();
  FrequencyEasyScoreCalculator score = new FrequencyEasyScoreCalculator();
  
  @Test
  public void HoppingWithTheSameLength() {
	  List<Transmitter> transmitters = new ArrayList<Transmitter>();
	  Transmitter tran1 = new Transmitter(1, "Hopping");
	  Transmitter tran2 = new Transmitter(2, "Hopping");
	 
	  MAIO maio1 = new MAIO(1, 1);
	  MAIO maio2 = new MAIO(2,2);
	  
	  Site site1 = new Site(1);
	  Site site2 = new Site(2);
	  
	  tran1.setMaio(maio1);
	  tran2.setMaio(maio2);
	  
	  tran1.setSite(site1);
	  tran2.setSite(site2);
	  
	  transmitters.add(tran1);
	  transmitters.add(tran2);
	  
	  frequencyPlanTest.setTransmitterList(transmitters);
	  score.calculateScore(frequencyPlanTest);
	  
  }
  
  
  @Test
  public void HoppingWithTheDifferentLength() {
	  List<Transmitter> transmitters = new ArrayList<Transmitter>();
	  Transmitter tran1 = new Transmitter(1, "Hopping");
	  Transmitter tran2 = new Transmitter(2, "Hopping");
	 
	  MAIO maio1 = new MAIO(1, 1);
	  MAIO maio2 = new MAIO(2,3);
	  
	  Site site1 = new Site(1);
	  Site site2 = new Site(2);
	  
	  tran1.setMaio(maio1);
	  tran2.setMaio(maio2);
	  
	  tran1.setSite(site1);
	  tran2.setSite(site2);
	  
	  transmitters.add(tran1);
	  transmitters.add(tran2);
	  
	  frequencyPlanTest.setTransmitterList(transmitters);
	  score.calculateScore(frequencyPlanTest);
	  
  }
  
  
  @Test
  public void HoppingWithNonHopping() {
	  List<Transmitter> transmitters = new ArrayList<Transmitter>();
	  Transmitter tran1 = new Transmitter(1, "Hopping");
	  Transmitter tran2 = new Transmitter(2, "Non-Hopping");
	 
	  MAIO maio1 = new MAIO(1, 1);
	  MAIO maio2 = null;
	  
	  Site site1 = new Site(1);
	  Site site2 = new Site(2);
	  
	  tran1.setMaio(maio1);
	  tran2.setMaio(maio2);
	  
	  tran1.setSite(site1);
	  tran2.setSite(site2);
	  
	  transmitters.add(tran1);
	  transmitters.add(tran2);
	  
	  frequencyPlanTest.setTransmitterList(transmitters);
	  score.calculateScore(frequencyPlanTest);
	  
  }
  
  
}
