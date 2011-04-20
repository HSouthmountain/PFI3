package se.mah.k3.soderberg.hampus.molev2;

import android.os.Handler;
import android.util.Log;

public class GameUpdateThread extends Thread{
	
	Handler _handler;
	private boolean _running = true;
	
	public GameUpdateThread(Handler handler){
_handler = handler;
	}
	
	public void run(){
		super.run();
		while(_running){
			try {
				Log.i("k3", "Thread Running");
				_handler.sendEmptyMessage(NORM_PRIORITY);
				Thread.sleep(50);
			} catch (InterruptedException e) {
				Log.i("k3", e.getMessage());
			}
			
	}
}
}
