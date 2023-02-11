package com.example.menu;

import android.app.Activity;

import androidx.appcompat.app.AlertDialog;

//  Clase usada para as mensagens
public class Msg{
    public static void  mensagem(String txt, Activity Act){
        AlertDialog.Builder msg = new AlertDialog.Builder(Act);
        msg.setMessage(txt);
        msg.setNegativeButton("OK",null);
        msg.show();

    }
}
