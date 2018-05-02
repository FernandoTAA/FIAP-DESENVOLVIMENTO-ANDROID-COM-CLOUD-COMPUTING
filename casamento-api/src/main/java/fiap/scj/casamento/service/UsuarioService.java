package fiap.scj.casamento.service;

import fiap.scj.casamento.entity.Usuario;
import fiap.scj.casamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void inserir(Usuario usuario) {
        usuarioRepository.insert(usuario);
    }

    public boolean existe(String usuario) {
        return usuarioRepository.findByUsuario(usuario) != null;
    }

    public boolean validar(String usuario, String senha) {
        Usuario entity = usuarioRepository.findByUsuario(usuario);
        String senhaCriptografada = Usuario.criptografar(senha);
        return entity != null && entity.getSenhaCriptografada().equals(senhaCriptografada);
    }
}
