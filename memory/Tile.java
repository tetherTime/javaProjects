/*
* Assignment	memory Exam 3
* Program		Tile
* date			Nov 11, 2018
* Author		Jorge Jimenez


			"         ______________",
            "        /             /|",
            "       /             / |",
            "      /____________ /  |",
            "     | ___________ |   |",
            "     ||           ||   |",
            "     ||   MEMORY  ||   |",
            "     ||   GAME!!! ||   |",
            "     ||___________||   |",
            "     |   _______   |  /",
            "    /|  (_______)  | /",
            "   ( |_____________|/",
            "    \\",
            ".=======================.",
            "| ::::::::::::::::  ::: |",
            "| ::::::::::::::[]  ::: |",
            "|   -----------     ::: |",
            "`-----------------------'"
 */
package memory;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 * @author Jorge Jimenez
 *  <p> This class will initialize a JButton. 
 *  <p>It assigns two images, a default image and the image that will be hidden.
 */
public class Tile
{
	public JButton tile = new JButton();
	
	// default image and the hidden image
	private ImageIcon defaultImage = new ImageIcon(MemoryApp.class.getResource("/memory/default.png"));
	private ImageIcon hiddenImage;
	
	// game flags
	private boolean isFlipped = false;
	private boolean isFound	  = false;
	
	/**
	 * Constructor - Tile
	 * <p> Initializes the JButton
	 */
	public Tile()
	{
		// resize default image
		defaultImage.setImage(defaultImage.getImage().getScaledInstance(Memory.IMAGE_WIDTH, Memory.IMAGE_HEIGHT, Image.SCALE_SMOOTH));
		this.tile.setBackground(Color.DARK_GRAY);
		this.tile.setOpaque(true);
		this.tile.setFocusable(false);
		this.tile.setHorizontalAlignment(JLabel.CENTER);
		this.tile.setVerticalAlignment(JLabel.CENTER);
		this.tile.setIcon(defaultImage);
	}
 
	public void foundMe()
	{
		this.tile.setBorder(new LineBorder(Color.PINK, 3, true));
	}
	/*****************************************************
	 * 				GETTERS & SETTERS					 *
	 *****************************************************/

	/**
	 * Field getter
	 * 
	 * @return the isFlipped
	 */
	public boolean isFlipped()
	{
		return isFlipped;
	}


	/**
	 * Field getter
	 * 
	 * @return the isFound
	 */
	public boolean isFound()
	{
		return isFound;
	}


	/**
	 * Field setter
	 * 
	 * @param isFlipped the isFlipped to set
	 */
	public void setFlipped(boolean isFlipped)
	{
		this.isFlipped = isFlipped;
	}


	/**
	 * Field setter
	 * 
	 * @param isFound the isFound to set
	 */
	public void setFound(boolean isFound)
	{
		this.isFound = isFound;
	}


	/**
	 * METHOD - setImage
	 *
	 * Return Type - void
	 * @param image	void
	 */
	public void setImage(ImageIcon image)
	{
		this.hiddenImage = image;
	}
	

	
	/**
	 * METHOD - getHiddenImg
	 *
	 * Return Type - ImageIcon
	 * @return	ImageIcon
	 */
	public ImageIcon getHiddenImg()
	{
		return this.hiddenImage;
	}
	
	/**
	 * METHOD - getDefaultImg
	 *
	 * Return Type - ImageIcon
	 * @return	ImageIcon
	 */
	public ImageIcon getDefaultImg()
	{
		return this.defaultImage;
	}
}
