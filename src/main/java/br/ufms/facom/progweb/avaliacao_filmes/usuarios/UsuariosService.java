package br.ufms.facom.progweb.avaliacao_filmes.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuariosService {
    @Autowired
    private UsuariosRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Iterable<Usuarios> listarTodos() {
        return repository.findAll();
    }

    public Usuarios encontrarUsuarios(String cpf){
        return repository.findByCpf(cpf);
    }

    public void salvarUsuarios(UsuariosCreateDto dto) {
        String senhaHasheada = passwordEncoder.encode(dto.getSenha());

        Usuarios newUsuario = new Usuarios(
            dto.getNome(),
            dto.getEmail(),
            senhaHasheada,
            dto.getIdade(),
            dto.getCpf(),
            dto.getSexo(),
            dto.getTipoUsuario()
        );

        if(repository.existsByEmail(newUsuario.getEmail())) {
            throw new IllegalArgumentException("Usuário com email '" + newUsuario.getEmail() + "' já existe.");
        }

        if(repository.existsByCpf(newUsuario.getCpf())) {
            throw new IllegalArgumentException("Usuário com CPF '" + newUsuario.getCpf() + "' já existe.");
        }

        repository.save(newUsuario);
    }

    public void excluirUsuarios(String cpf) {
        Usuarios usuario = repository.findByCpf(cpf);
        if (usuario != null) {
            repository.delete(usuario);
        } else {
            throw new IllegalArgumentException("Usuário com CPF '" + cpf + "' não encontrado.");
        }
    }
}
