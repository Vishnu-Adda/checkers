package scene;

import subscene.CheckersSubScene;
import subscene.HighScoresSubScene;
import subscene.HowToPlaySubScene;
import subscene.MainMenuSubScene;
import subscene.NewGameSubScene;
import subscene.SubScenes;

/**
 * This scene handles each of the views for the pre-game menu, including:
 * - Main Menu
 * - Difficulty Selection
 * - High Scores
 * - How to play
 * 
 * @author Andrew Johnston
 *
 */

public class MenuScene extends CheckersScene {
	private SceneManager sceneManager;
	
	private CheckersSubScene activeScene;
	private CheckersSubScene mainMenu;
	private CheckersSubScene newGame;
	private CheckersSubScene highScores;
	private CheckersSubScene howToPlay;
	
	/**
	 * Initialize the scene with a reference to the scene manager class so
	 * we can perform a transition from one scene to another.
	 * @param sceneManager SceneManager
	 */
	public MenuScene (SceneManager sceneManager) {
		this.sceneManager = sceneManager;
		
		setup();
	}
	
	/**
	 * Set up each one of the subscenes for the main-menu scene.
	 */
	private void setup () {
		super.setBackground("file:resources/background.png");
		
		newGame = new NewGameSubScene(this);
		add(newGame);
		
		highScores = new HighScoresSubScene(this);
		add(highScores);
		
		howToPlay = new HowToPlaySubScene(this);
		add(howToPlay);
		
		mainMenu = new MainMenuSubScene(this);
		add(mainMenu);
		activeScene = mainMenu;
	}

	@Override
	public void segueToSubScene(SubScenes subScene) throws Exception {
		CheckersSubScene segueTo = null;
		
		switch (subScene) {
		case MAIN_MENU:
			segueTo = mainMenu;
			break;
			
		case NEW_GAME:
			segueTo = newGame;
			break;
			
		case HIGH_SCORES:
			segueTo = highScores;
			break;
			
		case HOW_TO_PLAY:
			segueTo = howToPlay;
			break;
			
		default:
			throw new Exception("Invalid Segue: " + subScene.toString());
		}
		
		if (segueTo != null && activeScene != segueTo) {
			activeScene.transitionScene(false);
			segueTo.transitionScene(true);
			activeScene = segueTo;
		}
	}
}
