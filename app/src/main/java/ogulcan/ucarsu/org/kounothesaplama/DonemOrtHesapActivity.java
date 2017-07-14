package ogulcan.ucarsu.org.kounothesaplama;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.InputFilter;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by ogulc on 3.03.2017.
 */
public class DonemOrtHesapActivity extends AppCompatActivity{

    String sayiDers;
    Bundle bundle;

    Button hesapla;
    Toolbar toolbar;
    Spinner spKredi = null;
    Spinner spHarf  = null;
    EditText dersAdi= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donemortalamahesap);

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Dönem Ortalaması Hesaplama");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DonemOrtHesapActivity.this,DonemOrtActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        sayiDers=sayiAl();
        tableBas(sayiDers);

    }

	// Bu fonksiyon ile kullanıcının girdiği ders sayısı kadar dinamik olarak spinner basıyorum kredi ve notu seçebilmesi için.
    private void tableBas(String sayiDers) {

        final Integer deger=Integer.parseInt(sayiDers);
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        stk.setGravity(Gravity.CENTER);

        TableRow tbrow0 = new TableRow(this);

        TextView AdTview = new TextView(this);
        AdTview.setText(" Ders Adı ");
        AdTview.setTextColor(Color.BLACK);
        AdTview.setPadding(10,10,10,10);
        tbrow0.addView(AdTview);

        TextView KrediTview = new TextView(this);
        KrediTview.setText(" Kredi ");
        KrediTview.setTextColor(Color.BLACK);
        KrediTview.setPadding(10,10,10,10);
        tbrow0.addView(KrediTview);

        TextView DersHarfiTview = new TextView(this);
        DersHarfiTview.setText(" Ders Harfi ");
        DersHarfiTview.setTextColor(Color.BLACK);
        DersHarfiTview.setPadding(10,10,10,10);
        tbrow0.addView(DersHarfiTview);

        stk.addView(tbrow0);

        final String[] kredi={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        final double[] krediNotu={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        final String[] harf={"AA","BA","BB","CB","CC","DC","DD","FD","FF"};
        final double[] harfNotu = {4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.5, 0.0};
        int maxLengthofEditText = 4;

        try
        {
            for (int k = 0; k < deger; k++)
            {
                TableRow tbrow = new TableRow(this);

                dersAdi = new EditText(this);
                dersAdi.setGravity(Gravity.CENTER);
                dersAdi.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLengthofEditText)});
                tbrow.addView(dersAdi);

                spKredi = new Spinner(this);
                spKredi.setGravity(Gravity.CENTER);
                spKredi.setId(1000+k);
                tbrow.addView(spKredi);

                ArrayAdapter<String>  krediadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, kredi);
                krediadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spKredi.setAdapter(krediadapter);

                spHarf = new Spinner(this);
                spHarf.setGravity(Gravity.CENTER);
                spHarf.setId(2000+k);
                tbrow.addView(spHarf);

                ArrayAdapter<String> harfadapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,harf);
                harfadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spHarf.setAdapter(harfadapter);

                stk.addView(tbrow);
            }

            hesapla=(Button)findViewById(R.id.hesapla);
            final Spinner spinnerKredi = new Spinner(this);
            final Spinner spinnerHarf=new Spinner(this);

			// burada ki hesapla butonu ile kullanıcının girdiği değer kadar bir for döngüsünde spinnerlara verdiğim id lere göre dönem ortalamasını hesaplatıyorum.
            hesapla.setOnClickListener(new View.OnClickListener() {
                double genelToplam =0;
                double araToplam = 0;
                double krediToplam=0;
                double krediToplama=0;
                double ortalama=0;
                @Override
                public void onClick(View view) {
                    for(int j=0;j<deger;j++)
                    {

                        int krediID;
                        int harfID;

                        spKredi=(Spinner)findViewById(1000+j);
                        krediID=spKredi.getSelectedItemPosition();
                        spHarf=(Spinner)findViewById(2000+j);
                        harfID=spHarf.getSelectedItemPosition();

                        if(krediID==0) // 1 kredidir
                        {
                            krediToplam=1;
                            if(harfID==0) // AA dır
                            {
                                araToplam=1*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=1*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=1*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=1*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=1*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=1*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=1*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=1*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=1*0;
                            }
                        }
                        if(krediID==1) // 2 kredidir
                        {
                            krediToplam=2;
                            if(harfID==0) // AA dır
                            {
                                araToplam=2*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=2*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=2*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=2*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=2*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=2*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=2*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=2*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=2*0;
                            }
                        }
                        if(krediID==2) // 3 kredidir
                        {
                            krediToplam=3;
                            if(harfID==0) // AA dır
                            {
                                araToplam=3*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=3*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=3*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=3*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=3*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=3*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=3*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=3*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=3*0;
                            }
                        }
                        if(krediID==3) // 4 kredidir
                        {
                            krediToplam=4;
                            if(harfID==0) // AA dır
                            {
                                araToplam=4*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=4*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=4*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=4*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=4*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=4*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=4*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=4*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=4*0;
                            }
                        }
                        if(krediID==4) // 5kredidir
                        {
                            krediToplam=5;
                            if(harfID==0) // AA dır
                            {
                                araToplam=5*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=5*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=5*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=5*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=5*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=5*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=5*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=5*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=5*0;
                            }
                        }
                        if(krediID==5) // 6kredidir
                        {
                            krediToplam=6;
                            if(harfID==0) // AA dır
                            {
                                araToplam=6*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=6*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=6*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=6*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=6*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=6*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=6*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=6*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=6*0;
                            }
                        }
                        if(krediID==6) // 7kredidir
                        {
                            krediToplam=7;
                            if(harfID==0) // AA dır
                            {
                                araToplam=7*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=7*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=7*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=7*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=7*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=7*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=7*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=7*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=7*0;
                            }
                        }
                        if(krediID==7) // 8kredidir
                        {
                            krediToplam=8;
                            if(harfID==0) // AA dır
                            {
                                araToplam=8*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=8*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=8*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=8*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=8*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=8*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=8*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=8*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=8*0;
                            }
                        }
                        if(krediID==8) // 9kredidir
                        {
                            krediToplam=9;
                            if(harfID==0) // AA dır
                            {
                                araToplam=9*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=9*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=9*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=9*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=9*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=9*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=9*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=9*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=9*0;
                            }
                        }
                        if(krediID==9) // 10kredidir
                        {
                            krediToplam=10;
                            if(harfID==0) // AA dır
                            {
                                araToplam=10*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=10*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=10*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=10*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=10*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=10*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=10*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=10*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=10*0;
                            }
                        }
                        if(krediID==10) // 11kredidir
                        {
                            krediToplam=11;
                            if(harfID==0) // AA dır
                            {
                                araToplam=11*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=11*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=11*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=11*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=11*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=11*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=11*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=11*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=11*0;
                            }
                        }
                        if(krediID==11) // 12kredidir
                        {
                            krediToplam=12;
                            if(harfID==0) // AA dır
                            {
                                araToplam=12*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=12*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=12*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=12*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=12*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=12*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=12*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=12*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=12*0;
                            }
                        }
                        if(krediID==12) // 13 kredi
                        {
                            krediToplam=13;
                            if(harfID==0) // AA dır
                            {
                                araToplam=13*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=13*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=13*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=13*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=13*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=13*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=13*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=13*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=13*0;
                            }
                        }
                        if(krediID==13) // 14kredidir
                        {
                            krediToplam=14;
                            if(harfID==0) // AA dır
                            {
                                araToplam=14*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=14*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=14*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=14*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=14*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=14*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=14*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=14*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=14*0;
                            }
                        }
                        if(krediID==14) // 15kredidir
                        {
                            krediToplam=15;
                            if(harfID==0) // AA dır
                            {
                                araToplam=15*4;
                            }
                            if(harfID==1) //ba dır
                            {
                                araToplam=15*3.5;
                            }
                            if(harfID==2)
                            {
                                araToplam=15*3;
                            }
                            if(harfID==3)
                            {
                                araToplam=15*2.5;
                            }
                            if(harfID==4)
                            {
                                araToplam=15*2;
                            }
                            if(harfID==5)
                            {
                                araToplam=15*1.5;
                            }
                            if(harfID==6)
                            {
                                araToplam=15*1;
                            }
                            if(harfID==7)
                            {
                                araToplam=15*0.5;
                            }
                            if(harfID==8)
                            {
                                araToplam=15*0;
                            }
                        }

                        genelToplam = genelToplam + araToplam;
                        krediToplama=krediToplama+krediToplam;

                        araToplam=0;
                        krediToplam=0;
                    }

                    ortalama=genelToplam/krediToplama;

                    alertbox(ortalama);

                    genelToplam=0;
                    krediToplama=0;
                    ortalama=0;

                }
            });
        }
        catch(Exception e)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.hataicon);
            final String title = "Hata";
            builder.setTitle(title);
            builder.setMessage(Html.fromHtml("<font color='#000000'> Beklenmeyen Bir Hata Oluştu.Uygulamayı Tekrar Başlatınız." ));
            builder.setCancelable(false);
            builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    onRestart();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    AlertDialog alertDialog = (AlertDialog) dialog;
                    ViewGroup viewGroup = (ViewGroup) alertDialog.getWindow()
                            .getDecorView();
                    TextView textView = findTextViewWithTitle(viewGroup, title);
                    if (textView != null) {
                        textView.setEllipsize(null);
                        textView.setMaxHeight((int) (50 * alertDialog.getContext().getResources().getDisplayMetrics().density));
                        textView.setMovementMethod(new ScrollingMovementMethod());
                    }
                }

                private TextView findTextViewWithTitle(ViewGroup viewGroup, String title) {
                    for (int i = 0, N = viewGroup.getChildCount(); i < N; i++) {
                        View child = viewGroup.getChildAt(i);
                        if (child instanceof TextView) {
                            TextView textView = (TextView) child;
                            if (textView.getText().equals(title)) {
                                return textView;
                            }
                        } else if (child instanceof ViewGroup) {
                            ViewGroup vGroup = (ViewGroup) child;
                            return findTextViewWithTitle(vGroup, title);
                        }
                    }
                    return null;
                }
            });
            alertDialog.show();
            Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
            pbutton.setTextColor(Color.BLACK);
        }

    }

    private void alertbox(double ortalama) {
        ortalama = Math.floor(ortalama*100)/100;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.cevap);
        final String title = "Cevap";
        final String Mesage="Ortalamanız: " + ortalama;
        builder.setTitle(title);
        builder.setMessage(Html.fromHtml("<font color='#000000'>" + Mesage));
        builder.setCancelable(false);
        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                AlertDialog alertDialog = (AlertDialog) dialog;
                ViewGroup viewGroup = (ViewGroup) alertDialog.getWindow()
                        .getDecorView();
                TextView textView = findTextViewWithTitle(viewGroup, title);
                if (textView != null) {
                    textView.setEllipsize(null);
                    textView.setMaxHeight((int) (50 * alertDialog.getContext().getResources().getDisplayMetrics().density));
                    textView.setMovementMethod(new ScrollingMovementMethod());
                }
            }

            private TextView findTextViewWithTitle(ViewGroup viewGroup, String title) {
                for (int i = 0, N = viewGroup.getChildCount(); i < N; i++) {
                    View child = viewGroup.getChildAt(i);
                    if (child instanceof TextView) {
                        TextView textView = (TextView) child;
                        if (textView.getText().equals(title)) {
                            return textView;
                        }
                    } else if (child instanceof ViewGroup) {
                        ViewGroup vGroup = (ViewGroup) child;
                        return findTextViewWithTitle(vGroup, title);
                    }
                }
                return null;
            }
        });
        alertDialog.show();
        Button pbutton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLACK);
    }

    private String sayiAl() {

        String sayi;
        bundle=getIntent().getExtras();
        sayi=bundle.getString(DonemOrtActivity.dersSayisii);

        return sayi;
    }

}
