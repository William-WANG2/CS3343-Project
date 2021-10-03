package testCase;
import scenes.LoadingScene;
import util.*;

public class Test00 extends GameApplication{
	
	@Override
	public void initialize() {
		currScene = new TestDrawingScene();
		currScene.enter();
		super.initialize();
	}
}
