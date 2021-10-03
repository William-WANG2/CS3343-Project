package testCase;
import scenes.LoadingScene;
import util.*;

public class TestDrawingApp extends GameApplication{
	
	@Override
	public void initialize() {
		currScene = new TestDrawingScene();
		currScene.enter();
		super.initialize();
	}
}
