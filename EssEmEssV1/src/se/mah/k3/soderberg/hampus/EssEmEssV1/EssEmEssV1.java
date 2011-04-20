package se.mah.k3.soderberg.hampus.EssEmEssV1;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EssEmEssV1 extends Activity implements OnClickListener {

	Essemmess server = EssemmessHelper.getServer(this);
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Button loginButton = (Button) findViewById(R.id.button1);
        loginButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) {
        EditText usernameText = (EditText) findViewById(R.id.editText1);
        EditText passwordText = (EditText) findViewById(R.id.editText2);
		switch (v.getId()) {
			case R.id.button1:
				//INLOGGNINGEN SKALL FINNAS MED!!
				server.login(usernameText.getText().toString(), passwordText.getText().toString());
				
				//TEMP:
				Intent messages = new Intent(EssEmEssV1.this, Messages.class); //Byter activity
				startActivity(messages);									//Byter activity
				//TEMP
				
				
				break;
		}
		
	}
	
	public void NewEssemmessLogin(EssemmessLoginEvent evt) {
		//**************************
		if (evt.getLoggedin() == true) {   //ÄNDRA TILL TRUE!!
			Intent messages = new Intent(EssEmEssV1.this, Messages.class); //Byter activity
			startActivity(messages);										//Byter activity
		}
	}
	
//protected void onStop(){
	//Log.i("bla", "ONSTOP");
	//LOGGA UT HÄR!!! (tror jag! ;))
//}
}