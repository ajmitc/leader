package leader;

import leader.event.GameEvent;
import leader.event.GameEventListener;
import leader.view.View;

public class Controller implements GameEventListener {
	private Model model;
	private View view;

	public Controller(Model model, View view) {
		this.model = model;
		this.view = view;
		this.model.addGameEventListener(this);
	}

	@Override
	public void handleGameEvent(GameEvent gameEvent) {
		if (gameEvent.getType() == GameEvent.TYPE_ADVANCE_TICK){
			if (this.model.getGame() != null)
				this.model.getGame().update();
			this.view.refresh();
			gameEvent.consume();
		}
	}
}