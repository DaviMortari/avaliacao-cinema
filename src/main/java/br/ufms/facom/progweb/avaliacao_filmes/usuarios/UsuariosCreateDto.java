package br.ufms.facom.progweb.avaliacao_filmes.usuarios;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UsuariosCreateDto {
    @NotBlank(message = "O nome do usuário não pode estar vazio.")
    private String nome;

    @NotBlank(message = "O email do usuário não pode estar vazio.")
    private String email;

    @NotBlank(message = "A senha do usuário não pode estar vazia.")
    private String senha;

    @NotNull(message = "A idade do usuário não pode ser nula.")
    private int idade;

    @NotBlank(message = "O CPF do usuário não pode estar vazio.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos.")
    private String cpf;

    @NotNull(message = "O sexo do usuário não pode ser nulo.")
    private Usuarios.Sexo sexo;

    @NotNull(message = "O tipo de usuário não pode ser nulo.")
    private Usuarios.TipoUsuario tipoUsuario;

    public UsuariosCreateDto() {}

    

    public UsuariosCreateDto(String nome, String email, String senha, int idade, String cpf,Usuarios.Sexo sexo, Usuarios.TipoUsuario tipoUsuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idade = idade;
        this.cpf = cpf;
        this.sexo = sexo;
        this.tipoUsuario = tipoUsuario;
    }



    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public int getIdade() {
        return idade;
    }

    public Usuarios.Sexo getSexo() {
        return sexo;
    }

    public Usuarios.TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public String getCpf() {
        return cpf;
    }
}
