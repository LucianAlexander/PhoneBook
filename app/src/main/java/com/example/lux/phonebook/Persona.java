package com.example.lux.phonebook;

public class Persona {

    private String nome,cognome,indirizo,telefono,eta;

    public Persona(String nome, String cognome, String indirizzo, String telefono, String eta){
        this.setNome(nome);
        this.setCognome(cognome);
        this.setIndirizzo(indirizzo);
        this.setTelefono(telefono);
        this.setEta(eta);
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getIndirizzo() {
        return indirizo;
    }

    public void setIndirizzo(String indirizo) {
        this.indirizo = indirizo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }
}
