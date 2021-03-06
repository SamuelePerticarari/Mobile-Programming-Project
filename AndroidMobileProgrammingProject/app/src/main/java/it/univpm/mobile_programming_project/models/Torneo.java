package it.univpm.mobile_programming_project.models;

import java.util.Date;
import java.util.Map;

import it.univpm.mobile_programming_project.utils.Helper;

public class Torneo {
    private String id;
    private String titolo;
    private String categoria;
    private String indirizzo;
    private Date dataOra;
    private String regolamento;

    public Torneo(){}

    public Torneo(String id, String titolo, String categoria, String indirizzo, Date dataOra, String regolamento) {
        this.id = id;
        this.titolo = titolo;
        this.categoria = categoria;
        this.indirizzo = indirizzo;
        this.dataOra = dataOra;
        this.regolamento = regolamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Date getDataOra() {
        return dataOra;
    }

    public void setDataOra(Date dataOra) {
        this.dataOra = dataOra;
    }

    public String getRegolamento() {
        return regolamento;
    }

    public void setRegolamento(String regolamento) {
        this.regolamento = regolamento;
    }

    public void createFromHashMap(Map<String, Object> torneoSingolo) {

        this.setId((String)torneoSingolo.get("id"));
        this.setTitolo((String)torneoSingolo.get("titolo"));
        this.setCategoria((String)torneoSingolo.get("categoria"));
        this.setIndirizzo((String)torneoSingolo.get("indirizzo"));
        this.setRegolamento((String)torneoSingolo.get("regolamento"));
        this.setDataOra(Helper.fromMillisToDate((Long)torneoSingolo.get("dataOra")));
    }
}
