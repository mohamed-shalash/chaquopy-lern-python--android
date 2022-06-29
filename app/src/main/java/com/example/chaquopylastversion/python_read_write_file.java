package com.example.chaquopylastversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class python_read_write_file extends AppCompatActivity {

    Button btn;
    EditText txt1,txt2;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_python_read_write_file);

        btn =findViewById(R.id.python_activity_read_write_file_btn);
        txt1=findViewById(R.id.python_activity_read_write_file_text1);
        txt2=findViewById(R.id.python_activity_read_write_file_text2);

        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));

        Python py =Python.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data =txt1.getText().toString();
                PyObject pyObject =py.getModule("python_file_script");
                PyObject object =pyObject.callAttr("main",data);

                String str =object.toString();
                txt2.setText(str);
            }
        });
    }
}