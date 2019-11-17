package com.aka0586.www.aplikasiportalberita;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Notif extends AppCompatActivity {

    // deklarasi kode request
    public static final int notifikasi = 1;

    // deklarasi tombol
    Button btnkirim;
    // deklarasi edittext
    EditText txtjudul, txtpesan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notif);

        // inisialisasi komponen
        btnkirim = (Button) findViewById(R.id.btnkirim);
        txtjudul = (EditText) findViewById(R.id.txtJudul);
        txtpesan = (EditText) findViewById(R.id.txtpesan);

        // aksi letika tombol diklik
        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // membuat komponen Inten
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                // memanggil method untuk menampilkan notifikasi
                // dengan mengirimkan data yang dikirim dari komponen EditText
                tampilNotifikasi(txtjudul.getText().toString()
                        , txtpesan.getText().toString(), intent);
            }
        });
    }

    private void tampilNotifikasi(String s, String s1, Intent intent) {
        // membuat komponen pending intent
        PendingIntent pendingIntent = PendingIntent.getActivity(Notif.this
                , notifikasi, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // membuat komponen notifikasi
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Notif.this);
        Notification notification;
        notification = builder.setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentTitle(s)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(Notif.this.getResources()
                        , R.mipmap.ic_launcher))
                .setContentText(s1)
                .build();

        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager notificationManager = (NotificationManager) Notif.this
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(notifikasi, notification);
    }
}
