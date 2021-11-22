package util;
/*Record time context for the game*/
public class GameTimer {
	
	public static GameTimer mTimer = new GameTimer();
	public static GameTimer getInstance() {return mTimer;}
	private GameTimer()
	{
		baseTime 	= 0;
		pausedTime  = 0;
		prevTime    = 0;
		currTime    = 0;
		isStop      = false;
	};
	
	//Members

	private long deltaTime;
	private long baseTime;
	private long pausedTime;
	private long stopTime;
	private long prevTime;
	private long currTime;
	
	private boolean isStop;
	
	
	
	
	
	public long DeltaTime()
	{
		return deltaTime;
	}
	
	
	
	public void Reset() 
	{
		baseTime = (System.currentTimeMillis());
		prevTime = baseTime;	
		stopTime = 0;
		isStop = false;
	}

	public void Stop()
	{
		if(!isStop)
		{
			stopTime = (System.currentTimeMillis());
			isStop = true;
		}
	}
	public void Tick()
	{
		if(isStop)
		{
			deltaTime = 0;
			return;
		}
		currTime = (System.currentTimeMillis());
		deltaTime = currTime - prevTime;
		prevTime = currTime;
		
	}
}
