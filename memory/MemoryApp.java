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

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Dimension;

/**
 * @author Jorge Jimenez
 * <p> This class contains the main method. 
 * <p> Creates the jFrame
 */
public class MemoryApp extends JFrame
{

	/**
	 * Field Type long
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MemoryApp frame = new MemoryApp();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MemoryApp()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		JPanel mainPanel = createMainPanel();
		
		contentPane.add(panel, BorderLayout.NORTH);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		
		JLabel attemptsLbl = new JLabel("Number of attempts: 0");
		JLabel lblAllMatchesFound = new JLabel("--");
		JLabel matchesFound = new JLabel("Number of Matches Found: 0");
		JLabel titleLbel = new JLabel("MEMORY");
		
		makeLabels(attemptsLbl, lblAllMatchesFound, matchesFound, titleLbel);
		
		addLabels(panel, attemptsLbl, lblAllMatchesFound, matchesFound, titleLbel);
		
		Memory game = new Memory(mainPanel, attemptsLbl, lblAllMatchesFound, matchesFound);
	}

	/**
	 * METHOD - addLabels
	 *
	 * Return Type - void
	 * @param panel
	 * @param attemptsLbl
	 * @param label
	 * @param matchesFound
	 * @param titleLbel	void
	 */
	private void addLabels(JPanel panel, JLabel attemptsLbl, JLabel label, JLabel matchesFound, JLabel titleLbel)
	{
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		panel.add(label);
		panel.add(titleLbel);
		panel.add(matchesFound);
		panel.add(attemptsLbl);
	}

	/**
	 * METHOD - makeLabels
	 *
	 * Return Type - void
	 * @param attemptsLbl
	 * @param label
	 * @param matchesFound
	 * @param titleLbel	void
	 */
	private void makeLabels(JLabel attemptsLbl, JLabel label, JLabel matchesFound, JLabel titleLbel)
	{
		titleLbel.setPreferredSize(new Dimension(150, 16));
		titleLbel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbel.setFont(new Font("DIN Alternate", Font.PLAIN, 15));
		attemptsLbl.setFont(new Font("DIN Alternate", Font.BOLD, 17));
		attemptsLbl.setPreferredSize(new Dimension(150, 16));
		attemptsLbl.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setPreferredSize(new Dimension(150, 16));
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("DIN Alternate", Font.BOLD, 17));
		matchesFound.setFont(new Font("DIN Alternate", Font.BOLD, 17));
		matchesFound.setPreferredSize(new Dimension(261, 16));
	}

	/**
	 * METHOD - createMainPanel
	 *
	 * Return Type - JPanel
	 * @return	JPanel
	 */
	private JPanel createMainPanel()
	{
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainPanel.setLayout(new GridLayout(3, 4, 20, 20));
		return mainPanel;
	}

}
