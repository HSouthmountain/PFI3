package se.mah.k3.soderberg.hampus.EssEmEssV1;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class Messages extends Activity implements OnClickListener {
    
	//Essemmess server = EssemmessHelper.getServer(this);
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages);
        
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
				//server.post(sendText.getText().toString(), "test");
				
				
				break;
				
			case R.id.buttonGet:
				
				//Hämta meddelande + mitt
			//server.read("test");
				break;
		}
	}
	}