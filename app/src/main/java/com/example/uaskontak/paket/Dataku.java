package com.example.uaskontak.paket;


public class Dataku {
    private String key;
    private String nama;
    private String number;

    public Dataku(){

    }

    public Dataku(String key, String nama, String number) {
        this.key = key;
        this.nama = nama;
        this.number = number;

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
