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

   EditText EdNome,EdContato;
   Button BTnAnterior,BTnProximo,BTnVoltar;

    // Banco de dados {OBJ}
    SQLiteDatabase BD=null;
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
    public  void  CriarAbrirBD(){
        try {
            BD=openOrCreateDatabase("BancoAgenda",MODE_PRIVATE,null);
        }catch ( Exception ex){
            msg("Erro em criar banco de dados.");
        }
    }

    // Fechar Banco de Dados
    public  void fecharDB(){
        BD.close();
    }

    // Buscar contatos
    public  void  BuscaDados(){
        CriarAbrirBD();

        cursor=BD.query("Contatos",
                new String[]{"nome","fone"},
                null,
                null,
                null,
                null,
                null,
                null);
        if (cursor.getCount() !=0){
            cursor.moveToFirst();
            MostraDados();
        }else{
            msg("Cadastro não encotrado");
        }
    }

    // Mosta dados
    public void MostraDados(){
        int nome = cursor.getColumnIndex("nome");
       int contato = cursor.getColumnIndex("fone");
        EdNome.setText(cursor.getString(nome));
        EdContato.setText(cursor.getString(contato));
    }


    // Poximo registro
    public void  ProximoRegistro( View V){
        try {
            cursor.moveToNext();
            MostraDados();
        }catch (Exception Ex){
            if (cursor.isAfterLast()){
                msg("Não exitem mais registros");
            }else{
                msg("Erro ao procurar registros");
            }
        }
    }

    // Registro  anterior
    public void  AnteriorRegistro( View V){
        try {
            cursor.moveToPrevious();
            MostraDados();
        }catch (Exception Ex){
            if (cursor.isBeforeFirst()){
                msg("Não exitem mais registros");
            }else{
                msg("Erro ao procurar registros");
            }
        }
    }

    // Fechar tela
    public  void fechaTela(View V){
       this.finish();
    }

    // Mensagens
    public  void  msg( String txt){
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setMessage(txt);
        msg.setNegativeButton("OK",null);
        msg.show();

    }
}