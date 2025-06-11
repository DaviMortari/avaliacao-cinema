package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface FilmeRepository extends CrudRepository<Filmes, Long> {
    Filmes findById(long id);
    long countByGenero(String genero);
    List<Filmes> findByGenero(String genero);
    List<Filmes> findByTituloContaining(String titulo);
    boolean existsByTitulo(String titulo);
    
    // Métodos de consulta personalizados podem ser definidos aqui
    // Exemplo: List<Filmes> findByTituloContaining(String titulo);
    
    // Outros métodos de manipulação de dados podem ser adicionados conforme necessário
    
}
