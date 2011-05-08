package se.mah.k3.soderberg.hampus.EssEmEssV1;

import java.util.ArrayList;

import se.k3.goransson.andreas.essemmesslib.Essemmess;
import se.k3.goransson.andreas.essemmesslib.EssemmessHelper;
import se.k3.goransson.andreas.essemmesslib.EssemmessListener;
import se.k3.goransson.andreas.essemmesslib.EssemmessLoginEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessPublishEvent;
import se.k3.goransson.andreas.essemmesslib.EssemmessReadEvent;
import se.k3.goransson.andreas.essemmesslib.Post;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;

public class Messages extends Activity implements OnClickListener,
		EssemmessListener {

	// Importerar credentials från activity1
	// Bundle share = getIntent().getExtras();
	// Bundle shared = getIntent().getBundleExtra("");
	Bundle share;
	private String stringMessages[] = {"placeholder"};
//stringMessages[0] =
	// ExpandableListView receivedMessages = (ExpandableListView)
	// findViewById(R.id.listViewReceivedMessages);

	private ListView receivedMessages;

	// Initierar ett server-objekt
	Essemmess server = EssemmessHelper.getServer(this);
	//ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);

	receivedMessages = (ListView) findViewById(R.id.listViewReceivedMessages);
	 //receivedMessages.setAdapter(adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , stringMessages));
	 //receivedMessages.setAdapter(adapter);
	
	//HÄR!!!
	/*ArrayList<String>data = new ArrayList<String>();
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
	receivedMessages.setAdapter(adapter);
	data.add("No messages!");*/

		// Set-up mina clickListeners
		Button sendButton = (Button) findViewById(R.id.buttonSend);
		sendButton.setOnClickListener(this);
		Button getButton = (Button) findViewById(R.id.buttonGet);
		getButton.setOnClickListener(this);
		
		
		server.addEssemmessEventListener(this);

		// Synkar mina share-objekt.
		share = getIntent().getExtras();
		String username = share.getString("username");
		String password = share.getString("password");

		// Loggar in på servern med credentials från startskärmen...
		server.login(username, password);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		EditText sendText = (EditText) findViewById(R.id.editTextMessageToSend);
		// EditText getText = (EditText)
		// findViewById(R.id.editTextMessageReceivedSent);
		EditText sendTag = (EditText) findViewById(R.id.editTextSendTag);

		switch (v.getId()) {
		case R.id.buttonSend:

			// Skicka meddelande
			Log.i("send", "messageSent");
			server.post(sendText.getText().toString(), sendTag.getText()
					.toString());

			break;

		case R.id.buttonGet:

			// Hämta meddelande + mitt
			
			

			server.read("HS");
Log.i("press","getbuttonclicked");
//adapter.notifyDataSetChanged();
			break;
		}
	}

	@Override
	public void NewEssemmessPosts(EssemmessReadEvent evt) {
		// TODO Auto-generated method stub
		Log.i("hej","readEventStartar");
		ArrayList<Post> messagesObjects = evt.getPosts();
		// Post messages[];
		// messages = (Post[]) messagesObjects.toArray();
		// String stringMessages[] = null;
		//stringMessages = new String[messagesObjects.size()];
		
		//int i = messagesObjects.size()-1;
		int i = 9998;
		stringMessages = new String[99999];
		
		//Log.i("h", "hej"+(messagesObjects.size()-1)+"hej");
		for (Post p : messagesObjects) {
			stringMessages[i] = p.message;
			
			if (i != 1) {
			//Log.i("test2"+i, p.message);
			//adapter.insert(p.message, i);
			i--;
			}
			
			
		}
		
		//Borde skapas längre upp och sedan tillkallas vid behov... (exempelvis här)
		ArrayList<String>data = new ArrayList<String>();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,data);
		receivedMessages.setAdapter(adapter);
		
		for (int e = 9998; e>i; e--){
			data.add(stringMessages[e]);
		}
		
		adapter.notifyDataSetChanged();
		
		//adapter.setNotifyOnChange(true);
		//adapter.notifyDataSetChanged();
		//adapter.notifyDataSetInvalidated();
		
		//Log.i("try", stringMessages[9997]);
		//Log.i("try", stringMessages[9996]);
		//Log.i("try", stringMessages[9995]);
		//Log.i("try", stringMessages[9994]);
}

	@Override
	public void NewEssemmessLogin(EssemmessLoginEvent evt) {
		// TODO Auto-generated method stub

	}

	@Override
	public void NewEssemmessPublish(EssemmessPublishEvent evt) {
		// TODO Auto-generated method stub

	}
}