package ogulcan.ucarsu.org.kounothesaplama;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompatSideChannelService;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ogulc on 1.03.2017.
 */

public class DersOrtActivity  extends AppCompatActivity {

    private Toolbar toolbar;
    Bundle bundle;
    int degerAl;

    LinearLayout first;
    LinearLayout second;
    LinearLayout third;

    ScrollView sv1;
    ScrollView sv2;
    ScrollView sv3;

    Boolean cevap;
    Boolean cevapVPF;
    Boolean cevapVPSF;

    //vize+final
    Button hesapla;
    EditText vizeNot;
    EditText vizeEtki;
    EditText finalNot;
    EditText finalEtki;

    double vizeNotu;
    int vize_etki;
    double finalNotu;
    int final_etki;
    double sonuc;

    //vize+proje+sunum+final
    Button hesaplaVPSF;
    EditText vizeNotuVPSF;
    EditText vizeNotuEtkisiVPSF;
    EditText projeNotuVPSF;
    EditText projeNotuEtkisiVPSF;
    EditText sunumNotuVPSF;
    EditText sunumNotuEtkisiVPSF;
    EditText vizeEtkiVPSF;
    EditText finalNotuVPSF;
    EditText finalEtkiVPSF;

    double vizeNotu_VPSF;
    int vizeNotuEtki_VPSF;
    double projeNotu_VPSF;
    int projeNotuEtki_VPSF;
    double sunumNotu_VPSF;
    int sunumNotuEtki_VPSF;
    int vize_etki_VPSF;
    double finalNotu_VPSF;
    int finalNotuEtki_VPSF;
    double sonucVPSF;

    //vize + proje + final
    Button hesaplaVPF;
    EditText vizeNotuVPF;
    EditText vizeNotuEtkisiVPF;
    EditText projeNotuVPF;
    EditText projeNotuEtkisiVPF;
    EditText vizeEtkiVPF;
    EditText finalNotuVPF;
    EditText finalEtkiVPF;

