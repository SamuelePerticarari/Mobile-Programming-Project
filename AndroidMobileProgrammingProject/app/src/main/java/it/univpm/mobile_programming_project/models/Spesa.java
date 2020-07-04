package it.univpm.mobile_programming_project.models;

import java.util.Date;
import java.util.Map;

public class Spesa {

    private String id;
    private String nome;
    private String descrizione;
    private String tipo;
    private Double prezzo;
    private Date dataInserimento;
    private Date dataPagamento;

    public Spesa( String id, String nome, String descrizione, String tipo, Double prezzo, Date dataInserimento, Date dataPagamento ) {
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.prezzo = prezzo;
        this.dataInserimento = dataInserimento;
        this.dataPagamento = dataPagamento;
    }

    public Spesa() {}

    public Boolean isSpesaPagata() {
        return this.dataPagamento != null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Date getDataInserimento() {
        return dataInserimento;
    }

    public void setDataInserimento(Date dataInserimento) {
        this.dataInserimento = dataInserimento;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }



    public void createFromHashMap(Map<String, Object> spesaSingola) {

        this.setId((String)spesaSingola.get("id"));
        this.setNome((String)spesaSingola.get("nome"));
        this.setDescrizione((String)spesaSingola.get("descrizione"));
        this.setPrezzo((Double)spesaSingola.get("prezzo"));

//        this.setDataInserimento((String)spesaSingola.get("dataInserimento"));
    }
}
