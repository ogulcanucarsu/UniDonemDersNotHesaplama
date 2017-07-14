package ogulcan.ucarsu.org.kounothesaplama;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

/**
 * Created by ogulc on 13.06.2017.
 */

public class harfSecimActivity extends AppCompatActivity{

    Toolbar toolbar;
    RadioGroup rg;
    Button ileri;

    int deger=0;
    Bundle bundle=new Bundle();
    public static String Deger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secimharf);

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Üniversite Seçiniz");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(harfSecimActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        rg=(RadioGroup)findViewById(R.id.harfSecsRG);
        ileri=(Button)findViewById(R.id.btn_harfSec);


        ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedRadioButtonId = rg.getCheckedRadioButtonId();
                if (checkedRadioButtonId == -1) {
                    Toast.makeText(getApplicationContext(),"Lütfen Seçim Yapınız..", Toast.LENGTH_LONG).show();
                }
                else
                {
                    if (checkedRadioButtonId == R.id.harfSecKOU)
                    {
                        deger=1;
                        bundle.putInt(Deger,deger);
                        Intent intent = new Intent();
                        intent.putExtras(bundle);
                        intent.setClass(getApplicationContext(),harfActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Şuanlık Sadece Kocaeli Üniversitenin Harf Notları Vardır..", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });



    }
}
