package fiap.scj.casamento.repository;

import fiap.scj.casamento.entity.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Usuario findByUsuario(String usuario);

}
