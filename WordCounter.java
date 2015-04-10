/**
 * The Class WordCounter.
 * Creates threads and oversees what they do
 * 
 * @author Chase Irby
 * @version %I%, %G%
 */
import java.io.File;
import java.util.Scanner;
import java.awt.*;
import javax.swing.*;

public class WordCounter implements Runnable
{
	private File file;
	private String fileName;
	private String textFromFile;
	private int threadID;
	private int numWords;
	private Scanner scanner;
	
	private JFrame frame;
	private JPanel results2;
	private JPanel resultsText;
	private JLabel results;
	private JTextArea copiedText;
	private JScrollPane areaScrollPane;
	
	/**
	 * Instantiates a new word counter thread
	 *
	 * @param f the file to create a thread from
	 * @param i the thread number
	 */
	public WordCounter(File f, int i) 
	{
		this.file = f;
		this.threadID = i;
		this.textFromFile = "";
		this.numWords = 0;
		this.fileName = this.file.toString();
		try
		{
			this.scanner = new Scanner(this.file);
		}
		catch (Exception exception)
		{
			
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * What each thread will do
	 */
	public void run()
	{
		try
		{
			while (scanner.hasNext("\\S+"))
			{
				this.textFromFile += scanner.next("\\S+") + " ";
				this.numWords++;
			}
			
			// creates a new Jframe with results and the text upon each thread completion
			this.frame = new JFrame("Results for thread " + threadID);
			this.frame.setLayout(new GridBagLayout());
			GridBagConstraints gc = new GridBagConstraints();
			gc.weightx = 0.5;
			gc.weighty = 0.5;
			this.frame.setSize(200, 200);
			this.frame.setResizable(false);
			this.results2 = new JPanel();
			this.resultsText = new JPanel();
			this.results = new JLabel("Thread " + this.threadID + ": the file \"" + this.fileName + "\" has " + this.numWords + " words.");
			this.copiedText = new JTextArea(textFromFile, 30, 50);
			this.copiedText.setEditable(false);
			this.copiedText.setLineWrap(true);
			this.copiedText.setWrapStyleWord(true);
			this.areaScrollPane = new JScrollPane(copiedText);
			this.areaScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
			this.areaScrollPane.setPreferredSize(new Dimension(500, 500));
			this.results2.add(this.results);
			this.resultsText.add(this.areaScrollPane);
			gc.gridx = 0;
			gc.gridy = 0;
			this.frame.add(this.results2, gc);
			gc.gridx = 0;
			gc.gridy = 1;
			this.frame.add(this.resultsText, gc);
			this.frame.pack();
			this.frame.setVisible(true);
			this.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		catch (Exception exception)
		{
			
		}
	}

}
