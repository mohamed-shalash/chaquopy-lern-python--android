package com.example.chaquopylastversion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.samsao.messageui.views.MessagesWindow;

public class chatbot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        MessagesWindow messagesWindow =(MessagesWindow) findViewById(R.id.customized_messages_window);
        EditText message =(EditText) findViewById(R.id.message_box_text_field);

        message.setHint("type text here");
        messagesWindow.setBackgroundResource(R.color.design_default_color_primary);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        messagesWindow.getWritingMessageView().findViewById(R.id.message_box_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                messagesWindow.sendMessage(message.getText().toString());

                Python py =Python.getInstance();
                PyObject pyObject =py.getModule("scriptchatpox");
                PyObject object =pyObject.callAttr("main",message.getText().toString());

                messagesWindow.receiveMessage(object.toString());
            }
        });




    }
}