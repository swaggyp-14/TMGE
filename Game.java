package inf122GameScreen;

import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel;
import javax.swing.JButton; //imports JButton library
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout; //imports GridLayout library

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
	
	public Game() {
		setLayout(new BorderLayout()); //setting layout
		createLeftComponents(); //creating player + score components
		createRightComponents(); //creating quit/stats/new game buttons 

		JPanel result = new JPanel(); //new panel
		result.setLayout(new BorderLayout());
		result.add(createLeftPanel(), BorderLayout.WEST);
		result.add(createRightPanel(), BorderLayout.EAST);
		result.add(makeGrid(), BorderLayout.CENTER);
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
	
	private void createRightComponents()
	{
		//creating the buttons on the right side
		quit = new JButton("QUIT");
		newGame = new JButton("NEW GAME");
		statistics = new JButton("STATISTICS");
	}
	

	private Component createRightPanel() { 
		//adding the buttons to the right side
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,3));
	
		panel.add(quit);
		panel.add(newGame);
		panel.add(statistics);
		
		return panel;	
	}
	
	private Component makeGrid() {
		//making the grid in the middle
		JPanel frame = new JPanel();
		frame.setLayout(new GridLayout(3, 3));
		frame.add(new JButton());
		frame.add(new JButton());
		frame.add(new JButton());
		frame.add(new JButton());
		frame.add(new JButton());
		frame.add(new JButton());
		frame.add(new JButton());
		frame.add(new JButton());
		frame.add(new JButton());
		frame.setBackground(Color.RED);
		return frame;
	}
	
	

	public static void main(String[] args) {
		JFrame frame = new Game();
		frame.setTitle("Candy Crush");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	

}
