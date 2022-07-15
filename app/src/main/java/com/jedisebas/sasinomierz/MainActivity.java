package com.jedisebas.sasinomierz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText numberEt = findViewById(R.id.numberEt);
        final Button calculateBtn = findViewById(R.id.calculateBtn);
        final FloatingActionButton fab = findViewById(R.id.fab);
        final TextView[] textViews = new TextView[5];

        final int[] values = new int[]{
                R.string.kilo_sasin, R.string.sasin, R.string.milli_sasin,
                R.string.micro_sasin, R.string.nano_sasin
        };

        textViews[0] = findViewById(R.id.kiloSasinTv);
        textViews[1] = findViewById(R.id.sasinTv);
        textViews[2] = findViewById(R.id.milliSasinTv);
        textViews[3] = findViewById(R.id.microSasinTv);
        textViews[4] = findViewById(R.id.nanoSasinTv);

        fab.setOnClickListener(view -> numberEt.setText(""));

        setText(textViews, values, new BigDecimal[]{BigDecimal.valueOf(0), BigDecimal.valueOf(0),
                BigDecimal.valueOf(0), BigDecimal.valueOf(0), BigDecimal.valueOf(0)});

        calculateBtn.setOnClickListener(view -> {
            final String numberS = numberEt.getText().toString().trim();

            if (numberS.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty), Toast.LENGTH_SHORT).show();
            } else {

                final BigDecimal[] bigDecimals = new BigDecimal[5];

                bigDecimals[1] = divide(new BigDecimal(numberS), BigDecimal.valueOf(70_000_000));
                bigDecimals[0] = divide(bigDecimals[1]);
                bigDecimals[2] = multiplyByThousand(bigDecimals[1]);
                bigDecimals[3] = multiplyByThousand(bigDecimals[2]);
                bigDecimals[4] = multiplyByThousand(bigDecimals[3]);

                setText(textViews, values, bigDecimals);
            }
        });
    }

    private BigDecimal multiplyByThousand(final BigDecimal multiplier) {
        if (multiplier == null) {
            return new BigDecimal(0);
        } else {
            return multiplier.multiply(BigDecimal.valueOf(1000));
        }
    }

    private BigDecimal divide(final BigDecimal divider, final BigDecimal dividedBy) {
        if (divider == null || dividedBy == null || dividedBy.equals(new BigDecimal(0))) {
            return new BigDecimal(0);
        } else {
            return divider.divide(dividedBy, 10, RoundingMode.HALF_UP);
        }
    }

    private BigDecimal divide(final BigDecimal divider) {
        return divide(divider, BigDecimal.valueOf(1000));
    }

    private void setText(final TextView[] textViews, final int[] values, final BigDecimal[] bigDecimals) {
        for (int i = 0; i < textViews.length; i++) {
            textViews[i].setText(getString(values[i], bigDecimals[i].toString()));
        }
    }
}