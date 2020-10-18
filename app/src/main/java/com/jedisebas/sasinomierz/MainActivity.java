package com.jedisebas.sasinomierz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import java.math.BigDecimal;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    EditText liczbaEt;
    TextView kilosasinTv, sasinTv, milisasinTv, mikrosasinTv, nanosasinTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        liczbaEt = (EditText) findViewById(R.id.liczbaEt);
        kilosasinTv = (TextView) findViewById(R.id.kilosasinTv);
        sasinTv = (TextView) findViewById(R.id.sasinTv);
        milisasinTv = (TextView) findViewById(R.id.milisasinTv);
        mikrosasinTv = (TextView) findViewById(R.id.mikrosasinTv);
        nanosasinTv = (TextView) findViewById(R.id.nanosasinTv);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                liczbaEt.setText("");
            }
        });
    }

    public void obl(View view) {
        String ls;
        double l=0, ks, s, ms, us, ns;

        try {
            ls = liczbaEt.getText().toString().trim();
            l = Double.parseDouble(ls);
        }catch (NumberFormatException e){liczbaEt.setText("0");}
        finally {

            BigDecimal sasin = BigDecimal.valueOf(l/70000000);
            BigDecimal kilosasin = new BigDecimal(1000);
            BigDecimal milisasin = BigDecimal.valueOf(l/70000);
            BigDecimal mikrosasin = BigDecimal.valueOf(l/70);
            BigDecimal nanosasin = BigDecimal.valueOf(l * 100/7);

            kilosasinTv.setText(sasin.divide(kilosasin).toPlainString() + " kilosasin");
            sasinTv.setText(sasin.toPlainString() + " sasin");
            milisasinTv.setText(milisasin.toPlainString() + " milisasin");
            mikrosasinTv.setText(mikrosasin.toPlainString() + " mikrosasin");
            nanosasinTv.setText(nanosasin.toPlainString() + " nanosasin");
        }
    }
}