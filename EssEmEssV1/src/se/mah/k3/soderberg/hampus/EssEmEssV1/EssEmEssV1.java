
package se.mah.k3.soderberg.hampus.EssEmEssV1;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import se.k3.goransson.andreas.essemmesslib.EssemmessListener;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EssEmEssV1 extends Activity implements OnClickListener {

	private Essemmess server = EssemmessHelper.getServer(this);
	private String username;
	private String password;
	Bundle share = new Bundle();
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);//Ändra till main, bara för debug
        Button loginButton = (Button) findViewById(R.id.button1);
        loginButton.setOnClickListener(this);
        
        //temp
        
    }

	@Override
	public void onClick(View v) {
        EditText usernameText = (EditText) findViewById(R.id.editText1);
        EditText passwordText = (EditText) findViewById(R.id.editText2);
		switch (v.getId()) {
			case R.id.button1:
				//INLOGGNINGEN SKALL FINNAS MED!!
	//1.		//server.login(usernameText.getText().toString(), passwordText.getText().toString());
				// Testade att skicka meddelande direkt... 
	//2.		//server.post("PING", "test");
				
				//Spara credentials i Strängar. (säkerhetsrisk!!!)
				username = usernameText.getText().toString();
				password = passwordText.getText().toString();
				
				//TEMP:
				Intent messages = new Intent(EssEmEssV1.this, Messages.class); //Byter activity
				
				//Singleton shareObj = new Singleton();
				
				//Lägg credentials i en Bundle så activity 2 får tillgång...
				share.putString("username", username);
				share.putString("password", password);
				
				messages.putExtras(share);
				server.logout();			//Loggar ut innan byte...
				startActivity(messages);									//Byter activity
				//TEMP
				
				
				break;
		}
		
	}
	
	public void NewEssemmessLogin(EssemmessLoginEvent evt) {
		//**************************
		Log.i("Login","LoginMethodStarted");
		if (evt.getLoggedin().booleanValue() == true) {   //ÄNDRA TILL TRUE!!
			Log.i("Login", "True,ShouldLaunchMessagesActivityNow!");
			Intent messages = new Intent(EssEmEssV1.this, Messages.class); //Byter activity
			startActivity(messages);										//Byter activity
		}
	}
	
//protected void onStop(){
	//Log.i("bla", "ONSTOP");
	//LOGGA UT HÄR!!! (tror jag! ;))
//}
}