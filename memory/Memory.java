/*
* Assignment	Exam 3
* Program		Memory
* date			Nov 10, 2018
* Author		Jorge Jimenez
*/
package memory;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * @author Jorge Jimenez
 * 	 <p>This class is responsible for most of the functionality of the game.
 * 	
 *	 @see <a href="https://docs.oracle.com/javase/7/docs/api/java/awt/Image.html">Class Image</a>
 *	 @see <a href="https://docs.oracle.com/javase/7/docs/api/java/util/Timer.html">Class Timer</a>
 */
public class Memory
{
	// delay variables
	private Timer timer;
	private final static int DELAY_TIME = 1000;
	
	// flag to wait for the second tile clicked
	static boolean isFirstFliped = false;

	// Constant
	static int IMAGE_WIDTH = 240;
	static int IMAGE_HEIGHT = 180;

	// create a random object
	Random random = new Random();
	private JLabel score;
	private JLabel label;
	private JLabel matchesFound;
	private int attmps;
	private int numOfMatches;
	// holds the original unaltered images for the game
	List<ImageIcon> template = new ArrayList<ImageIcon>(
			Arrays.asList(
					new ImageIcon(MemoryApp.class.getResource("/memory/akuma.jpeg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/balrog.jpeg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/chunli.jpg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/guile.jpg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/ken.png")),
					new ImageIcon(MemoryApp.class.getResource("/memory/Ryu.jpeg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/akuma.jpeg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/balrog.jpeg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/chunli.jpg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/guile.jpg")),
					new ImageIcon(MemoryApp.class.getResource("/memory/ken.png")),
					new ImageIcon(MemoryApp.class.getResource("/memory/Ryu.jpeg"))));

	// will hold the altered images in the game
	private ArrayList<ImageIcon> images = new ArrayList<ImageIcon>(template);
	private Tile[] tiles = new Tile[12];

	/**
	 * Constructor - Memory
	 *
	 * @param panel
	 * @param score
	 */
	public Memory(JPanel panel, JLabel score, JLabel label, JLabel matchesFound)
	{

//		defaultImage.setImage(defaultImage.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		resizeImages();
		initGame(panel);
		updateMouseEvent(panel);
		this.score = score;
		this.label = label;
		this.matchesFound = matchesFound;
	}

	/**
	 * METHOD - initGame
	 * <p>Randomizes the images and adds them to an array
	 * Return Type - void
	 * @param panel	void
	 */
	@SuppressWarnings("unchecked")
	private void initGame(JPanel panel)
	{
		Collections.sort(template, new Unsort());
		
		for (int i = 0; i < tiles.length; i++)
		{
			tiles[i] = new Tile();
			tiles[i].setImage(template.remove(0));
		}

	}

	/**
	 * METHOD - mouseClick
	 *
	 * Return Type - void
	 * @param panel	void
	 */
	private void updateMouseEvent(JPanel panel)
	{
		for (Tile el : tiles)
		{
			addMouseEvent(el);

			panel.add(el.tile);
		}
		// resetMouseClick(panel);
	}

	/**
	 * METHOD - mouseEvent
	 * <p> Takes in a tile and adds an actionListener
	 *  <p> It uses Timer to create a delay
	 * Return Type - void
	 * @param el	void
	 */
	private void addMouseEvent(Tile el)
	{
		el.tile.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent evt)
			{
				
				el.tile.setIcon(el.getHiddenImg());
				el.setFlipped(true);
				el.tile.removeActionListener(this);


				// first flipped
				if (!Memory.isFirstFliped)
				{
					Memory.isFirstFliped = true;
				} else // second flipped
				{
					el.setFlipped(true);
					disableMouseEvent();
					timer.setRepeats(false);
					timer.start();

				}
				
				// Create the ActionListener for the timer
				timer = new Timer(DELAY_TIME, new ActionListener()
				{
					public void actionPerformed(ActionEvent evt)
					{
						
						compareTiles(el);
						Memory.isFirstFliped = false;
					}
				});
			}

		});
	}

	/**
	 * METHOD - disableTiles
	 * <p> Disables all the tiles while the game compares the chosen tiles.
	 * Return Type - void	void
	 */
	protected void disableMouseEvent()
	{
		for (Tile el : tiles)
		{
			ActionListener[] actionListeners = el.tile.getActionListeners();
			for (ActionListener actionListener : actionListeners)
				el.tile.removeActionListener(actionListener);
		}
	}

	/**
	 * METHOD - compareTiles
	 *
	 *<p> Finds the tiles that are fliped and not discovered yet and compares them. It also updates the tiles
	 * Return Type - boolean
	 * @param tile
	 * @return	boolean
	 */
	private boolean compareTiles(Tile tile)
	{
		Tile compareTile = null;
		Tile compareTile2 = null;

		boolean gotFirstTile = false;

		// System.out.println(tile.getHiddenImg().toString());

		//iterate throught the tiles and look for 
		// tiles that are flipped but not found yet
		for (Tile el : tiles)
		{
			if ((el.isFlipped() && !el.isFound()))
			{
				if (!gotFirstTile)
				{
					compareTile = el;
					gotFirstTile = true;
				} else
					compareTile2 = el;
			}
		}
		
		// Troubleshooting bugs
		if (compareTile == null || compareTile2 == null)
		{
			System.out.println("null pointer");
			return false;
		}
		// set to string to compare file names of the image
		String s1 = compareTile.getHiddenImg().toString();
		String s2 = compareTile2.getHiddenImg().toString();

		// if They are the same set their isFound to true
		if (s1.equals(s2))
		{
			compareTile.setFound(true);
			compareTile2.setFound(true);
			
			compareTile.foundMe();
			compareTile2.foundMe();
			
			numOfMatches++;
			matchesFound.setText("Number of matches: " + numOfMatches);
			label.setText("Match Found");
		} else// if they are not the same flip them back add the action listener back
		{
			label.setText("No Match Found");
			compareTile.setFlipped(false);
			compareTile2.setFlipped(false);
			compareTile.tile.setIcon(compareTile.getDefaultImg());
			compareTile2.tile.setIcon(compareTile.getDefaultImg());
			label.setText("Match Not Found");
		}
		// update the label
		this.score.setText("Number of attempts: " + Integer.toString(attmps));
		attmps++;
		
		updateAvailableTiles();
		timer.stop();
		if (gameover())
		{
			label.setText("");
			matchesFound.setFont(new Font("DIN Alternate", Font.BOLD, 13));
			String result = String.format("All matches found – Your score is %.1f", (float)numOfMatches/attmps);
			matchesFound.setText(result);
			
		}
		return s1.equals(s2);
	}


	/**
	 * METHOD - updateAvailableTiles
	 *	<p>Adds the action listener to tiles not discovered
	 * Return Type - void	void
	 */
	private void updateAvailableTiles()
	{
		for (Tile tile : tiles)
		{
			if (!tile.isFound())
				addMouseEvent(tile);
		}
	}

	public boolean gameover()
	{
		for (Tile tile : tiles)
		{
			if (!tile.isFound())
				return false;
		}
		return true;
	}
	/**
	 * METHOD - resizeImages
	 *	<p>Uses getScaledIstance to resize the images to fit in the panel.<p>
	 * Return Type - void	void
	 */
	private void resizeImages()
	{
		int size = images.size();
		// resize the image to fit the panel
		for (int i = 0; i < size; i++)
		{
			images.get(i).setImage(
					images.get(i).getImage().getScaledInstance(
							IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH)
			);
		}
		
	}
}
