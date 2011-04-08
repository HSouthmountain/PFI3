package se.mah.k3.soderberg.hampus.exercise2_v3;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import android.util.Log;

public class Excercise2_v3 extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Set up click listeners for buttons
        
        View continueButton = findViewById(R.id.button1); 
        continueButton.setOnClickListener(this);
        
    }
    
    public void onClick(View v) {
    	switch (v.getId()){
    	case R.id.button1:
    		

    		//MY CODE GOES HERE!!
    		
    		
    		EditText rubrik = (EditText)findViewById(R.id.editText1); //Sparar Tag
    		String rubrikText = rubrik.getText().toString();		  //sparar tag
    		
    		EditText meddelande = (EditText)findViewById(R.id.editText2); //sparar meddelande
    		 String meddelandeText =(meddelande.getText().toString());	  //sparar meddelande

    		 String total = rubrikText+"\n"+meddelandeText;				//Lägger ihop "tag" och "meddelande"
    		
    		Toast t = Toast.makeText(this, total, Toast.LENGTH_LONG);   //Skapar en toast av strängen total
    		t.show();													//Visar min toast
    		
    		rubrik.setText("");											//rensar text
    		meddelande.setText("");										//rensar text
    		
    	}
    		
    	}
    }
