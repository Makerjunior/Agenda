package com.example.menu;
// Commit teste
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


// Impots Banco de dados
import android.database.sqlite.SQLiteDatabase;


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
        AbrirTabela();
        fecharDB();
        Log.v("inic","Aplicativo Iniciado");
    }

    // Criar banco de dados / Se não existir sera criado
    public  void  CriarAbrirBD(){
        try {
            BD=openOrCreateDatabase("BancoAgenda",MODE_PRIVATE,null);
        }catch ( Exception ex){
            Msg.mensagem("Erro em criar banco de dados.",this);
        }
    }

    public  void AbrirTabela() {
        try {
            BD.execSQL("CREATE TABLE IF NOT EXISTS Contatos(id INTEGER PRIMARY KEY, nome TEXT, fone Text );");
        } catch (Exception ex) {
            Msg.mensagem("Erro ao criar tabela Contatos.",this);
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

    public  void AdicionarDados( View V) {
        String StNome, StContact;
        StNome = EdNome.getText().toString();
        StContact = EdContato.getText().toString();
        if (StNome == " " || StContact == " ") {
            Msg.mensagem("Campos não podem ser vazios",this);
            return;
        }

        CriarAbrirBD();
        try {
            BD.execSQL("INSERT INTO COntatos (nome,fone) VALUES ('" + StNome + "','" + StContact + "')");
        } catch (Exception Ex) {
            Msg.mensagem("Erro ao adicionar contato",this);
        } finally {
            Msg.mensagem("COntato adicionado com susseço",this);
        }
      fecharDB();
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

 public  void ApagarTabela( String StTabela){
        try {
            BD.execSQL("DELETE FROM '"+StTabela+"'" );
        }catch (Exception Ex){
            Msg.mensagem("Erro ao apagar tabela",this);
        }

 }

}