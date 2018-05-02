package fiap.scj.casamento.rest;

import fiap.scj.casamento.dto.EdicaoUsuarioDTO;
import fiap.scj.casamento.dto.MensagemDTO;
import fiap.scj.casamento.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<MensagemDTO> cadastrar(@RequestBody EdicaoUsuarioDTO usuario) {
        if (usuarioService.existe(usuario.getUsuario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MensagemDTO("usuario_ja_existe"));
        }
        usuarioService.inserir(usuario.toEntity());
        return ResponseEntity.ok(new MensagemDTO("usuario_cadastrado"));
    }

    @GetMapping("/{usuario}/autenticar")
    public ResponseEntity<MensagemDTO> autenticar(@PathVariable("usuario") String autenticacaoUsuario, @RequestHeader("senha") String senha) {
        if (usuarioService.validar(autenticacaoUsuario, senha)) {
            return ResponseEntity.ok(new MensagemDTO("autenticacao_valida"));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MensagemDTO("autenticacao_invalida"));
        }
    }

}
