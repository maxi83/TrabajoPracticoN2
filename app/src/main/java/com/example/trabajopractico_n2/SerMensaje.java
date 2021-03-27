package com.example.trabajopractico_n2;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.util.Log;

import androidx.annotation.Nullable;



public class SerMensaje extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }
    public void onCreate() {
        super.onCreate();
    }
@Override
public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread()
        {
public void run() {
        while (true){
            Log.d("prueba", "Mostrar ultimos 5 mensajes");
            Uri mensajes = Uri.parse(Telephony.Sms.CONTENT_URI.toString());
            Cursor cursor = getContentResolver().query(mensajes, null,
                    null, null, null);

            String fecha = null;
            String id = null;
            String contenido = null;
            int flag = 0;


            if (cursor.getCount() > 0) {

                while (cursor.moveToNext() && flag < 5) {

                    id = cursor.getString(cursor.getColumnIndex(Telephony.Sms._ID));
                    fecha = cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE));
                    contenido = cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY));

                    Log.d("prueba", id);
                    Log.d("prueba", fecha);
                    Log.d("prueba", contenido);
                    flag++;
                }

            }
                 try{
                 Thread.sleep(9000);
                } catch (InterruptedException e){
                Log.d("prueba", "ERROR: " + e.getMessage().toString());
                }
             }
            }
        }.start();
        return START_STICKY;
    }

    @Override


    public void onDestroy() {
        super.onDestroy();
    }
}