package se.mah.k3.soderberg.hampus.EssEmEssV1;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Messages extends Activity implements OnClickListener {
    
	//Importerar credentials från activity1
	//Bundle share = getIntent().getExtras();
	//Bundle shared = getIntent().getBundleExtra("");
	Bundle share;
	
   
    
    //Initierar ett server-objekt
	Essemmess server = EssemmessHelper.getServer(this);
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        
        //Set-up mina clickListeners
        Button sendButton = (Button) findViewById(R.id.buttonSend);
        sendButton.setOnClickListener(this);
        Button getButton = (Button) findViewById(R.id.buttonGet);
        getButton.setOnClickListener(this);
       
        
        //Synkar mina share-objekt.
        share = getIntent().getExtras();
        String username = share.getString("username");
        String password = share.getString("password");
        
        //Loggar in på servern med credentials från startskärmen...
        server.login(username, password);
   }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText sendText = (EditText) findViewById(R.id.editTextMessageToSend);
		EditText getText = (EditText) findViewById(R.id.editTextMessageReceivedSent);
        EditText sendTag = (EditText) findViewById(R.id.editTextMessageToSend);
		
        
       switch (v.getId()) {
			case R.id.buttonSend:
				
				//Skicka meddelande
				Log.i("send", "messageSent");
				server.post(sendText.getText().toString(), "HS");
				
				
				break;
				
			case R.id.buttonGet:
				
				//Hämta meddelande + mitt
			server.read("test");
			
				break;
		}
	}
	}