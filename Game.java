//package inf122GameScreen;

import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel;
import javax.swing.JButton; //imports JButton library
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout; //imports GridLayout library
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Game  extends JFrame {
	static int WIDTH = 1000; 
	static int HEIGHT = 1000;
	private static final int FIELD_WIDTH = 10;
	private JLabel playerLabel;
	private JLabel scoreLabel;
	private JTextField playerField;
	private JTextField scoreField;
	private JButton quit;
	private JButton newGame;
	private JButton statistics;
	
	private boolean tileClicked = false;
	
	
	
	public Game(int rows, int columns) {
		tileGrid = new JButton[rows][columns];
		
		setLayout(new BorderLayout()); //setting layout
		createLeftComponents(); //creating player + score components
		createRightComponents(); //creating quit/stats/new game buttons 

		JPanel result = new JPanel(); //new panel
		result.setLayout(new BorderLayout());
		result.add(createLeftPanel(), BorderLayout.WEST);
		result.add(createRightPanel(), BorderLayout.EAST);
		result.add(makeGrid(rows,columns), BorderLayout.CENTER);
		add(result);
		setSize(WIDTH, HEIGHT);
		
	}
	
	
	private Component createLeftPanel() { 
		//creates left panel with the player text box + input box
		JPanel player = new JPanel();
		player.add(playerLabel);
		player.add(playerField);
		
		JPanel score = new JPanel();
		score.add(scoreLabel);
		score.add(scoreField);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		panel.add(player);
		panel.add(score);
		
		return panel;
	}
	
	
	private void createLeftComponents()
	{
		//player name
		playerLabel = new JLabel("PLAYER: ");
		playerField = new JTextField(FIELD_WIDTH);
		playerField.setText("");
		
		//player score
		scoreLabel = new JLabel("SCORE: ");
		scoreField = new JTextField(FIELD_WIDTH);
		scoreField.setText("");
	}
	
	private JButton makeQuitButton() {
		JButton btn = new JButton("QUIT");
	    btn.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("quitting game");
	    		}
	    	});
	      
	      return btn;
	}
	
	private JButton makeNGButton() {
		JButton btn = new JButton("NEW GAME");
	    btn.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("restarting game");
	    		}
	    	});
	      
	      return btn;
	}
	
	private JButton makeStatsButton() {
		JButton btn = new JButton("STATISTICS");
	    btn.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println("showing stats page");
	    		}
	    	});
	      
	      return btn;
	}
	
	private void createRightComponents()
	{
		//creating the buttons on the right side
		quit = makeQuitButton();
		newGame = makeNGButton();
		statistics = makeStatsButton();
	}
	

	private Component createRightPanel() { 
		//adding the buttons to the right side
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5,3));
		
		
		for ( int i = 0; i < 6; i++ ) {
			panel.add(new JPanel());
		}
		panel.add(quit);
		panel.add(newGame);
		panel.add(statistics);
		for ( int i = 0; i < 6; i++ ) {
			panel.add(new JPanel());
		}
		
		return panel;	
	}
	
	private Component makeGrid(int rows, int columns) {
		//making the grid in the middle
		JPanel frame = new JPanel();
		frame.setLayout(new GridLayout(rows, columns));
		for ( int i = 0; i < rows; i++ ) {
			for ( int j = 0; j < columns; j++ ) {
				JButton btn = new JButton();
				int r = (int)(Math.random() * 256);
				int g = (int)(Math.random() * 256);
				int b = (int)(Math.random() * 256);
				btn.setBackground(new Color(r,g,b));
				
				tileClickedEvent(btn);
				
				btn.setName(String.format("%d,%d",i,j));
				
				frame.add(btn);
			}			
		}
		frame.setBackground(Color.RED);
		return frame;
	}
	
	private void tileClickedEvent(JButton btn) {
		btn.addActionListener(new ActionListener() {
	    	@Override
	    	public void actionPerformed(ActionEvent e) {
	    		System.out.println(String.format("tile clicked at location: %s",btn.getName()));
	    		tileClicked = !(tileClicked)
	    		}
	    	});
	}
	

	public static void main(String[] args) {
		JFrame frame = new Game(5,5);
		frame.setTitle("Candy Crush");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	

}