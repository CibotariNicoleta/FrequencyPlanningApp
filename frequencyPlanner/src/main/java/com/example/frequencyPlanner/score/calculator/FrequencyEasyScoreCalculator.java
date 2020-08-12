package com.example.frequencyPlanner.score.calculator;

import java.util.ArrayList;
import java.util.List;

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore;
import org.optaplanner.core.impl.score.director.easy.EasyScoreCalculator;

import com.example.frequencyPlanner.domain.Frequency;
import com.example.frequencyPlanner.domain.FrequencyPlan;
import com.example.frequencyPlanner.domain.Transmitter;
import com.example.frequencyPlanner.persistence.FrequencyPlanGenerator;

public class FrequencyEasyScoreCalculator implements EasyScoreCalculator<FrequencyPlan>{

	 private String badResult; 
	 @Override
	 public HardSoftScore calculateScore(FrequencyPlan fr) {
		
		 int hardScore = 0;
		 int startPosition = 0;
		 List<Transmitter> hoppingTransmitterList = transmitterTypeVerification(fr);
		 int tactFinish = FrequencyPlanGenerator.frequencyDomain.length/2;
		 for(int tact = 0; tact < tactFinish; tact++ )
		 {   
			 getNewFrequency(hoppingTransmitterList, tactFinish, tact, startPosition); 
			 hardScore = noNeighbourWithTheSameFrequency(fr, hardScore);
			 hardScore = siteRuleRespected(fr, hardScore);
			 startPosition++;
				
		 }
		 
		 
		 return HardSoftScore.of(hardScore, 0 );
	 }




	private int siteRuleRespected(FrequencyPlan fr, int hardScore) {
		for(int i = 0 ; i<fr.getTransmitterList().size(); i++)
			 for(int j=i+1 ; j < fr.getTransmitterList().size(); j++) {
				 if(( fr.getTransmitterList().get(i).getFrequency()==null||fr.getTransmitterList().get(j).getFrequency()==null ))
					 continue;
				 else {
				 if((fr.getTransmitterList().get(i).getSite().getIdSite()==fr.getTransmitterList().get(j).getSite().getIdSite()) )
					 if(Math.abs(fr.getTransmitterList().get(i).getFrequency().getFrequencyValue() - fr.getTransmitterList().get(j).getFrequency().getFrequencyValue())<2) {
						 hardScore-=1;
						 badResult+="\n siteRule not respected: Transmitter: "+fr.getTransmitterList().get(i).getId()+ " with frequency: "+fr.getTransmitterList().get(i).getFrequency().getFrequencyValue()+"and Site: "+fr.getTransmitterList().get(i).getSite().getIdSite()+
								 " ==>> Transmitter: "+fr.getTransmitterList().get(j).getId()+ " with frequency: "+fr.getTransmitterList().get(j).getFrequency().getFrequencyValue()+"and Site: "+fr.getTransmitterList().get(j).getSite().getIdSite();
							
					 }
						 
				 }
			 }
		return hardScore;
	}




	private int noNeighbourWithTheSameFrequency(FrequencyPlan fr, int hardScore) {
		for(Transmitter iterator : fr.getTransmitterList()) {
			 for(Transmitter neighbourIterator : iterator.getNeighbours())  
				 if(iterator.getFrequency() == null || neighbourIterator.getFrequency() ==null)
					 continue;
				 else if(iterator.getFrequency().getFrequencyValue() == neighbourIterator.getFrequency().getFrequencyValue() ) {
					 hardScore -= 1;
					 badResult+="\n neighbourRule not respected: Transmitter: "+iterator.getId()+ " with frequency: "+iterator.getFrequency().getFrequencyValue()+"and Site: "+iterator.getSite().getIdSite()+
							 " ==>> Transmitter: "+neighbourIterator.getId()+ " with frequency: "+neighbourIterator.getFrequency().getFrequencyValue()+"and Site: "+neighbourIterator.getSite().getIdSite();
				 }
					
		 }
		
		return hardScore;
	}




	private void getNewFrequency(List<Transmitter> hoppingTransmitterList, int tactFinish, int tact, int startPosition) {
		
		for(Transmitter transmitterIterator : hoppingTransmitterList) {
			if(transmitterIterator.getMaio() != null) {
			 if(transmitterIterator.getMaio().getFrequencyGroupNumber() == 1) {
				 if(transmitterIterator.getMaio().getPosition() + tact > tactFinish - 1) {
				    transmitterIterator.setFrequency(new Frequency(FrequencyPlanGenerator.frequencyGroup1[startPosition]));
				 }
				 else
					transmitterIterator.setFrequency(new Frequency(FrequencyPlanGenerator.frequencyGroup1[transmitterIterator.getMaio().getPosition()]));
					 
					 
			 }
			 else {
				 if(transmitterIterator.getMaio().getPosition()+tact > tactFinish-1)
					 transmitterIterator.setFrequency(new Frequency(FrequencyPlanGenerator.frequencyGroup2[startPosition]));
				 else
					 transmitterIterator.setFrequency(new Frequency(FrequencyPlanGenerator.frequencyGroup2[transmitterIterator.getMaio().getPosition()]));
			 }
			 
			}
				 
		 }
	}

	 
	 
	 
	private List<Transmitter> transmitterTypeVerification(FrequencyPlan fr) {
		List<Transmitter> hoppingTransmitterList = new ArrayList<Transmitter>();
		for(Transmitter transmitter:fr.getTransmitterList())
			 if(transmitter.getType().equals("Non-Hopping"))
			 {
				 transmitter.setMaio(null);
			 }
			 else {
				 transmitter.setFrequency(null);
				 hoppingTransmitterList.add(transmitter);
			 }
		return hoppingTransmitterList;		
	}




	public String getBadResult() {
		return badResult;
	}

	
}




/* @Override
public HardSoftScore calculateScore(FrequencyPlan fr) {
	 int hardScore = 0;
	 for(Transmitter iterator : fr.getTransmitterList()) {
		 for(Transmitter neighbourIterator : iterator.getNeighbours())  
			 if(iterator.getFrequency()==null || neighbourIterator.getFrequency() ==null)
				 continue;
			 else if(iterator.getFrequency().getFrequencyValue() == neighbourIterator.getFrequency().getFrequencyValue() )
				 hardScore-=1;
	 }
	 
	 
	 for(int i = 0 ; i<fr.getTransmitterList().size(); i++)
		 for(int j=i+1 ; j < fr.getTransmitterList().size(); j++) {
			 if(( fr.getTransmitterList().get(i).getFrequency()==null||fr.getTransmitterList().get(j).getFrequency()==null ))
				 continue;
			 else {
			 if((fr.getTransmitterList().get(i).getSite().getIdSite()==fr.getTransmitterList().get(j).getSite().getIdSite()) )
				 if(Math.abs(fr.getTransmitterList().get(i).getFrequency().getFrequencyValue() - fr.getTransmitterList().get(j).getFrequency().getFrequencyValue())<2)
					 hardScore-=1;
			 }
		 }
	 
//	 for(Transmitter iterator : fr.getTransmitterList()) {
//		 if(iterator.getFrequency()==null)
//			 hardScore-=1;
//	 }
		 
			 		
	 
					 return HardSoftScore.of(hardScore, 0 );
}*/
 
 

