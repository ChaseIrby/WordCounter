/**
 * The Class WordCountGUI
 * Creates the interface and runs the JFileChooser
 * 
 * @author Chase Irby
 * @version %I%, %G%
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class WordCountGUI
{

	/**
	 * Instantiates a new word count gui.
	 */
	public WordCountGUI() 
	{
		// create the frame, panel, and buttons
		
		JFrame wordCountFrame = new JFrame("Word Counter");
		
		JPanel ctrlPanel = new JPanel();
		
		JButton getFileButton = new JButton("Count Words in a File");
		JButton exitButton = new JButton("Exit");
		
		wordCountFrame.setLayout(new FlowLayout());
		wordCountFrame.setResizable(false);
		wordCountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add the buttons and their functions
		
		ctrlPanel.add(getFileButton);
		ctrlPanel.add(exitButton);
		
		getFileButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {	
            	final File[] files; // create an array of files
            	JFileChooser chooser = new JFileChooser(); // create a JFileChooser object
            	chooser.setMultiSelectionEnabled(true); // allow the user to select multiple files via Ctrl
            	if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) // show the JFileChooser object
            	{
            		// get the selected files and for each one - create a thread for counting the words
            		files = chooser.getSelectedFiles();
            		if (files != null)
            		{
            				int threadNum = 0;
                			for (File f : files)
                			{
                				new WordCounter(f, ++threadNum).run();
                			}

            		}
            	}
                else
                {
                	// Warn the user if they closed the window
                	JOptionPane.showMessageDialog(null, "You closed the window!", "Oops!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
		
		exitButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
		});
		
		wordCountFrame.add(ctrlPanel);
		wordCountFrame.pack();
		wordCountFrame.setVisible(true);
	}
	
	/**
	 * The main method. Creates a new WordCountGUI object.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		WordCountGUI gui = new WordCountGUI();
	}

}
