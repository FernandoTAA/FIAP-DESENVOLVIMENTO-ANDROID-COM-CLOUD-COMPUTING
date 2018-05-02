package fiap.scj.casamento.dto;

import fiap.scj.casamento.entity.Casamento;

import java.time.format.DateTimeFormatter;

public class CasamentoDTO extends EdicaoCasamentoDTO {

    private String id;

    @Override
    public Casamento toEntity() {
        Casamento casamento = super.toEntity();
        casamento.setId(id);
        return casamento;
    }

    public static CasamentoDTO valueOf(Casamento casamento) {
        CasamentoDTO casamentoDTO = null;
        if (casamento != null) {
            casamentoDTO = new CasamentoDTO();
            casamentoDTO.setId(casamento.getId());
            casamentoDTO.setNomeHomem(casamento.getNomeHomem());
            casamentoDTO.setNomeMulher(casamento.getNomeMulher());
            if (casamento.getData() != null) {
                String data = DateTimeFormatter.ISO_DATE.format(casamento.getData());
                casamentoDTO.setData(data);
            }
            casamentoDTO.setLocal(casamento.getLocal());
        }
        return casamentoDTO;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
