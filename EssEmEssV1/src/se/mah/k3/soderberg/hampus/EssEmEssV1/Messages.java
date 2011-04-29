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
	private String lv_arr[] = { "Android", "iPhone", "BlackBerry",
			"AndroidPeople" };

	// Initierar ett server-objekt
	Essemmess server = EssemmessHelper.getServer(this);
	ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.messages);

		receivedMessages = (ListView) findViewById(R.id.listViewReceivedMessages);
	 receivedMessages.setAdapter(adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1 , stringMessages));

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

			server.read("test");
Log.i("press","getbuttonclicked");
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
		
		int i = messagesObjects.size()-1;
		stringMessages = new String[i];
		
		Log.i("h", "hej"+messagesObjects.size()+"hej");
		for (Post p : messagesObjects) {
			stringMessages[i] = p.message;
			Log.i("test2"+i, p.message);
			//adapter.insert(p.message, i);
			i--;
		}
		
		adapter.setNotifyOnChange(true);
		adapter.notifyDataSetChanged();
		adapter.notifyDataSetInvalidated();
		
		Log.i("try", stringMessages[0]);
		Log.i("try", stringMessages[1]);
		Log.i("try", stringMessages[2]);
		Log.i("try", stringMessages[3]);
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