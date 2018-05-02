package fiap.scj.casamento.dto;

import fiap.scj.casamento.entity.Casamento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EdicaoCasamentoDTO {

    private String nomeHomem;
    private String nomeMulher;
    private String data;
    private String local;

    public Casamento toEntity() {
        LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ISO_LOCAL_DATE);
        return new Casamento(nomeHomem, nomeMulher, localDate, local);
    }

    public String getNomeHomem() {
        return nomeHomem;
    }

    public void setNomeHomem(String nomeHomem) {
        this.nomeHomem = nomeHomem;
    }

    public String getNomeMulher() {
        return nomeMulher;
    }

    public void setNomeMulher(String nomeMulher) {
        this.nomeMulher = nomeMulher;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
