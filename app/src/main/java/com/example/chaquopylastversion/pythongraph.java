package com.example.chaquopylastversion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class pythongraph extends AppCompatActivity {
    ImageView im_main;
    Button btn;
    EditText txt1,txt2;
    String x_data ,y_data;

    BitmapDrawable drawable;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pythongraph);

        btn =findViewById(R.id.python_activity_btn);
        im_main =findViewById(R.id.python_activity_image);
        txt1=findViewById(R.id.python_activity_text1);
        txt2=findViewById(R.id.python_activity_text2);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py =Python.getInstance();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x_data =txt1.getText().toString();
                y_data =txt2.getText().toString();

                PyObject pyObject =py.getModule("script3");
                PyObject object =pyObject.callAttr("main",x_data,y_data);

                String str =object.toString();

                byte [] data =android.util.Base64.decode(str, Base64.DEFAULT);

                Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                im_main.setImageBitmap(bmp);
            }
        });
    }
}