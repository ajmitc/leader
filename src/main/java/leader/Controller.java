package leader;

public class Controller
{
	private Model _model;
	public View _view;
	
	public Controller( Model model, View view )
	{
		_model = model;
		_view  = view;
		
		_view.displayMainMenu();
	}
}