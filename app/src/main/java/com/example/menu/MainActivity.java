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

    Cursor cursor;
    String NomeBD = "Agenda";
    String NomeTB= "Contatos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdNome = (EditText)findViewById(R.id.Ed_nome);
        EdContato = (EditText) findViewById(R.id.Ed_contato);
        BtnSalvar = (Button) findViewById(R.id.Btn_salvar);
        BtnBuscar= (Button)findViewById(R.id.Btn_buscar);
        BtnSair= (Button)findViewById(R.id.Btn_sair);



        Banco.CriarAbrirBD(NomeBD,this);
        Banco.AbrirTabela(NomeTB,this);
        Banco.FecharDB();
        Log.v("inic","Aplicativo Iniciado");
    }






    // Abrindo tela de colsulta
    public void AbrirTelaConsulta( View V){
        Intent telaConsulta = new Intent(this, tela_consulta.class);
        startActivity(telaConsulta);

    }

    public  void AdicionarDados( View V) {
         Banco.CriarAbrirBD(NomeBD,this);
         Banco.AdicionarDados(EdNome.getText().toString(),EdContato.getText().toString(),this);
        EdNome.setText(null);
        EdContato.setText(null);
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



}