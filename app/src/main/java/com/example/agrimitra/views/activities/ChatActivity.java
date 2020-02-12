package com.example.agrimitra.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.agrimitra.R;
import com.example.agrimitra.views.models.ChatBoxAdapter;
import com.example.agrimitra.views.models.Message;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {



    public RecyclerView recyclerView;
    public List<Message> messageList;
    public ChatBoxAdapter chatBoxAdapter;
    public EditText messageTxt;
    public Button send;
    private Socket socket;
    private String nickname;
    private Message m;
    SharedPreferences sharedpreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        messageTxt = findViewById(R.id.message);
        send = findViewById(R.id.send);
        messageList = new ArrayList<>();
        recyclerView = findViewById(R.id.messagelist);
        sharedpreferences = getSharedPreferences(Login.MyPREFERENCES, Context.MODE_PRIVATE);

        // nickname = (String) getIntent().getExtras().getString(MainActivity.NICKNAME);
        nickname = sharedpreferences.getString("name",null);




        try {
            socket = IO.socket("http://10.0.2.2:3000");
            socket.connect();
            socket.emit("india", nickname);


        } catch (Exception e) {
            e.printStackTrace();
        }




        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!messageTxt.getText().toString().isEmpty()) {
                    socket.emit("messagedetection", nickname, messageTxt.getText().toString());
                    messageTxt.setText(" ");
                }
            }
        });

        socket.on("userdisconnect", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String data = (String) args[0];
                        Toast.makeText(ChatActivity.this, data, Toast.LENGTH_SHORT);
                    }
                });
            }
        });



        socket.on("message", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject data = (JSONObject) args[0];


                        try {
                            Log.d("Hello wolrd", "run: "+data);
                            String nickname = data.getString("senderNickname");
                            String message = data.getString("message");
                            m = new Message(nickname, message);
                            messageList.add(m);
                            Log.d("Chatting app", "run: "+m.getMessage());


                            chatBoxAdapter = new ChatBoxAdapter(messageList);

                            chatBoxAdapter.notifyDataSetChanged();

                            recyclerView.setAdapter(chatBoxAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
        socket.on("userjoinedthechat", new Emitter.Listener() {
            @Override
            public void call(final Object... args) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("Qwerty", "run: Evetn");
                        String data = (String) args[0];
                        // get the extra data from the fired event and display a toast
                        Toast.makeText(ChatActivity.this, data, Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.disconnect();
    }

}
