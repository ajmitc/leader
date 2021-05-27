package leader;

import leader.game.event.GameEvent;
import leader.game.event.GameEventListener;
import leader.game.event.GameEventType;
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
		if (gameEvent.getType() == GameEventType.ADVANCE_TICK){
			if (this.model.getGame() != null)
				this.model.getGame().update();
			this.view.refresh();
			gameEvent.consume();
		}
	}
}