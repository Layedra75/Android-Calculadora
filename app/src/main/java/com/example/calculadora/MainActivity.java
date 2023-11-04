package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView entradaTextView;
    private TextView resultadoTextView;
    private String entrada = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entradaTextView = findViewById(R.id.entradaTextView);
        resultadoTextView = findViewById(R.id.resultadoTextView);

        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button button5 = findViewById(R.id.button5);
        Button button6 = findViewById(R.id.button6);
        Button button7 = findViewById(R.id.button7);
        Button button8 = findViewById(R.id.button8);
        Button button9 = findViewById(R.id.button9);
        Button punto = findViewById(R.id.punto);
        Button igual = findViewById(R.id.igual);
        Button suma = findViewById(R.id.suma);
        Button resta = findViewById(R.id.resta);
        Button multiplicacion = findViewById(R.id.multiplicacion);
        Button division = findViewById(R.id.Dividir);
        Button porcentaje = findViewById(R.id.Porcentaje);
        Button parentesis = findViewById(R.id.Parentesis);
        Button buttonC = findViewById(R.id.buttonC);

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "0";
                entradaTextView.setText(entrada);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "1";
                entradaTextView.setText(entrada);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "2";
                entradaTextView.setText(entrada);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "3";
                entradaTextView.setText(entrada);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "4";
                entradaTextView.setText(entrada);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "5";
                entradaTextView.setText(entrada);
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "6";
                entradaTextView.setText(entrada);
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "7";
                entradaTextView.setText(entrada);
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "8";
                entradaTextView.setText(entrada);
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "9";
                entradaTextView.setText(entrada);
            }
        });

        punto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += ".";
                entradaTextView.setText(entrada);
            }
        });

        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "+";
                entradaTextView.setText(entrada);
            }
        });

        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "-";
                entradaTextView.setText(entrada);
            }
        });

        multiplicacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "X";
                entradaTextView.setText(entrada);
            }
        });

        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "/";
                entradaTextView.setText(entrada);
            }
        });

        porcentaje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "%";
                entradaTextView.setText(entrada);
            }
        });

        parentesis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada += "()";
                entradaTextView.setText(entrada);
            }
        });

        igual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    entrada = String.valueOf(eval(entrada));
                    resultadoTextView.setText(entrada);
                } catch (Exception e) {
                    resultadoTextView.setText("Error");
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada = "";
                entradaTextView.setText("0");
                resultadoTextView.setText("0");
            }
        });
    }

    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Carácter inesperado: " + (char) ch);
                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm();
                    else if (eat('-')) x -= parseTerm();
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('X')) x *= parseFactor();
                    else if (eat('/')) x /= parseFactor();
                    else if (eat('%')) x %= parseFactor();
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();
                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else {
                    throw new RuntimeException("Carácter inesperado: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor());
                return x;
            }
        }.parse();
    }
}
