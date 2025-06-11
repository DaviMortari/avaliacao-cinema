package br.ufms.facom.progweb.avaliacao_filmes.usuarios;

import org.springframework.data.repository.CrudRepository;

public interface UsuariosRepository extends CrudRepository<Usuarios, Long> {
    Usuarios findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    Usuarios findByCpf(String cpf);
    long countByTipoUsuario(Usuarios.TipoUsuario tipoUsuario);
    long countBySexo(Usuarios.Sexo sexo);
    long countByIdade(int idade);
    boolean existsByNome(String nome);
    Usuarios findByNome(String nome);
    Usuarios findById(long id);
    
}
