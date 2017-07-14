package ogulcan.ucarsu.org.kounothesaplama;

import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by ogulc on 2.03.2017.
 */

public class DonemOrtActivity extends AppCompatActivity {


    private Toolbar toolbar;
    EditText dersSayisi;
    Button ileri;
    Boolean edittextDolumu;
    Bundle bundle=new Bundle();
    public static String dersSayisii;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ortdonem);

        toolbar = (Toolbar) findViewById(R.id.toolbar_search);
        toolbar.setTitle("Dönem Ortalaması Hesaplama");
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setNavigationIcon(R.drawable.ic_back_white);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(DonemOrtActivity.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });

        ileri=(Button)findViewById(R.id.btn_ileri);
        dersSayisi=(EditText) findViewById(R.id.edit_ders);

        try
        {
            ileri.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    edittextDolumu=edittextFunction(dersSayisi);

                    if(edittextDolumu==true)
                    {
                        String _dersSayisi;
                        double ds;

                        _dersSayisi=dersSayisi.getText().toString();
                        ds=Double.parseDouble(_dersSayisi);
                        if(ds==0)
                        {
                          Toast.makeText(getApplicationContext(),"Ders Sayısı Sıfır Olamaz.",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            bundle.putString(dersSayisii,_dersSayisi);

                            Intent intent = new Intent();
                            intent.putExtras(bundle);
                            intent.setClass(getApplicationContext(),DonemOrtHesapActivity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Lütfen Dönemlik Ders Sayınızı Giriniz.",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
        catch(Exception e)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.hataicon);
            final String title = "Hata";
            final String Mesage="Beklenmeyen Bir Hata Oluştu, Lütfen Tekrar Deneyiniz. ";
            builder.setTitle(title);
            builder.setMessage(Html.fromHtml("<font color='#000000'>" + Mesage));
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

    private Boolean edittextFunction(EditText dersSayisi) {
        String ders;
        ders=dersSayisi.getText().toString();
        if(ders.matches(""))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
