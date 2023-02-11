package com.example.menu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class tela_consulta extends AppCompatActivity {

    EditText EdNome, EdContato;
    Button BTnAnterior, BTnProximo, BTnVoltar;

    // Banco de dados {OBJ}
    SQLiteDatabase BD = null;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_consulta);
        EdNome = (EditText) findViewById(R.id.idNomeTConsulta);
        EdContato = (EditText) findViewById(R.id.idContatoTConsulta);
        BTnProximo = (Button) findViewById(R.id.BtnTProximo);
        BTnAnterior = (Button) findViewById(R.id.Btn_TAnterior);
        BTnVoltar = (Button) findViewById(R.id.BtnT_Voltar);

        BuscaDados();
    }

    // Abrir Banco
    public void CriarAbrirBD() {
        try {
            BD = openOrCreateDatabase("BancoAgenda", MODE_PRIVATE, null);
        } catch (Exception ex) {
            Msg.mensagem("Erro em criar banco de dados.", this);
        }
    }

    // Fechar Banco de Dados
    public void fecharDB() {
        BD.close();
    }

    // Buscar contatos
    public void BuscaDados() {
        CriarAbrirBD();

        cursor = BD.query("Contatos",
                new String[]{"nome", "fone"},
                null,
                null,
                null,
                null,
                null,
                null);
        if (cursor.getCount() != 0) {
            cursor.moveToFirst();
            MostraDados();
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
