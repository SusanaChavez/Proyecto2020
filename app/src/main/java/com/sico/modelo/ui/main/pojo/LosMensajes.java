package com.sico.modelo.ui.main.pojo;

import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LosMensajes {

    public static final List<Mensaje> ITEMS = new ArrayList<Mensaje>();

    public static final Map<String, Mensaje> ITEM_MAP = new HashMap<String, Mensaje>();

    private static final int COUNT = 25;

    static {
        for (int i = 1; i <= COUNT; i++) {
            addItem(crear(i));
        }
    }

    private static void addItem(Mensaje item) {
        ITEMS.add(item);
        ITEM_MAP.put(String.valueOf(item.getId()), item);
    }

    private static Mensaje crear(int position) {
        return new Mensaje(position, "Item " + position, buscarDetalle(position));
    }

    private static String buscarDetalle(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Detalle ").append(position);
  //      for (int i = 0; i < position; i++) {
   //         builder.append("\nMás información sobre detalle.");
    //    }
        return builder.toString();
    }
}