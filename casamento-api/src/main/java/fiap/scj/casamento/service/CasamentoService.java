package fiap.scj.casamento.service;

import fiap.scj.casamento.entity.Casamento;
import fiap.scj.casamento.repository.CasamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class CasamentoService {

    @Autowired
    private CasamentoRepository casamentoRepository;

    public Collection<Casamento> buscarTodos() {
        return casamentoRepository.findAll();
    }

    public Casamento buscarPorId(String casamentoId) {
        Optional<Casamento> casamento = casamentoRepository.findById(casamentoId);
        return casamento.isPresent() ? casamento.get() : null;
    }

    public Casamento inserir(Casamento casamento) {
        return casamentoRepository.insert(casamento);
    }

    public Casamento updated(Casamento casamento) {
        return casamentoRepository.save(casamento);
    }

    public void removerPorId(String casamentoId) {
        casamentoRepository.deleteById(casamentoId);
    }
}
