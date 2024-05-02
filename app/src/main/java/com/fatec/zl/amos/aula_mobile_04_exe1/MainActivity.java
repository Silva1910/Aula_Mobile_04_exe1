package com.fatec.zl.amos.aula_mobile_04_exe1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.Calendar;
public class MainActivity extends AppCompatActivity {
    private EditText etDia;
    private EditText etMes;
    private EditText etAno;

    private TextView tvRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etDia = findViewById(R.id.etDia);
        etDia.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etMes = findViewById(R.id.etMes);
        etMes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        etAno = findViewById(R.id.etAno);
        etAno.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tvRes = findViewById(R.id.tvRes);
        tvRes.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button  btnCalc = findViewById(R.id.btnCalc);
        btnCalc.setOnClickListener(op -> calc());

    }
    private void calc(){


        // coletando dados do usuario
        int dia = Integer.parseInt(etDia.getText().toString());
        int mes = Integer.parseInt(etMes.getText().toString());
        int ano = Integer.parseInt(etAno.getText().toString());

        // recebendo o dia mes e ano atual
        Calendar hoje = Calendar.getInstance();
        int anoAtual = hoje.get(Calendar.YEAR);
        int mesAtual = hoje.get(Calendar.MONTH) + 1;
        int diaAtual = hoje.get(Calendar.DAY_OF_MONTH) -1;

        // comecando calculo base
        int idadeAnos = anoAtual - ano;
        int idadeMeses = mesAtual - mes;
        int idadeDias = diaAtual - dia;

        // vendo se ja ocorreu o aniversario este ano
        if (idadeMeses < 0 || (idadeMeses == 0 && idadeDias < 0)) {
            idadeAnos--;
            idadeMeses = 12 - Math.abs(idadeMeses);
        }

        // vendo se ja aconteceu esse mes
        if (idadeDias < 0) {
            int mesTemp = mes == 12 ? 1 : mes + 1;
            idadeDias = diasNoMes(mesTemp, anoAtual) - dia + diaAtual;
            idadeMeses--;
        }


        tvRes.setText("Idade: " + idadeAnos + " anos, " + idadeMeses + " meses e " + idadeDias + " dias.");


        etDia.setText("");
        etMes.setText("");
        etAno.setText("");
    }

    private int diasNoMes(int mes, int ano) {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return 31;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        } else {
            return seBissexto(ano) ? 29 : 28;
        }
    }
    private boolean seBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }


}