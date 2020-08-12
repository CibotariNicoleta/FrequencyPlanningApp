package com.example.frequencyPlanner.controller;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;
import org.optaplanner.core.api.solver.SolverJob;
import org.optaplanner.core.api.solver.SolverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.frequencyPlanner.domain.Frequency;
import com.example.frequencyPlanner.domain.FrequencyPlan;
import com.example.frequencyPlanner.domain.MAIO;
import com.example.frequencyPlanner.domain.Transmitter;
import com.example.frequencyPlanner.persistence.FrequencyPlanGenerator;


@RestController
public class FrequencyPlannerController {
   
	@RequestMapping(value = "/frequencyPlanning", method=RequestMethod.GET)
	public ModelAndView frequencyPage() {
		return new ModelAndView("frequencyPlanning");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.POST )
	public ModelAndView home(@RequestParam("transmitters") int transmitters, @RequestParam("frequency") int frequency, @RequestParam("site") int site, @RequestParam("neighbours") int neighbours ) {
		System.out.println("hjkl");
		SolverFactory<FrequencyPlan> solverFactory = SolverFactory.createFromXmlResource(
                "com/example/optaplanner/frequencyPlanner/solve/FrequencyPlannerSolve.xml");
		
        Solver<FrequencyPlan> solver = solverFactory.buildSolver();
     
        // Load a problem
        FrequencyPlan unsolvedFrequencyPlan = new FrequencyPlanGenerator().createFrequencyPlan(transmitters, frequency, site, neighbours );
       
        // Solve the problem
        FrequencyPlan solvedFrequencyPlan = solver.solve(unsolvedFrequencyPlan);
        
        for(Transmitter transmitter : solvedFrequencyPlan.getTransmitterList()) {
        	if(transmitter.getFrequency() == null)
        		transmitter.setFrequency(new Frequency(-1));
        	
        	if(transmitter.getType() == "Non-Hopping")
        	{
        		transmitter.setMaio(new MAIO(-1,-1));
        	}
        }
       
        
        ModelAndView model = new ModelAndView("home");
        model.addObject("frequencyPlan", solvedFrequencyPlan.getTransmitterList());
        model.addObject("frequencyGroup1", FrequencyPlanGenerator.frequencyGroup1);
        model.addObject("frequencyGroup2", FrequencyPlanGenerator.frequencyGroup2);
        model.addObject("frequencyGroup3", FrequencyPlanGenerator.frequencyGroup3);
        model.addObject("score", solvedFrequencyPlan.getScore());
		return model;
	}
}
