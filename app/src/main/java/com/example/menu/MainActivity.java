package com.example.menu;
// Commit teste
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;
import com.example.menu.databinding.ActivityMainBinding;

import java.util.logging.LogManager;

public class MainActivity extends AppCompatActivity {

    EditText EdNome;
    EditText EdContato;
    Button  BtnBuscar;
    Button BtnSalvar;
    Button BtnSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EdNome = (EditText)findViewById(R.id.Ed_nome);
        EdContato = (EditText) findViewById(R.id.Ed_contato);
        BtnSalvar = (Button) findViewById(R.id.Btn_salvar);
        BtnBuscar= (Button)findViewById(R.id.Btn_buscar);
        BtnSair= (Button)findViewById(R.id.Btn_sair);




        Log.v("inic","Aplicativo Iniciado");
    }



}