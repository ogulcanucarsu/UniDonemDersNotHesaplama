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
 * Created by ogulc on 10.06.2017.
 */

public class DersSecim extends AppCompatActivity {

    private Toolbar toolbar;
    Button Ileri;
    RadioGroup radioGroup;
    int deger=0;
    Bundle bundle=new Bundle();
    public static String Deger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derssecim);

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Ders İçeriği Seçim");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DersSecim.this,MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        Ileri=(Button)findViewById(R.id.btn_secIleri);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);

        Ileri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == -1) {
                    Toast.makeText(getApplicationContext(),"Lütfen Seçim Yapınız..", Toast.LENGTH_LONG).show();
                }
                else{
                    if (checkedRadioButtonId == R.id.RB1)
                    {
                        deger=1;
                        bundle.putInt(Deger,deger);
                    }
                    if(checkedRadioButtonId==R.id.RB2)
                    {
                        deger=2;
                        bundle.putInt(Deger,deger);
                    }
                    if(checkedRadioButtonId==R.id.RB3)
                    {
                        deger=3;
                        bundle.putInt(Deger,deger);
                    }
                    Intent intent = new Intent();
                    intent.putExtras(bundle);
                    intent.setClass(getApplicationContext(),DersOrtActivity.class);
                    startActivity(intent);
                }
            }

        });
    }

}
