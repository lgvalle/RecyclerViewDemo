package com.pruebas.lgvalle.mispruebas;

/**
 * Created by lgvalle on 13/07/14.
 */
public class Item {

    private String url;
    private String name;

    public Item(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

}
