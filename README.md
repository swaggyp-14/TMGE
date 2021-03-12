# Required

Java 10 or JavaFX  

# Instructions for running

0. Download release jar, 'java -jar TileMatchingGameEngine.jar'
1. Register usernames
2. Populate the # of fields you want for the active players sequentially. For example, if you want 2 players, fill out login field 1 and 2.
3. Click Login. This takes you to the launcher screen
4. You can now launch Bejeweled or Candy Crush

# Game Rules (Also found in design document)
**Bejeweled**
* Tiles can move 1 way in any direction. 
* A move is successful as long as it is within 1 unit. It does not have to result in a match. 
* Red cherry bombs are special. The gem you switch a cherry bomb with clears all gems on the board that are that color.
    * For example: You select a blue gem and switch it with a cherry bomb. It will remove all blue gems on the board.
* Pink dice are also special. They clear all tiles in a cross formation regardless of whether or not 3 were matched. 
* Switching players randomizes the board.


# JAR Note

We were unable to get the images to load even using ImageIO. We reverted it to display color *only for the jar build*

If you clone this repository and run it locally, it should display the images. 

The change was a follows

**on non-jar build:** btn.setIcon(new ImageIcon(resource.getDir(colorMap.get(map.getTile(i, j).getColor()))));

**on jar build:** btn.setBackground(colorMap.get(map.getTile(i, j).getColor()));

# Issues

If you have trouble running the Jar, try running it with Java 10
