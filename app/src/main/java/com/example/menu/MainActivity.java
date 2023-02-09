package com.example.menu;
// Commit teste
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.menu.databinding.ActivityMainBinding;

import java.util.logging.LogManager;


// Impots Banco de dados
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.*;


public class MainActivity extends AppCompatActivity {

    EditText EdNome;
    EditText EdContato;
    Button  BtnBuscar;
    Button BtnSalvar;
    Button BtnSair;

      // Banco de dados {OBJ}
    SQLiteDatabase BD=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdNome = (EditText)findViewById(R.id.Ed_nome);
        EdContato = (EditText) findViewById(R.id.Ed_contato);
        BtnSalvar = (Button) findViewById(R.id.Btn_salvar);
        BtnBuscar= (Button)findViewById(R.id.Btn_buscar);
        BtnSair= (Button)findViewById(R.id.Btn_sair);


        CriarAbrirBD();

        Log.v("inic","Aplicativo Iniciado");
    }

    // Criar banco de dados / Se não existir sera criado
    public  void  CriarAbrirBD(){
        try {
            BD=openOrCreateDatabase("BancoAgenda",MODE_PRIVATE,null);
        }catch ( Exception ex){
            msg("Erro em criar banco de dados.");
        }

        try {
            BD.execSQL("CREATE TABLE IF NOT EXISTS Contatos(id INTEGER PRIMARY KEY, nome TEXT, fone Text );");
        } catch ( Exception ex){
            msg("Erro ao criar tabela Contatos.");
        }finally {
            msg("Tabela Contatos criada com sucesso.");

        }
    }

    // Fechar Banco de Dados
   public  void fecharDB(){
        BD.close();
   }


    // Abrindo tela de colsulta
    public void AbrirTelaConsulta( View V){
        Intent telaConsulta = new Intent(this, tela_consulta.class);
        startActivity(telaConsulta);

    }


   //Sair do Aplicativo
    public  void sair(View V){
       /* Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
       */
    this.finish();

    }

    public  void  msg( String txt){
        AlertDialog.Builder msg = new AlertDialog.Builder(this);
        msg.setMessage(txt);
        msg.setNegativeButton("OK",null);
        msg.show();

    }

}