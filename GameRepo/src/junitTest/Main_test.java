package junitTest;

import org.junit.Test;

import game.EntryPoint;
import game.GREGame;
import scenes.ResultScene;
import util.GameApplication;
import util.GameTimer;
import util.Texture;

public class Main_test {
	//Unit test begin
	//GameTimer.Reset()
	@Test
	public void test01() {
		GameTimer.getInstance().Reset();
	} 
	//Unit test begin
	
	//Integration Testing begin
    //GameApplication.initialize()
	@Test
	public void test02() {
		GameApplication game = new GREGame(); 
		game.initialize();

	} 
	
	//GameApplication.loadScene()
	@Test
	public void test03() {
		GameApplication game = new GREGame(); 
		game.initialize();
		ResultScene r=new ResultScene();
		game.loadScene(r);

	} 
	
	//GameApplication.initialize()
	@Test
	public void test04() {
		GameApplication game = new GREGame(); 
		game.initialize();
		ResultScene r=new ResultScene();
		game.loadScene(r);
	

	} 
	
	//System Testing
	//main
		@Test
		public void test05() {
			EntryPoint e=new EntryPoint();
			e.main(null);
		

		} 
	

}
