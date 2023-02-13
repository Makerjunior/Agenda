package com.example.menu;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class DebugBanco {

    public  static List<String> getCamposColuna(Cursor c, String columnName) {
        ArrayList<String> DadosColuna = new ArrayList<String>();
        int columnIndex = c.getColumnIndex(columnName);
        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            DadosColuna.add(c.getString(columnIndex));
        }
        return DadosColuna;
    }
}
