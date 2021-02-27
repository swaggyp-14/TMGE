package game;

import GameData.TileMap;
import facade.GameFacade;

import javax.swing.JFrame; //imports JFrame library
import javax.swing.JLabel;
import javax.swing.JButton; //imports JButton library
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Game extends JFrame {
	static int WIDTH = 1000;
	static int HEIGHT = 600;
	private String gameName;
	private GameFacade gf;
	private int scorePerClear;
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
	private Map<String, Color> colorMap;
	private JPanel parentPanel;

	public Game(String gameName, TileMap map) throws Exception {
		this.gameName = gameName;
		gf = new GameFacade();
		// TODO - Make this customizable thru API
		this.colorMap = new HashMap<String, Color>();
		colorMap.put("R", Color.RED);
		colorMap.put("B", Color.BLUE);
		colorMap.put("P", Color.pink);
		colorMap.put("Y", Color.yellow);
		colorMap.put("G", Color.GREEN);
		colorMap.put("O", Color.orange);
		this.map = map;
		this.map.fillBoard();

		setLayout(new BorderLayout()); //setting layout
		createLeftComponents(); //creating player + score components
		createRightComponents(); //creating quit/stats/new game buttons

		parentPanel = new JPanel(); //new panel
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(createLeftPanel(), BorderLayout.WEST);
		parentPanel.add(createRightPanel(), BorderLayout.EAST);
		parentPanel.add(makeGrid(), BorderLayout.CENTER);
		add(parentPanel);
		setSize(WIDTH, HEIGHT);

	}
	public void setScorePerClear(int score) {
		this.scorePerClear = score;
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
		playerField.setText(this.gf.getUserID());

		//player score
		scoreLabel = new JLabel("SCORE: ");
		scoreField = new JTextField(FIELD_WIDTH);
		scoreField.setText("0");
	}

	private JButton makeQuitButton(GameFacade gf, JFrame f, String gameName) {
		JButton btn = new JButton("Quit Program");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					gf.handleExit(gameName);
					f.dispose();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});

		return btn;
	}

	private JButton makeNGButton(GameFacade gf, String gameName) {
		JButton btn;
		if (gf.hasNextPlayer()) {
			btn = new JButton("Next Player");
		} else {
			btn = new JButton("");
		}
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				switch(e.getActionCommand()){
					case "Next Player":
						try {
							gf.handleSwitch(gameName);
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
						playerField.setText(gf.getUserID());
						scoreField.setText(gf.getScore());
						if (gf.hasNextPlayer()) {
							btn.setText("Next Player");
						} else {
							btn.setText("");
						}

				}
			}
		});

		return btn;
	}

	private JButton makeStatsButton(GameFacade gf) {
		JButton btn = new JButton("Statistics");
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
		quit = makeQuitButton(this.gf, this, this.gameName);
		newGame = makeNGButton(this.gf, this.gameName);
		statistics = makeStatsButton(this.gf);
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
		TileFrame.removeAll();

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


	private Component makeGrid() {

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
				tileClicked = !(tileClicked);
				if (!tileClicked) {
					map.switchTiles(previousTile, btn.getName());
					map.checkMatches();
					map.updateBoard();
					updateTiles();
					TileFrame.revalidate();
					TileFrame.repaint();
					previousTile = null;
					gf.updateScore(scorePerClear);
					scoreField.setText(gf.getScore());
				} else {
					previousTile = btn.getName();
				}
			}
		});
	}

}