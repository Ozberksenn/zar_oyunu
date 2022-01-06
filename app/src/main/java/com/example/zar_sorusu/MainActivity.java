package com.example.zar_sorusu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView imageView1,imageView2;
    TextView textView1,textView3,textView2;
    ListView list1;
    ProgressBar progressBar1;
    Button button2,button1;
    ArrayList<String> dizi=new ArrayList<>();
    int[] gorseller={R.drawable.sifit,R.drawable.bir,R.drawable.iki,R.drawable.uc,R.drawable.dort,R.drawable.bes,R.drawable.alti};
    Timer zamanlayici=new Timer();
    int sure=20;
    int hakkimiz=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();




    }
    public void Tmr()
    {
        zamanlayici = new Timer();
        TimerTask gorev=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        sure--;
                        if (sure==-1)
                        {
                            oyun_bitir();
                        }
                        else
                        {
                            progressBar1.setProgress(sure);
                            textView3.setText(String.valueOf("Süre :"+sure));
                        }

                    }
                });
            }
        };
        zamanlayici.schedule(gorev,0,1000);
    }
    public void dizimizi_bagla()
    {
        ArrayAdapter<String> adaptor=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dizi);
        list1.setAdapter(adaptor);
    }
    public void init()
    {
        imageView1=findViewById(R.id.imageView1);
        imageView2=findViewById(R.id.imageView2);
        textView1=findViewById(R.id.textView1);
        textView3=findViewById(R.id.textView3);
        textView2=findViewById(R.id.textView2);
        list1=findViewById(R.id.list1);
        progressBar1=findViewById(R.id.progressBar1);
        progressBar1.setMax(sure);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button1.setEnabled(false);
        button2.setEnabled(true);

    }

    public void zarla(View view) {
        Random rastgele=new Random();
        int s1=rastgele.nextInt(6)+1;
        int s2=rastgele.nextInt(6)+1;
        imageView1.setImageResource(gorseller[s1]);
        imageView2.setImageResource(gorseller[s2]);
        int toplam = s1+s2;
        textView1.setText("Toplam :"+toplam);

        if((toplam==11) ||(toplam==7))
        {
            yaz("KAZANDINIZ...");
            oyun_bitir();

        }
        else if ((toplam==2) || (toplam==3) || (toplam==12))
        {
            yaz("KAYBETTİNİZ...");
            oyun_bitir();

        }
        else
        {
            hakkimiz--;
            if(hakkimiz==-1)
            {
                yaz("HAKKINIZ DOLMUŞTUR...");
                oyun_bitir();
            }
            else
            {
                String eklenecek="İlk : "+s1+" İkinci : "+s2+" Toplam : "+toplam;
                dizi.add(eklenecek);
                dizimizi_bagla();
                textView2.setText("Hakkınız :"+hakkimiz);
            }
        }
    }
    public void oyun_bitir()
    {
        zamanlayici.cancel();
        button1.setEnabled(false);
        button2.setEnabled(true);
    }

    public void yeni_oyun(View view) {
        sure=20;
        dizi.clear();
        dizimizi_bagla();
        Tmr();
        button1.setEnabled(true);
        button2.setEnabled(false);
        hakkimiz=5;
    }
    public void yaz(String gelen)
    {
        Toast.makeText(this,gelen,Toast.LENGTH_SHORT).show();
    }
}