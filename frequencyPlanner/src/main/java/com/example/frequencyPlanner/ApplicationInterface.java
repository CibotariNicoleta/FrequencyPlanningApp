package com.example.frequencyPlanner;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.Dimension;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import org.optaplanner.core.api.solver.Solver;
import org.optaplanner.core.api.solver.SolverFactory;

import com.example.frequencyPlanner.domain.FrequencyPlan;
import com.example.frequencyPlanner.domain.Transmitter;
import com.example.frequencyPlanner.persistence.FrequencyPlanGenerator;
import com.example.frequencyPlanner.score.calculator.FrequencyEasyScoreCalculator;

import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.ComponentOrientation;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Cursor;

public class ApplicationInterface extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nrTransmitters;
	private JTextField nrFrequencies;
	private JTextField nrTransmitters_Site;
	private JTextField nrNeighbours_Transmitter;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicationInterface frame = new ApplicationInterface();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ApplicationInterface() {
		setTitle("Frequency Planner Application");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 908, 561);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JLabel lblScore = new JLabel();
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(10, 50));
		panel.setBackground(SystemColor.textHighlight);
		panel.setMinimumSize(new Dimension(10, 50));
		panel.setMaximumSize(new Dimension(32767, 50));
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Frequency Planner");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_8 = new JPanel();
		panel_8.setPreferredSize(new Dimension(400, 100));
		panel_8.setMinimumSize(new Dimension(400, 100));
		panel_8.setMaximumSize(new Dimension(400, 200));
		panel_2.add(panel_8);
		panel_8.setLayout(new BoxLayout(panel_8, BoxLayout.X_AXIS));
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setMaximumSize(new Dimension(70, 32767));
		panel_8.add(horizontalStrut);
		
		JLabel lblNewLabel_4 = new JLabel("Fill the following textFields");
		lblNewLabel_4.setMinimumSize(new Dimension(150, 13));
		lblNewLabel_4.setPreferredSize(new Dimension(150, 13));
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panel_8.add(lblNewLabel_4);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setMaximumSize(new Dimension(70, 32767));
		panel_8.add(horizontalStrut_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new LineBorder(new Color(153, 180, 209), 2, true));
		panel_5.setPreferredSize(new Dimension(10, 50));
		panel_5.setMinimumSize(new Dimension(10, 50));
		panel_5.setMaximumSize(new Dimension(400, 50));
		panel_2.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.X_AXIS));
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setMaximumSize(new Dimension(75, 32767));
		panel_5.add(horizontalStrut_2);
		
		JLabel lblNewLabel_1 = new JLabel("Number of transmitters");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_5.add(lblNewLabel_1);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setMaximumSize(new Dimension(10, 32767));
		panel_5.add(horizontalStrut_4);
		
		nrTransmitters = new JTextField();
		nrTransmitters.setHorizontalAlignment(SwingConstants.CENTER);
		nrTransmitters.setMaximumSize(new Dimension(75, 25));
		panel_5.add(nrTransmitters);
		nrTransmitters.setColumns(10);
		
		Component horizontalStrut_17 = Box.createHorizontalStrut(20);
		panel_5.add(horizontalStrut_17);
		
		JLabel lblNewLabel_6 = new JLabel("(1 - 10000)");
		lblNewLabel_6.setForeground(Color.RED);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_5.add(lblNewLabel_6);
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setMaximumSize(new Dimension(75, 32767));
		panel_5.add(horizontalStrut_3);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 5));
		verticalStrut.setMinimumSize(new Dimension(0, 5));
		verticalStrut.setMaximumSize(new Dimension(32767, 5));
		panel_2.add(verticalStrut);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(153, 180, 209), 2, true));
		panel_4.setPreferredSize(new Dimension(400, 50));
		panel_4.setMinimumSize(new Dimension(10, 50));
		panel_4.setMaximumSize(new Dimension(400, 60));
		panel_2.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.X_AXIS));
		
		Component horizontalStrut_5 = Box.createHorizontalStrut(20);
		horizontalStrut_5.setMaximumSize(new Dimension(80, 32767));
		panel_4.add(horizontalStrut_5);
		
		JLabel lblNewLabel_2 = new JLabel("Number of frequencies");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_4.add(lblNewLabel_2);
		
		Component horizontalStrut_9 = Box.createHorizontalStrut(20);
		horizontalStrut_9.setMaximumSize(new Dimension(10, 32767));
		panel_4.add(horizontalStrut_9);
		
		nrFrequencies = new JTextField();
		nrFrequencies.setHorizontalAlignment(SwingConstants.CENTER);
		nrFrequencies.setMaximumSize(new Dimension(75, 25));
		panel_4.add(nrFrequencies);
		nrFrequencies.setColumns(10);
		
		Component horizontalStrut_16 = Box.createHorizontalStrut(20);
		panel_4.add(horizontalStrut_16);
		
		JLabel lblNewLabel_7 = new JLabel("(1 - 160)");
		lblNewLabel_7.setForeground(Color.RED);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_7);
		
		Component horizontalStrut_6 = Box.createHorizontalStrut(20);
		horizontalStrut_6.setMaximumSize(new Dimension(80, 32767));
		panel_4.add(horizontalStrut_6);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setMaximumSize(new Dimension(32767, 5));
		verticalStrut_1.setMinimumSize(new Dimension(0, 5));
		verticalStrut_1.setPreferredSize(new Dimension(0, 5));
		panel_2.add(verticalStrut_1);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(153, 180, 209), 2, true));
		panel_6.setPreferredSize(new Dimension(400, 50));
		panel_6.setMaximumSize(new Dimension(400, 50));
		panel_2.add(panel_6);
		panel_6.setLayout(new BoxLayout(panel_6, BoxLayout.X_AXIS));
		
		Component horizontalStrut_7 = Box.createHorizontalStrut(20);
		horizontalStrut_7.setMaximumSize(new Dimension(70, 32767));
		panel_6.add(horizontalStrut_7);
		
		JLabel lblNewLabel_3 = new JLabel("Number of transmitters/site");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_6.add(lblNewLabel_3);
		
		Component horizontalStrut_10 = Box.createHorizontalStrut(20);
		horizontalStrut_10.setMaximumSize(new Dimension(4, 32767));
		panel_6.add(horizontalStrut_10);
		
		nrTransmitters_Site = new JTextField();
		nrTransmitters_Site.setHorizontalAlignment(SwingConstants.CENTER);
		nrTransmitters_Site.setMaximumSize(new Dimension(40, 25));
		panel_6.add(nrTransmitters_Site);
		nrTransmitters_Site.setColumns(10);
		
		Component horizontalStrut_18 = Box.createHorizontalStrut(20);
		panel_6.add(horizontalStrut_18);
		
		JLabel lblNewLabel_8 = new JLabel("(1 - 5)");
		lblNewLabel_8.setForeground(Color.RED);
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_6.add(lblNewLabel_8);
		
		Component horizontalStrut_8 = Box.createHorizontalStrut(20);
		horizontalStrut_8.setMaximumSize(new Dimension(70, 32767));
		panel_6.add(horizontalStrut_8);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setMinimumSize(new Dimension(0, 5));
		verticalStrut_2.setMaximumSize(new Dimension(32767, 5));
		verticalStrut_2.setPreferredSize(new Dimension(0, 5));
		panel_2.add(verticalStrut_2);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new LineBorder(new Color(153, 180, 209), 2, true));
		panel_11.setPreferredSize(new Dimension(400, 50));
		panel_11.setMaximumSize(new Dimension(400, 50));
		panel_2.add(panel_11);
		panel_11.setLayout(new BoxLayout(panel_11, BoxLayout.X_AXIS));
		
		Component horizontalStrut_19 = Box.createHorizontalStrut(20);
		horizontalStrut_19.setMaximumSize(new Dimension(100, 32767));
		panel_11.add(horizontalStrut_19);
		
		JLabel lblNewLabel_9 = new JLabel("Number of neigbours/transmitter");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_11.add(lblNewLabel_9);
		
		Component horizontalStrut_20 = Box.createHorizontalStrut(20);
		panel_11.add(horizontalStrut_20);
		
		nrNeighbours_Transmitter = new JTextField();
		nrNeighbours_Transmitter.setHorizontalAlignment(SwingConstants.CENTER);
		nrNeighbours_Transmitter.setMaximumSize(new Dimension(40, 25));
		panel_11.add(nrNeighbours_Transmitter);
		nrNeighbours_Transmitter.setColumns(10);
		
		Component horizontalStrut_21 = Box.createHorizontalStrut(20);
		panel_11.add(horizontalStrut_21);
		
		JLabel lblNewLabel_10 = new JLabel("(1 - 32)");
		lblNewLabel_10.setForeground(Color.RED);
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 12));
		panel_11.add(lblNewLabel_10);
		
		Component horizontalStrut_22 = Box.createHorizontalStrut(20);
		horizontalStrut_22.setMaximumSize(new Dimension(100, 32767));
		panel_11.add(horizontalStrut_22);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setMaximumSize(new Dimension(32767, 5));
		verticalStrut_3.setMinimumSize(new Dimension(0, 5));
		verticalStrut_3.setPreferredSize(new Dimension(0, 5));
		panel_2.add(verticalStrut_3);
		
		JPanel panel_7 = new JPanel();
		panel_2.add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		
		JPanel panel_10 = new JPanel();
		panel_10.setMaximumSize(new Dimension(400, 75));
		panel_10.setMinimumSize(new Dimension(400, 75));
		panel_10.setPreferredSize(new Dimension(400, 75));
		panel_7.add(panel_10);
		panel_10.setLayout(new BoxLayout(panel_10, BoxLayout.X_AXIS));
		
		Component horizontalStrut_11 = Box.createHorizontalStrut(20);
		horizontalStrut_11.setMaximumSize(new Dimension(170, 32767));
		panel_10.add(horizontalStrut_11);
		
		//action button for solving the Frequency Planning problem
		
		JButton btnSolve = new JButton("SOLVE");
		btnSolve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				textArea.setText("");
				
				int transmitters;
				int frequencies;
				int transmitters_site;
				int neighbours_transmitter;
				
				try {
				transmitters=Integer.parseInt(nrTransmitters.getText());
				frequencies=Integer.parseInt(nrFrequencies.getText());
				transmitters_site=Integer.parseInt(nrTransmitters_Site.getText());
				neighbours_transmitter=Integer.parseInt(nrNeighbours_Transmitter.getText());
				
				if((transmitters<1||transmitters>10000)||(frequencies<1||frequencies>160)||(transmitters_site<1||transmitters_site>5)||(neighbours_transmitter<1||neighbours_transmitter>32))
				{
					JOptionPane.showMessageDialog(btnSolve, "WARNING!\n" + 
							"Please fill in the boxes with the values mentioned in parentheses" );
					nrTransmitters.setText("");
					nrFrequencies.setText("");
					nrTransmitters_Site.setText("");
					nrNeighbours_Transmitter.setText("");
				}
				else {
				SolverFactory<FrequencyPlan> solverFactory = SolverFactory.createFromXmlResource(
		                "com/example/frequencyPlanner/FrequencyPlannerSolve.xml",Thread.currentThread().getContextClassLoader());
				
		        Solver<FrequencyPlan> solver = solverFactory.buildSolver();
		        
		        // Load a problem
		        FrequencyPlan unsolvedFrequencyPlan = new FrequencyPlanGenerator().createFrequencyPlan(transmitters, frequencies, transmitters_site,neighbours_transmitter);
		       
		        // Solve the problem
		        FrequencyPlan solvedFrequencyPlan = solver.solve(unsolvedFrequencyPlan);

		      
				for(Transmitter iterator: solvedFrequencyPlan.getTransmitterList()) {
					if(iterator.getFrequency()==null)
					{
						 textArea.append("\n Transmitter id->: "+iterator.getId()+" | frequency---->null"+ " | site-->" + iterator.getSite().getIdSite()+"\n");
					System.out.println("\n Transmitter id->: "+iterator.getId()+" | frequency---->null"+ " | site-->" + iterator.getSite().getIdSite()); 
					
					}else
					    	if(iterator.getType() == "Hopping")
					    	{
					    		textArea.append("\n Transmitter id->: "+iterator.getId()+" | frequency---->"+iterator.getFrequency().getFrequencyValue()+ " | site-->" + iterator.getSite().getIdSite() +"  HOPPING, MAIO ->" + iterator.getMaio().getPosition() + "   Group -> "+iterator.getMaio().getFrequencyGroupNumber()+"\n");
					    		System.out.println("\n Transmitter id->: "+iterator.getId()+" | frequency---->"+iterator.getFrequency().getFrequencyValue()+ " | site-->" + iterator.getSite().getIdSite() +"  HOPPING, MAIO ->" + iterator.getMaio().getPosition() + "   Group -> "+iterator.getMaio().getFrequencyGroupNumber());
						    	
					    		
					    	}else
					    	{
					    		textArea.append("\n Transmitter id->: "+iterator.getId()+" | frequency---->"+iterator.getFrequency().getFrequencyValue()+ " | site-->" + iterator.getSite().getIdSite() +" NO HOPPING"+"\n");
					    		System.out.println("\n Transmitter id->: "+iterator.getId()+" | frequency---->"+iterator.getFrequency().getFrequencyValue()+ " | site-->" + iterator.getSite().getIdSite() +" NO HOPPING");
					    	}
					if(iterator.getNeighbours()!=null) {
					for(Transmitter secondIterrator:iterator.getNeighbours())
						if(secondIterrator.getFrequency()==null)
						{
							textArea.append(" \n TransmitterNeighbour id ->: "+secondIterrator.getId() + "  | frequency---->null"+"\n");
							System.out.println(" \n TransmitterNeighbour id ->: "+secondIterrator.getId() + "  | frequency---->null");
							
						}
							else
							{
					textArea.append(" \n TransmitterNeighbour id ->: "+secondIterrator.getId() + "  | frequency---->"+secondIterrator.getFrequency().getFrequencyValue()+"\n");
					System.out.println(" \n TransmitterNeighbour id ->: "+secondIterrator.getId() + "  | frequency---->"+secondIterrator.getFrequency().getFrequencyValue());
				    
							}
					}
					textArea.append("\n numarul de vecini : " + iterator.getNeighbours().size()+"\n");
					System.out.println("\n numarul de vecini : " + iterator.getNeighbours().size());
					textArea.append("\n");
					System.out.println("\n");
				}
				
				
				//System.out.println(solver.getBestScore());
				lblScore.setText(solver.getBestScore().toString());
				
//				for(Frequency frequency:solvedFrequencyPlan.getFrequencyList()) {
//					System.out.println("\n frequency id: ----->" + frequency.getFrequencyValue());
//				}
				FrequencyEasyScoreCalculator checkWhoViolatesRules = new FrequencyEasyScoreCalculator();
				checkWhoViolatesRules.calculateScore(solvedFrequencyPlan );
				textArea.append(checkWhoViolatesRules.getBadResult());
				
					}
				}
			
			catch(NumberFormatException err)
			{
				JOptionPane.showMessageDialog(null, "WARNING\nInvalid data, please fill again");
				nrTransmitters.setText("");
				nrFrequencies.setText("");
				nrTransmitters_Site.setText("");
				nrNeighbours_Transmitter.setText("");
			}
			}
		});
		
		
		
		btnSolve.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_10.add(btnSolve);
		
		Component horizontalStrut_12 = Box.createHorizontalStrut(20);
		horizontalStrut_12.setMaximumSize(new Dimension(170, 32767));
		panel_10.add(horizontalStrut_12);
		
		JPanel panel_9 = new JPanel();
		panel_9.setMaximumSize(new Dimension(32767, 200));
		panel_7.add(panel_9);
		panel_9.setLayout(new BoxLayout(panel_9, BoxLayout.X_AXIS));
		
		Component horizontalStrut_13 = Box.createHorizontalStrut(20);
		horizontalStrut_13.setMaximumSize(new Dimension(100, 32767));
		panel_9.add(horizontalStrut_13);
		
		JLabel lblNewLabel_5 = new JLabel("SCORE: ");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_9.add(lblNewLabel_5);
		
		
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblScore.setMinimumSize(new Dimension(160, 25));
		lblScore.setMaximumSize(new Dimension(160, 25));
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setPreferredSize(new Dimension(160, 25));
		lblScore.setBackground(SystemColor.text);
		lblScore.setOpaque(true);
		panel_9.add(lblScore);
		
		Component horizontalStrut_14 = Box.createHorizontalStrut(20);
		horizontalStrut_14.setMaximumSize(new Dimension(100, 32767));
		panel_9.add(horizontalStrut_14);
		
		Component horizontalStrut_15 = Box.createHorizontalStrut(20);
		horizontalStrut_15.setPreferredSize(new Dimension(10, 0));
		panel_1.add(horizontalStrut_15);
		
		JPanel panel_3 = new JPanel();
		panel_3.setMaximumSize(new Dimension(350, 500));
		panel_3.setBounds(new Rectangle(5, 5, 5, 5));
		panel_1.add(panel_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setMinimumSize(new Dimension(450, 500));
		scrollPane.setPreferredSize(new Dimension(460, 450));
		panel_3.add(scrollPane);
		
		
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane.setViewportView(textArea);
	}

}
