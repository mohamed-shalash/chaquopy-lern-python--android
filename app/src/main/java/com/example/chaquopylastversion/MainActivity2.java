package com.example.chaquopylastversion;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import java.io.ByteArrayOutputStream;

public class MainActivity2 extends AppCompatActivity {
    ImageView im_main;
    Button btn;
    String imagestring = "";

    BitmapDrawable drawable;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        btn =findViewById(R.id.button_act2_activity);
        im_main =findViewById(R.id.firist_act2_image);

        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        Python py =Python.getInstance();

        //tv.setText(object.toString());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawable =(BitmapDrawable) im_main.getDrawable();
                bitmap =drawable.getBitmap();
                imagestring=getstringimage(bitmap);

                PyObject pyObject =py.getModule("script2");
                PyObject object =pyObject.callAttr("main",imagestring);

                String str =object.toString();

                byte [] data =android.util.Base64.decode(str, Base64.DEFAULT);

                Bitmap bmp = BitmapFactory.decodeByteArray(data,0,data.length);
                im_main.setImageBitmap(bmp);

            }
        });
    }

    private String getstringimage(Bitmap bit){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        byte [] imagebyte = baos.toByteArray();
        String encoded =android.util.Base64.encodeToString(imagebyte, Base64.DEFAULT);
        return encoded;
    }
}