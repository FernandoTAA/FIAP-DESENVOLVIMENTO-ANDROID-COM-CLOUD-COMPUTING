package fiap.scj.casamento.repository;

import fiap.scj.casamento.entity.Casamento;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CasamentoRepository extends MongoRepository<Casamento, String> {

}
