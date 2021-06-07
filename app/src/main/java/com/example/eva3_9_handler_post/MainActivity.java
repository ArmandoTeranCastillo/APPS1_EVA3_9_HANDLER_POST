package com.example.eva3_9_handler_post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwMostrar;
    Handler handler = new Handler();
    //Trabajo Pesado en segundo plano
    //Handler --> Loop (cambios en android para manejo de UI en los hilos)
    Runnable background = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    handler.post(foreground);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    };
    //Trabajo con la UI
    Runnable foreground = new Runnable() {
        @Override
        public void run() {
            txtVwMostrar.append("Hola mundo!! \n");
        }
    };
    Thread thread;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMostrar = findViewById(R.id.txtVwMostrar);
        thread = new Thread(background);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }
}