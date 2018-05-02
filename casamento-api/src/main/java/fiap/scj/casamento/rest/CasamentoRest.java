package fiap.scj.casamento.rest;

import fiap.scj.casamento.dto.CasamentoDTO;
import fiap.scj.casamento.dto.EdicaoCasamentoDTO;
import fiap.scj.casamento.entity.Casamento;
import fiap.scj.casamento.service.CasamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/casamento")
public class CasamentoRest {

    @Autowired
    private CasamentoService casamentoService;

    @GetMapping
    public Collection<CasamentoDTO> buscarTodos() {
        return casamentoService.buscarTodos().stream().map(CasamentoDTO::valueOf).collect(Collectors.toList());
    }

    @GetMapping("/{casamentoId}")
    public CasamentoDTO buscarPorId(@PathVariable("casamentoId") String casamentoId) {
        return CasamentoDTO.valueOf(casamentoService.buscarPorId(casamentoId));
    }

    @PostMapping
    public CasamentoDTO inserir(@RequestBody EdicaoCasamentoDTO casamento) {
        return CasamentoDTO.valueOf(casamentoService.inserir(casamento.toEntity()));
    }

    @PutMapping("/{casamentoId}")
    public CasamentoDTO atualizar(@PathVariable("casamentoId") String casamentoId, @RequestBody EdicaoCasamentoDTO casamento) {
        Casamento casamentoORM = casamento.toEntity();
        casamentoORM.setId(casamentoId);
        return CasamentoDTO.valueOf(casamentoService.updated(casamentoORM));
    }

    @DeleteMapping("/{casamentoId}")
    public void removerPorId(@PathVariable("casamentoId") String casamentoId) {
        casamentoService.removerPorId(casamentoId);
    }

}
