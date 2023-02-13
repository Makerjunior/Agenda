package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.menu.banco.Banco;

import java.util.ArrayList;

public class tela_consulta extends AppCompatActivity {

    EditText EdNome, EdContato;
    Button BTnAnterior, BTnProximo, BTnVoltar;

    Cursor cursor;

ArrayList<String> Nomes = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);
        EdNome = (EditText) findViewById(R.id.idNomeTConsulta);
        EdContato = (EditText) findViewById(R.id.idContatoTConsulta);
        BTnProximo = (Button) findViewById(R.id.BtnTProximo);
        BTnAnterior = (Button) findViewById(R.id.Btn_TAnterior);
        BTnVoltar = (Button) findViewById(R.id.BtnT_Voltar);

       cursor= Banco.BuscaDados(this);
        if (cursor.getCount() != 0) {
            MostraDados();

             // Debug
            for( String n: Banco.getCamposColuna(cursor,"nome")){
                Log.v("Nomes",n);
            }


        } else {
            Msg.mensagem("Cadastro não encotrado", this);
        }



    }


    // Mosta dados
    public void MostraDados() {
        int nome = cursor.getColumnIndex("nome");
        int contato = cursor.getColumnIndex("fone");
        EdNome.setText(cursor.getString(nome));
        EdContato.setText(cursor.getString(contato));
    }

    // Poximo registro
    public void ProximoRegistro(View V) {
        try {
            cursor.moveToNext();
            MostraDados();
        } catch (Exception Ex) {
            if (cursor.isAfterLast()) {
                Msg.mensagem("Não exitem mais registros", this);
            } else {
                Msg.mensagem("Erro ao procurar registros", this);
            }
        }
    }

    // Registro  anterior
    public void AnteriorRegistro(View V) {
        try {
            cursor.moveToPrevious();
            MostraDados();
        } catch (Exception Ex) {
            if (cursor.isBeforeFirst()) {
                Msg.mensagem("Não exitem mais registros", this);
            } else {
                Msg.mensagem("Erro ao procurar registros", this);
            }
        }
    }

    // Fechar tela
    public void fechaTela(View V) {
        this.finish();
    }


}
