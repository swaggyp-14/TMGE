package game;

import gamedata.Resource;
import gamedata.TileMap;
import facade.GameFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Game extends JFrame {
	private int width;
	private int height;
	private String gameName;
	private GameFacade gf;
	private int scorePerClear;
	private static final int FIELD_WIDTH = 10;
	private JLabel playerLabel;
	private JLabel scoreLabel;
	private JLabel prevHighLabel;
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
	private Resource resource;

	public Game(String gameName, TileMap map){
		this.gameName = gameName;
		gf = new GameFacade();
		this.map = map;
		// TODO - Make this customizable thru API
		this.colorMap = new HashMap<>();
		colorMap.put("R", Color.RED);
		colorMap.put("B", Color.BLUE);
		colorMap.put("P", Color.pink);
		colorMap.put("Y", Color.yellow);
		colorMap.put("G", Color.GREEN);
		colorMap.put("O", Color.orange);
		colorMap.put("INACTIVE",Color.BLACK);
		this.resource = new Resource(colorMap); //Map between Color --> resource path
		this.map.fillBoard();

		setLayout(new BorderLayout()); //setting layout
		createLeftComponents(); //creating player + score components
		createRightComponents(); //creating quit/stats/new game buttons
	}
	public void addColorEntry(String key, Color value) {
		this.colorMap.put(key, value);
	}
	public void addResourceEntry(Color key, String dir) {
		this.resource.addResource(key, dir);
	}
	public JFrame getFrame() {
		return this;
	}
	public void setGameDimensions(int width, int height) {
		this.width = width;
		this.height = height;
	}
	public void setGameName() {
		this.setTitle(this.gameName);
	}
	public void addAllComponents() throws IOException, ClassNotFoundException {
		parentPanel = new JPanel(); //new panel
		parentPanel.setLayout(new BorderLayout());
		parentPanel.add(createLeftPanel(), BorderLayout.WEST);
		parentPanel.add(createRightPanel(), BorderLayout.EAST);
		parentPanel.add(makeGrid(), BorderLayout.CENTER);
		add(parentPanel);
		setSize(this.width, this.height);
	}
	public void setScorePerClear(int score) {
		this.scorePerClear = score;
	}
	public void updateTextFields() throws IOException, ClassNotFoundException {
		playerField.setText(this.gf.getUserID());
		prevHighLabel.setText("Previous High: " + gf.getPreviousHigh(this.gameName));
	}
	private Component createLeftPanel() {
		//creates left panel with the player text box + input box
		JPanel player = new JPanel();
		player.add(playerLabel);
		player.add(playerField);

		JPanel score = new JPanel();
		score.add(scoreLabel);
		score.add(scoreField);

		JPanel previousHigh = new JPanel();
		previousHigh.add(prevHighLabel);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(4, 1));
		panel.add(player);
		panel.add(score);
		panel.add(previousHigh);

		return panel;
	}
	private void createLeftComponents() {
		//player name
		playerLabel = new JLabel("PLAYER: ");
		playerField = new JTextField(FIELD_WIDTH);

		//player score
		scoreLabel = new JLabel("SCORE: ");
		scoreField = new JTextField(FIELD_WIDTH);
		scoreField.setText("0");

		prevHighLabel = new JLabel("Previous High: ");
	}

	private JButton makeQuitButton(GameFacade gf, JFrame f, String gameName) {
		JButton btn = new JButton("Quit Program");
		btn.addActionListener(e -> {
			try {
				gf.handleExit(gameName);
				f.dispose();
			} catch (IOException ioException) {
				ioException.printStackTrace();
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
		btn.addActionListener(e -> {
			switch(e.getActionCommand()){
				case "Next Player":
					try {
						gf.handleSwitch(gameName);
						playerField.setText(gf.getUserID());
						this.map.fillBoard();
						updateTiles();
						TileFrame.revalidate();
						TileFrame.repaint();
					} catch (IOException | ClassNotFoundException ioException) {
						ioException.printStackTrace();
					}
					scoreField.setText(gf.getScore());
					if (gf.hasNextPlayer()) {
						btn.setText("Next Player");
					} else {
						btn.setText("");
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

	private void drawGrid() {
		TileFrame.setLayout(new GridLayout(map.getRow(), map.getColumn()));
		for (int i = 0; i < map.getRow(); i++) {
			for (int j = 0; j < map.getColumn(); j++) {
				JButton btn = new JButton();
				btn.setIcon(new ImageIcon(resource.getDir(colorMap.get(map.getTile(i, j).getColor()))));

				if ( !map.getTile(i,j).getIsActive() ) {
					btn.setVisible(false);
					btn.setEnabled(false);
				}

				btn.setOpaque(true);
				btn.setBorderPainted(false);
				tileClickedEvent(btn);
				map.checkMatches(); //after tiles are updated double check for matches
				map.updateBoard();
				btn.setName(String.format("%d,%d", i, j));
				TileFrame.add(btn);
			}
		}
		TileFrame.setBackground(Color.BLACK);
	}
	private JPanel updateTiles() throws IOException, ClassNotFoundException {
		TileFrame.removeAll();
		drawGrid();
		return TileFrame;
	}


	private Component makeGrid(){
		TileFrame = new JPanel();
		drawGrid();
		return TileFrame;
	}

	private void tileClickedEvent(JButton btn) {
		btn.addActionListener(e -> {
			try {
				System.out.println(btn.getName());
				updateTextFields();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			}
			tileClicked = !(tileClicked); //makes true
			if (!tileClicked) { //if false
				map.switchTiles(previousTile, btn.getName());
				map.checkMatches();
				map.updateBoard();
				try {
					updateTiles();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				}
				TileFrame.revalidate();
				TileFrame.repaint();
				previousTile = null;
				try {
					if(gf.hasNextPlayer()) {
						newGame.setText("Next Player");
					} else {
						newGame.setText("");
					}
					gf.updateScore(scorePerClear);
				} catch (IOException ioException) {
					ioException.printStackTrace();
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				}
				scoreField.setText(gf.getScore());
			} else {
				previousTile = btn.getName();
			}
		});
	}

}