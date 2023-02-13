package com.example.menu.banco;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.ContextWrapper;

import com.example.menu.Msg;

//import android.content.Context.MODE_PRIVATE;
public  class Banco extends DebugBanco {
    static SQLiteDatabase BD=null;
    static Cursor cursor;
    static String NomeBanco;
    static  String Tabela;


     // Criar banco de dados / Se não existir sera criado
    public static void  CriarAbrirBD(String NomeBD,Activity Atc){
        NomeBanco = NomeBD;
         ContextWrapper ContBD=new ContextWrapper(Atc);
         try {
             BD=ContBD.openOrCreateDatabase(NomeBD,MODE_PRIVATE,null);
         }catch ( Exception ex){
             Msg.mensagem("Erro em criar banco de dados.",Atc);
         }
     }

     //Abrir Tabela
     public static void AbrirTabela(String TabelaBD, Activity Act) {
        Tabela =TabelaBD;
         try {
             BD.execSQL("CREATE TABLE IF NOT EXISTS '"+TabelaBD+"'(id INTEGER PRIMARY KEY, nome TEXT, fone Text );");
         } catch (Exception ex) {
             Msg.mensagem("Erro ao criar tabela '"+TabelaBD+"'.",Act);
         }
     }

     // Fechar Banco de Dados
     public static void FecharDB(){
         BD.close();
     }

     // Adicionar dados
     public  static void AdicionarDados(  String nome, String contato, Activity Atc) {
         if ( nome.isEmpty() || contato.isEmpty()){
             Msg.mensagem("Preencha os campos coretamente",Atc);
             return ;
         }

        CriarAbrirBD(NomeBanco,Atc);
        try {
            BD.execSQL("INSERT INTO COntatos (nome,fone) VALUES ('" + nome + "','" + contato + "')");
        } catch (Exception Ex) {
            Msg.mensagem("Erro ao adicionar contato",Atc);
        } finally {
            Msg.mensagem("Contato adicionado com sucesso",Atc);
        }
       FecharDB();
    }

     //Exluir tabela
     public static void ApagarTabela( String Tabela){
        BD.execSQL("DELETE FROM '"+Tabela+"'");
    }

    // Buscar contatos
    public static Cursor BuscaDados(Activity Atc) {
        Banco.CriarAbrirBD(NomeBanco,Atc);

        cursor = BD.query(Tabela,
                new String[]{"nome", "fone"},
                null,
                null,
                null,
                null,
                null,
                null);

        cursor.moveToFirst();
          return cursor;
    }



}
