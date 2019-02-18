package it.evolvere.model;

public class Libro {

    private Long id;

    private String codice;

    private String categoria;

    private String titolo;

    private String descrizione;

    private String autore;
   // private Autore autore;

    public Libro(Long id, String titolo, String descrizione, String autore) {
        this.id = id;
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.autore = autore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public String getCodice() {
        return codice;
    }
    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return id + " - " + titolo  + " - " + autore;
    }
}
