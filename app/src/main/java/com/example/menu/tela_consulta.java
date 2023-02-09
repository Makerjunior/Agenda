package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tela_consulta extends AppCompatActivity {

   EditText EdNome,EdContato;
   Button BTnAnterior,BTnProximo,BTnVoltar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);
        EdNome = (EditText) findViewById(R.id.idNomeTConsulta);
        EdContato = (EditText) findViewById(R.id.idNomeTConsulta);
        BTnProximo = (Button) findViewById(R.id.BtnTProximo);
        BTnAnterior = (Button) findViewById(R.id.Btn_TAnterior);
        BTnVoltar = (Button) findViewById(R.id.BtnT_Voltar);

    }

    public  void fechaTela(View V){
       this.finish();
    }


}