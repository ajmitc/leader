package leader;

import leader.view.View;

public class Main
{
  	public static void main( String ... args )
  	{
  		boolean headless = false;
  		for (String arg: args){
  			if (arg.equals("--headless"))
  				headless = true;
		}
    	Model model = new Model();
  		if (headless){
			leader.clview.View view = new leader.clview.View(model);
			ClController controller = new ClController(model, view);
			controller.run();
		}
  		else {
			View view = new View(model);
			new Controller(model, view);
			view.getFrame().setVisible(true);
		}
  	}
}