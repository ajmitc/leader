package leader.view;

import javax.swing.JFrame;

import leader.Model;

public class SwingView extends View
{
	private JFrame _frame;
	
	public SwingView( Model model, JFrame frame )
	{
		super( model );
		_frame = frame;
	}
	
	public void displayMainMenu()
	{
		
	}
}