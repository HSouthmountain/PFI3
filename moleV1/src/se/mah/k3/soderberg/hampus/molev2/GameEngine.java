package se.mah.k3.soderberg.hampus.molev2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.widget.Toast;
import android.app.Application;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class GameEngine extends View implements Callback{

	private Handler myHandler;
	Paint background;
	Paint holePaint;
	public int counter = 0;
	private int score;
	private int fakeTime = 0;		//Används som en timer, räknas upp varje "varv". (ca: var 50:e millisekund)
	private boolean gameOver = false;
	
	private List<Drawable> myMoles = new ArrayList<Drawable>();
	
	public GameEngine (Context context) {
		
		super(context);
		background = new Paint();
		background.setColor(Color.parseColor("#339900"));
		holePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		holePaint.setColor(Color.BLACK);
		myHandler = new Handler (this);
		//new GameUpdateThread(myHandler).start();
		new GameUpdateThread(myHandler).start();
		createMoles();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event){
		
		Log.i("k3","Touch: "+event.getX());
		for (Drawable d : myMoles){
			if (d.pressed(event)== true){
				score++;
			}
		}
		update();
		return super.onTouchEvent(event);      
	}
	
	
	public boolean handleMessage(Message mess){
		Log.i("K3", "I handlemessage");
		update();
		return false;
	}
	
	private void update() {
		
		if (gameOver == true){
			//SystemClock.sleep(4000);		//Tänkt att ge time-out direkt efter Toast..
			gameOver = false;
		}
		
		//for (Drawable d : myMoles){
			//d.update();
			//d.hideMole();
	//	}
		
		//if ()
		//Toast scoreboard = Toast.makeText(getContext(), "Game over!\nScore: "+score, Toast.LENGTH_LONG);
		//scoreboard.show();
		//score = 0;
		
		/* **HIGHSCORE baserad på tid. (INT->String konvertering strular...)
		 * 
		 */ 
		for (Drawable d : myMoles){
			if (fakeTime > 999){
				//int insane = 0;
				d.hideMole();
				String toastText = "Your time "+fakeTime+"seconds!";
				Toast scoreboard = Toast.makeText(getContext(), "You've whacked "+score+"moles", Toast.LENGTH_LONG);
				scoreboard.show();
				fakeTime++;
				this.invalidate();
				gameOver = true;
				
				score = 0;		//Reset points
				fakeTime = 0;	//reset timer
			}
    		if (fakeTime < 1000){
    			d.update();
    			fakeTime++;
    			this.invalidate();
    		}
    		
    		
			
		}
		
		/*	*** HIGHSCORE baserat på poäng
		 * fakeTime++;
		if ( fakeTime > 200){
			 
			//show a toast + timeout...
			Toast scoreboard = Toast.makeText(getContext(), "Game over!\n"+score, Toast.LENGTH_LONG);   //Skapar en toast av strängen total
    		scoreboard.show();
    		
    		while (fakeTime >0){
    			
    		
    		for (Drawable d : myMoles){
    			d.hideMole();
    		}
    		fakeTime--;
    		}
		}
		 */
		
		
		this.invalidate();
	}

	private void createMoles() {
		// TODO Auto-generated method stub
		for (int i = 0; i< 3;i++){
			myMoles.add(new Mole (60+(i*100),50));
		}
		for (int i = 0; i< 3;i++){
			myMoles.add(new Mole (60+(i*100),125));
		}
		
		for (int i = 0; i< 3;i++){
			myMoles.add(new Mole (60+(i*100),205));
		}
	}

	/*
	public boolean handleMessage(Message arg0) {
		// TODO Auto-generated method stub
		//Log.i("k3", (e.getMessage());
		return false;
	}*/
	
	protected void onDraw (Canvas canvas){
		
		super.onDraw(canvas);
		canvas.drawPaint(background);
		
		//fixa livslängd
		//counter++;
		
		//Lite text
		
		
		//Ritar hål
		for (int i = 0; i< 3;i++){
			canvas.drawCircle((float)60+(i*100), (float)50, (float)30, holePaint);
		}
		for (int i = 0; i< 3;i++){
		canvas.drawCircle((float)60+(i*100), (float)125, (float)30, holePaint);
		}
		
		for (int i = 0; i< 3;i++){
			canvas.drawCircle((float)60+(i*100), (float)205, (float)30, holePaint);
		}
		
		for (Drawable d : myMoles){
			d.draw(canvas);
		}
		
	}
	
	
}
