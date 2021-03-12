# TMGE

# Instructions for running

1. Register usernames
2. Populate the # of fields you want for the active players
3. Click Login
4. You can now launch Bejeweled or Candy Crush

# Issues

We were unable to get the images to load even using ImageIO. We reverted it to display color *only for the jar build*

If you clone this repository and run it locally, it should display the images. 

The change was a follows

**on non-jar build:** btn.setIcon(new ImageIcon(resource.getDir(colorMap.get(map.getTile(i, j).getColor()))));

**on jar build:** btn.setBackground(colorMap.get(map.getTile(i, j).getColor()));
