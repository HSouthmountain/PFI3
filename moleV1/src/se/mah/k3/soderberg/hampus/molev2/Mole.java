package se.mah.k3.soderberg.hampus.molev2;

import java.util.Random;

import android.view.*;
import android.graphics.Bitmap;
import android.graphics.Picture;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;
import se.mah.k3.soderberg.hampus.molev2.R;
import android.graphics.drawable.*;
import android.os.Bundle;
import android.os.*;
import android.graphics.BitmapFactory;

public class Mole implements Drawable {

	private float _posX;
	private float _posY;
	public int alpha;
	private Paint _p;		//röd
	private Paint _p2;		//gul
	private Paint _p3;		//blå
	private float _radius = 20;
	private boolean alive = false;
	private boolean hit;
	//private pictureDrawable =
	//private Bitmap myPic = (Bitmap)findViewByID(R.drawable.moleup);
	//TextView textView = (TextView)findViewById(R.id.textView2);
	//private Picture myPic = new Picture(R.id.)
	
public Mole (float posX, float posY){
	
	_posX = posX;
	_posY = posY;
	alpha= 255;
	_p = new Paint(Paint.ANTI_ALIAS_FLAG);
	_p.setARGB(255, 200, 16, 32);
	_p2 = new Paint(Paint.ANTI_ALIAS_FLAG);
	_p2.setARGB(255, 50, 240, 50);
	_p3 = new Paint(Paint.ANTI_ALIAS_FLAG);
	_p3.setARGB(255, 16, 50, 200);
	alive = false;
	
}
	
	public void update(){
		
		Random randGen = new Random();
		if (randGen.nextInt(100)>98){
			alpha = 255;
			alive = true;
		}
			if (alpha > 0){
			//alpha = alpha-5;
		}
	}
	
	public void draw(Canvas c){
		if (alpha > 0){
			alpha= alpha -20;
		} else if (alpha <= 0){
			alpha =0;
			alive=false;
		}
		
		if (alive){
			c.drawCircle(_posX,_posY,_radius, _p3);
			c.drawCircle(_posX-10, _posY-8, _radius/4, _p2);
			c.drawCircle(_posX+10, _posY-8, _radius/4, _p2);
			c.drawCircle(_posX,_posY+4,9, _p);
			
			//Testa rita en bild här eller bestäm sökväg till vilken bild som skall ritas. Om alt.2 måste alla moles ALLTID vara synliga.(istället används två bilder, antingen synlig eller osynlig...) ;)
			
			//Försök till att rita bild:
			//c.drawPicture();
			//TextView textView = (TextView)findViewById(R.id.textView2);
			//c.drawPicture(R.drawable.moleup, 32);
			//c.drawBitmap(R.drawable.moleup, _posX, _posY, null);
			 	
		}
		
	}
	
	public boolean pressed(MotionEvent m){
		Log.i("K3", "Pressed");
		boolean hit = false;
		float dx = (_posX - m.getX());
		float dy = (_posY) - m.getY();
		float dist = (float) Math.sqrt(dx*dx+dy*dy);
		
		if (dist < _radius) {
			alive = false;
			alpha = 0;
			hit = true;
		
		}
		return hit;
	}
	
	
	public void hideMole(){
		alpha = 0;
		hit = false;
		alive= true;
	}
}
