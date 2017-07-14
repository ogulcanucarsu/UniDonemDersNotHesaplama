package ogulcan.ucarsu.org.kounothesaplama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

/**
 * Created by ogulc on 6.03.2017.
 */

public class harfActivity  extends AppCompatActivity {

    Toolbar toolbar;
    Bundle bundle;
    int degerAl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harf);


        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Harf Notları");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(harfActivity.this, harfSecimActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        degerAl=sayiAl();
        if(degerAl==1)
        {
            //KOU Buna göre textview ler tanımlanıp yukarda id ye göre textview.setText diyip basacaz
        }


    }

    private int sayiAl() {
        int sayi;
        bundle=getIntent().getExtras();
        sayi=bundle.getInt(harfSecimActivity.Deger);
        return sayi;
    }

}
