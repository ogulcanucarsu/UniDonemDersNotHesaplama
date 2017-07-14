package ogulcan.ucarsu.org.kounothesaplama;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    Button donemOrt;
    Button dersOrt;
    Button cikis;
    Button harf;
    Button finaldenKAL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Ana Menü");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));

        donemOrt=(Button) findViewById(R.id.donemOrt);
        dersOrt=(Button)findViewById(R.id.dersOrt);
        cikis=(Button)findViewById(R.id.cikis);
        harf=(Button)findViewById(R.id.harfNotlari);
        finaldenKAL = (Button)findViewById(R.id.finaldenKacAlmamLazim);


        donemOrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DonemOrtActivity.class));
            }
        });

        dersOrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,DersSecim.class));
            }
        });

        finaldenKAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=org.ucarsu.ogul.finaldenkacalmaliyim&hl=tr");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Play Store'dan Ücretsiz İndirebilirsiniz.",Toast.LENGTH_LONG).show();

            }
        });

        harf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,harfSecimActivity.class));
            }
        });

        cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

    }
}