    double vizeNotu_VPF;
    int vizeNotuEtki_VPF;
    double projeNotu_VPF;
    int projeNotuEtki_VPF;
    int vize_etki_VPF;
    double finalNotu_VPF;
    int finalNotuEtki_VPF;
    double sonucVPF;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ortders);

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Ders Ortalaması Hesaplama");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DersOrtActivity.this, DersSecim.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        sv1=(ScrollView)findViewById(R.id.sv1);// vize + final
        sv2=(ScrollView)findViewById(R.id.sv2);// vize + proje + final
        sv3=(ScrollView)findViewById(R.id.sv3);// vize + proje + sunum + final

        first = (LinearLayout)findViewById(R.id.FirstSecim); // vize + final
        second =(LinearLayout)findViewById(R.id.SecondSecim); // vize + proje + final
        third = (LinearLayout)findViewById(R.id.ThirdSecim); // vize + proje + sunum + final

        degerAl=sayiAl();  // DersSecimActivityden bundle ile aldığımız değer
        if(degerAl==1) // degerAl 1 ise vize + final ekranı visible olacaktır bu degerAlın değeri DersSecim Activityden gelen değer 
        {
            //linearlayout visible durumu
            first.setVisibility(View.VISIBLE);
            second.setVisibility(View.INVISIBLE);
            third.setVisibility(View.INVISIBLE);
            sv1.setVisibility(View.VISIBLE);
            sv2.setVisibility(View.INVISIBLE);
            sv3.setVisibility(View.INVISIBLE);

        }
        if(degerAl==2)  // + vize +proje + final durumunda ki ders içeriğinin layotu visible 
        {
            //linearlayotu durumu
            first.setVisibility(View.INVISIBLE);
            second.setVisibility(View.VISIBLE);
            third.setVisibility(View.INVISIBLE);
            sv1.setVisibility(View.INVISIBLE);
            sv2.setVisibility(View.VISIBLE);
            sv3.setVisibility(View.INVISIBLE);
        }
        if(degerAl==3)  // vize + proje + sunum + final in layotuu etkin 
        {
            //inearlayout durumu
            first.setVisibility(View.INVISIBLE);
            second.setVisibility(View.INVISIBLE);
            third.setVisibility(View.VISIBLE);
            sv1.setVisibility(View.INVISIBLE);
            sv2.setVisibility(View.INVISIBLE);
            sv3.setVisibility(View.VISIBLE);
        }

        //Vize + Final
        hesapla=(Button)findViewById(R.id.btn_hesapla);
        vizeNot=(EditText)findViewById(R.id.vize);
        vizeEtki=(EditText)findViewById(R.id.vize_etki);
        finalNot=(EditText)findViewById(R.id.finalNot);
        finalEtki=(EditText)findViewById(R.id.final_etki);

        //Vize(vize+ Proje + sunum) + Final   VPSF  = Vize + proje + sunum + final in kısaltılmışı
        hesaplaVPSF=(Button)findViewById(R.id.btn_hesaplaVPSF);
        vizeNotuVPSF = (EditText)findViewById(R.id.vizeNotuVPSF);
        vizeNotuEtkisiVPSF = (EditText)findViewById(R.id.vizeNotuetkisiVPSF);
        projeNotuVPSF = (EditText)findViewById(R.id.projeNotuVPSF);
        projeNotuEtkisiVPSF = (EditText)findViewById(R.id.projeNotuEtkiVPSF);
        sunumNotuVPSF = (EditText)findViewById(R.id.sunumNotuVPSF);
        sunumNotuEtkisiVPSF = (EditText)findViewById(R.id.sunumNotuEtkiVPSF);
        vizeEtkiVPSF =(EditText)findViewById(R.id.vize_etkiVPSF);
        finalNotuVPSF= (EditText)findViewById(R.id.finalNotVPSF);
        finalEtkiVPSF = (EditText)findViewById(R.id.final_etkiVPSF);

        //Vize(vize + proje) + final  VPF = Vize + proje + Final in kısaltılmışı 
        hesaplaVPF = (Button) findViewById(R.id.btn_hesaplaVPF);
        vizeNotuVPF = (EditText)findViewById(R.id.vizeNotuVPF);
        vizeNotuEtkisiVPF = (EditText)findViewById(R.id.vizeNotuetkisiVPF);
        projeNotuVPF = (EditText)findViewById(R.id.projeNotuVPF);
        projeNotuEtkisiVPF = (EditText)findViewById(R.id.projeNotuEtkiVPF);
        vizeEtkiVPF = (EditText)findViewById(R.id.vize_etkiVPF);
        finalNotuVPF = (EditText)findViewById(R.id.finalNotVPF);
        finalEtkiVPF = (EditText)findViewById(R.id.final_etkiVPF);

        //* -------------------   */

        try
        {
            hesapla.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cevap=degerlerDolumu(vizeNot,vizeEtki,finalNot,finalEtki);

                     if(cevap==true)
                      {
                                vizeNotu=Double.parseDouble(vizeNot.getText().toString());
                                vize_etki=Integer.parseInt(vizeEtki.getText().toString());
                                finalNotu=Double.parseDouble(finalNot.getText().toString());
                                final_etki=Integer.parseInt(finalEtki.getText().toString());

                                if(vizeNotu>100 || finalNotu>100)
                                {
                                    alertbox1("Vize ya da final notunuz 100'den büyük olamaz.");
                                }
                                else
                                {
                                    if((vize_etki + final_etki)!=100  )
                                    {
                                        alertbox1("Vize yüzdesi ve final yüzdesi toplamı 100 olmak zorundadır.");
                                    }
                                    else
                                    {
                                        if(vize_etki==0)
                                        {
                                            alertbox1("Yarı yıl içi etkisi sıfır olamaz.");
                                        }
                                        else if(final_etki==0)
                                        {
                                            alertbox1("Final Etkisi sıfır olamaz!");
                                        }
                                        else
                                        {
                                            sonuc=(((vizeNotu*vize_etki)/100) + ((finalNotu*final_etki)/100));
                                            alertbox(sonuc);
                                        }
                                    }
                                }
                            }
                            else
                            {
                                // txt_sonuc.setText("Lütfen boş alan bırakmayınız.");
                                alertbox1("Lütfen boş alan bırakmayınız.");
                            }
                      }
            });

            hesaplaVPSF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cevapVPSF=degerlerDolumuVPSF(vizeNotuVPSF,vizeNotuEtkisiVPSF,projeNotuVPSF,projeNotuEtkisiVPSF,sunumNotuVPSF,sunumNotuEtkisiVPSF,vizeEtkiVPSF,finalNotuVPSF,finalEtkiVPSF);

                    if(cevapVPSF==true)
                    {
                        vizeNotu_VPSF=Double.parseDouble(vizeNotuVPSF.getText().toString());
                        vizeNotuEtki_VPSF = Integer.parseInt(vizeNotuEtkisiVPSF.getText().toString());
                        projeNotu_VPSF = Double.parseDouble(projeNotuVPSF.getText().toString());
                        projeNotuEtki_VPSF = Integer.parseInt(projeNotuEtkisiVPSF.getText().toString());
                        sunumNotu_VPSF = Double.parseDouble(sunumNotuVPSF.getText().toString());
                        sunumNotuEtki_VPSF = Integer.parseInt(sunumNotuEtkisiVPSF.getText().toString());
                        vize_etki_VPSF =Integer.parseInt(vizeEtkiVPSF.getText().toString()) ;
                        finalNotu_VPSF = Double.parseDouble(finalNotuVPSF.getText().toString());
                        finalNotuEtki_VPSF = Integer.parseInt(finalEtkiVPSF.getText().toString());
                        //double sonucVPSF;

                        if (vizeNotu_VPSF>100 ||projeNotu_VPSF>100 ||sunumNotu_VPSF>100 || finalNotu_VPSF>100)
                        {
                            alertbox1("Girdiğiniz Notlar 100'den Büyük Olamaz.");
                        }
                        else
                        {
                            if(((vizeNotuEtki_VPSF + projeNotuEtki_VPSF + sunumNotuEtki_VPSF) !=100) || vizeNotuEtki_VPSF==0 || projeNotuEtki_VPSF==0 || sunumNotuEtki_VPSF==0)
                            {
                                alertbox1("Lütfen Vize Notu Etkisi, Proje Notu Etkisi ve Sunum Notu Etkisini Düzgün Doldurunuz");
                            }
                            else
                            {
                                if((vize_etki_VPSF ==0 ||finalNotuEtki_VPSF==0) || (vize_etki_VPSF + finalNotuEtki_VPSF !=100))
                                {
                                    alertbox1("Yarıyıl İçi ve Yarıyıl Sonu Not Etkilerini Düzgün Doldurunuz.");
                                }
                                else
                                {
                                    sonucVPSF = (((((vizeNotu_VPSF * vizeNotuEtki_VPSF)/100) + ((projeNotu_VPSF*projeNotuEtki_VPSF)/100) + ((sunumNotu_VPSF*sunumNotuEtki_VPSF)/100)) * vize_etki_VPSF)/100) + ((finalNotu_VPSF * finalNotuEtki_VPSF)/100);
                                    //Toast.makeText(getApplicationContext(),"Cevap: " + sonucVPSF, Toast.LENGTH_LONG).show();
                                    alertbox(sonucVPSF);
                                }
                            }
                        }
                    }
                    else
                    {
                        alertbox1("Lütfen Boş Alanları Doldurunuz.");
                    }
                }
            });

            hesaplaVPF.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    cevapVPF = degerlerDolumuVPF(vizeNotuVPF,vizeNotuEtkisiVPF,projeNotuVPF,projeNotuEtkisiVPF,vizeEtkiVPF,finalNotuVPF,finalEtkiVPF);

                    if(cevapVPF == true)
                    {
                         vizeNotu_VPF = Double.parseDouble(vizeNotuVPF.getText().toString());
                         vizeNotuEtki_VPF = Integer.parseInt(vizeNotuEtkisiVPF.getText().toString());
                         projeNotu_VPF = Double.parseDouble(projeNotuVPF.getText().toString());
                         projeNotuEtki_VPF = Integer.parseInt(projeNotuEtkisiVPF.getText().toString());
                         vize_etki_VPF = Integer.parseInt(vizeEtkiVPF.getText().toString());
                         finalNotu_VPF = Double.parseDouble(finalNotuVPF.getText().toString());
                         finalNotuEtki_VPF = Integer.parseInt(finalEtkiVPF.getText().toString());
                         //double sonucVPF;

                        if(vizeNotu_VPF> 100 ||projeNotu_VPF>100 || finalNotu_VPF>100)
                        {
                            alertbox1("Girdiğiniz Notlar 100'den Büyük Olamaz.");
                        }
                        else
                        {
                            if(((vizeNotuEtki_VPF + projeNotuEtki_VPF) !=100) || vizeNotuEtki_VPF==0 || projeNotuEtki_VPF==0)
                            {
                                alertbox1("Vize Notu Etkisi ve Proje Notu Etkisi Toplamı 100 Olmalıdır");
                            }
                            else
                            {
                                if((vize_etki_VPF ==0 ||finalNotuEtki_VPF==0) || (vize_etki_VPF + finalNotuEtki_VPF !=100))
                                {
                                    alertbox1("Yarıyıl İçi ve Yarıyıl Sonu Not Etkilerini Toplamı 100 Olmalıdır.");
                                }
                                else
                                {
                                    sonucVPF = ((((((vizeNotu_VPF * vizeNotuEtki_VPF)/100) + ((projeNotu_VPF*projeNotuEtki_VPF)/100)) * vize_etki_VPF)/100) + ((finalNotu_VPF * finalNotuEtki_VPF)/100) );
                                    //Toast.makeText(getApplicationContext(),"Cevap: " + sonucVPF, Toast.LENGTH_LONG).show();
                                    alertbox(sonucVPF);
                                }
                            }
                        }
                    }
                    else
                    {
                        alertbox1("Lütfen Boş Alanları Doldurunuz.");
                    }
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Beklenmeyen Bir Hata Oluştu", Toast.LENGTH_LONG).show();
            onRestart();
        }
    }

    private int sayiAl() {
        int sayi;
        bundle=getIntent().getExtras();
        sayi=bundle.getInt(DersSecim.Deger);
        return sayi;
    }

    private void alertbox(double sonuc) {

        sonuc = Math.floor(sonuc*100)/100;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.cevap);
        final String title = "Ortalama";
        final String Mesage="Ders Ortalamanız: " + sonuc;
        builder.setTitle(title);
        builder.setMessage(Html.fromHtml("<font color='#000000'>" + Mesage));
        builder.setCancelable(false);
        builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if(degerAl==1)
                {
                    vizeNot.getText().clear();
                    vizeEtki.getText().clear();
                    finalNot.getText().clear();
                    finalEtki.getText().clear();
                }
                if(degerAl==2)
                {
                    vizeNotuVPF.getText().clear();
                    vizeNotuEtkisiVPF.getText().clear();
                    projeNotuVPF.getText().clear();
                    projeNotuEtkisiVPF.getText().clear();
                    vizeEtkiVPF.getText().clear();
                    finalNotuVPF.getText().clear();
                    finalEtkiVPF.getText().clear();
                }
                if(degerAl==3)
                {
                    vizeNotuVPSF.getText().clear();
                    vizeNotuEtkisiVPSF.getText().clear();
                    projeNotuVPSF.getText().clear();
                    projeNotuEtkisiVPSF.getText().clear();
                    sunumNotuVPSF.getText().clear();
                    sunumNotuEtkisiVPSF.getText().clear();
                    vizeEtkiVPSF.getText().clear();
                    finalNotuVPSF.getText().clear();
                    finalEtkiVPSF.getText().clear();
                }
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

    private void alertbox1(String warning) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.hataicon);
        final String title = "Uyarı";
        builder.setTitle(title);
        builder.setMessage(Html.fromHtml("<font color='#000000'>" + warning));
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

	// Burada edittextlerin dolu olup olmadığını kontrol ettiriyorum ona göre kullanıcıya uyarı verdirecem.
    private Boolean degerlerDolumuVPF(EditText vizeNotuVPF, EditText vizeNotuEtkisiVPF, EditText projeNotuVPF, EditText projeNotuEtkisiVPF, EditText vizeEtkiVPF, EditText finalNotuVPF, EditText finalEtkiVPF) {

        String svizeNotuVPF;
        String svizeNotuEtkisiVPF;
        String sprojeNotuVPF;
        String sprojeNotuEtkisiVPF;
        String svizeEtkiVPF;
        String sfinalNotuVPF;
        String sfinalEtkiVPF;

        svizeNotuVPF = vizeNotuVPF.getText().toString();
        svizeNotuEtkisiVPF = vizeNotuEtkisiVPF.getText().toString();
        sprojeNotuVPF =projeNotuVPF.getText().toString();
        sprojeNotuEtkisiVPF = projeNotuEtkisiVPF.getText().toString();
        svizeEtkiVPF = vizeEtkiVPF.getText().toString();
        sfinalNotuVPF = finalNotuVPF.getText().toString();
        sfinalEtkiVPF = finalEtkiVPF.getText().toString();

        if(svizeNotuVPF.matches("") || svizeNotuEtkisiVPF.matches("") || sprojeNotuVPF.matches("") || sprojeNotuEtkisiVPF.matches("") || svizeEtkiVPF.matches("") || sfinalNotuVPF.matches("") || sfinalEtkiVPF.matches(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean degerlerDolumu(EditText vizeNot, EditText vizeEtki,EditText  finalNot,EditText  finalEtki) {

        String notVize;
        String etkiVize;
        String notFinal;
        String etkiFinal;

        notVize=vizeNot.getText().toString();
        etkiVize=vizeEtki.getText().toString();
        notFinal=finalNot.getText().toString();
        etkiFinal=finalEtki.getText().toString();

        if(notVize.matches("") || etkiVize.matches("") || notFinal.matches("") || etkiFinal.matches(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private Boolean degerlerDolumuVPSF(EditText vizeNotuVPSF, EditText vizeNotuEtkisiVPSF, EditText projeNotuVPSF, EditText projeNotuEtkisiVPSF, EditText sunumNotuVPSF, EditText sunumNotuEtkisiVPSF, EditText vizeEtkiVPSF, EditText finalNotuVPSF, EditText finalEtkiVPSF) {

        String SvizeNotuVPSF;
        String SvizeNotuEtkisiVPSF;
        String SprojeNotuVPSF;
        String SprojeNotuEtkisiVPSF;
        String SsunumNotuVPSF;
        String SsunumNotuEtkisiVPSF;
        String SvizeEtkiVPSF;
        String SfinalNotuVPSF;
        String SfinalEtkiVPSF;

        SvizeNotuVPSF =vizeNotuVPSF.getText().toString();
        SvizeNotuEtkisiVPSF=vizeNotuEtkisiVPSF.getText().toString();
        SprojeNotuVPSF=projeNotuVPSF.getText().toString();
        SprojeNotuEtkisiVPSF=projeNotuEtkisiVPSF.getText().toString();
        SsunumNotuVPSF=sunumNotuVPSF.getText().toString();
        SsunumNotuEtkisiVPSF=sunumNotuEtkisiVPSF.getText().toString();
        SvizeEtkiVPSF=vizeEtkiVPSF.getText().toString();
        SfinalNotuVPSF=finalNotuVPSF.getText().toString();
        SfinalEtkiVPSF=finalEtkiVPSF.getText().toString();

        if(SvizeNotuVPSF.matches("") ||SvizeNotuEtkisiVPSF.matches("") || SprojeNotuVPSF.matches("") || SprojeNotuEtkisiVPSF.matches("") || SsunumNotuVPSF.matches("") || SsunumNotuEtkisiVPSF.matches("") || SvizeEtkiVPSF.matches("") || SfinalNotuVPSF.matches("") || SfinalEtkiVPSF.matches(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
