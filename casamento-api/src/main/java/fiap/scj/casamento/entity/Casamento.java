package fiap.scj.casamento.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public class Casamento {

    @Id
    private String id;
    private String nomeHomem;
    private String nomeMulher;
    private LocalDate data;
    private String local;

    public Casamento() {
    }

    public Casamento(String nomeHomem, String nomeMulher, LocalDate data, String local) {
        this.nomeHomem = nomeHomem;
        this.nomeMulher = nomeMulher;
        this.data = data;
        this.local = local;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }
}
