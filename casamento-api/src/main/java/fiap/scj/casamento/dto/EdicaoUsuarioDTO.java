package fiap.scj.casamento.dto;

import fiap.scj.casamento.entity.Usuario;

public class EdicaoUsuarioDTO {

    private String nome;
    private String email;
    private String usuario;
    private String senha;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario toEntity() {
        return new Usuario(nome, email, usuario, senha);
    }
}
