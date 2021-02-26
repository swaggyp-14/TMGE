package game;

import GameData.Tile;
import GameData.TileMap;

import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel;
import javax.swing.JButton; //imports JButton library
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Game extends JFrame {
	static int WIDTH = 1000;
	static int HEIGHT = 600;
	private static final int FIELD_WIDTH = 10;
	private JLabel playerLabel;
	private JLabel scoreLabel;
	private JTextField playerField;
	private JTextField scoreField;
	private JButton quit;
	private JButton newGame;
	private JButton statistics;
	private boolean tileClicked = false;
	private TileMap map;
	private JPanel TileFrame;
	private String previousTile;
	public Game(int rows, int columns) {

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

	public Game(GameData.TileMap map) {
		this.map = map;
		this.map.fillBoard();


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
		panel.setLayout(new GridLayout(4, 1));
		panel.add(player);
		panel.add(score);

		return panel;
	}


	private void createLeftComponents() {
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

	private void createRightComponents() {
		//creating the buttons on the right side
		quit = makeQuitButton();
		newGame = makeNGButton();
		statistics = makeStatsButton();
	}


	private Component createRightPanel() {
		//adding the buttons to the right side
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 3));


		for (int i = 0; i < 6; i++) {
			panel.add(new JPanel());
		}
		panel.add(quit);
		panel.add(newGame);
		panel.add(statistics);
		for (int i = 0; i < 6; i++) {
			panel.add(new JPanel());
		}

		return panel;

	}


	private JPanel updateTiles() {
		//making the grid in the middle
		System.out.println(map);
		TileFrame.removeAll();
		HashMap<String, Color> colorMap = new HashMap<String, Color>();
		colorMap.put("R", Color.RED);
		colorMap.put("B", Color.BLUE);
		colorMap.put("P", Color.pink);
		colorMap.put("Y", Color.yellow);
		colorMap.put("G", Color.GREEN);
		colorMap.put("O", Color.orange);

		TileFrame.setLayout(new GridLayout(map.getRow(), map.getColumn()));
		for (int i = 0; i < map.getRow(); i++) {
			for (int j = 0; j < map.getColumn(); j++) {
				JButton btn = new JButton();
				btn.setBackground(colorMap.get(map.getTile(i, j).getColor()));
				tileClickedEvent(btn);
				btn.setName(String.format("%d,%d", i, j));
				TileFrame.add(btn);
			}
		}
		TileFrame.setBackground(Color.RED);
		System.out.println(map);
		return TileFrame;
	}


	private Component makeGrid() {
		//making the grid in the middle
		HashMap<String, Color> colorMap = new HashMap<String, Color>();
		colorMap.put("R", Color.RED);
		colorMap.put("B", Color.BLUE);
		colorMap.put("P", Color.pink);
		colorMap.put("Y", Color.yellow);
		colorMap.put("G", Color.GREEN);
		colorMap.put("O", Color.orange);

		TileFrame = new JPanel();
		TileFrame.setLayout(new GridLayout(map.getRow(), map.getColumn()));
		for (int i = 0; i < map.getRow(); i++) {
			for (int j = 0; j < map.getColumn(); j++) {
				JButton btn = new JButton();
				btn.setBackground(colorMap.get(map.getTile(i, j).getColor()));
				tileClickedEvent(btn);
				btn.setName(String.format("%d,%d", i, j));
				TileFrame.add(btn);
			}
		}
		TileFrame.setBackground(Color.RED);
		return TileFrame;
	}

	private void tileClickedEvent(JButton btn) {
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(String.format("tile clicked at location: %s", btn.getName()));
				tileClicked = !(tileClicked);
				if (!tileClicked) {
					map.switchTiles(previousTile, btn.getName());
					map.checkMatches();
					map.updateBoard();
					updateTiles();
					TileFrame.revalidate();
					TileFrame.repaint();
					previousTile = "";
				} else {
					previousTile = btn.getName();
				}
			}
		});
	}


	public static void main(String[] args) {

		JFrame frame = new Game(new TileMap(5, 5));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}


}