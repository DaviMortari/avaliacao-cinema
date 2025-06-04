package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import org.springframework.data.repository.CrudRepository;

public interface FilmeRepository extends CrudRepository<Filmes, String> {
    
    // Métodos de consulta personalizados podem ser definidos aqui
    // Exemplo: List<Filmes> findByTituloContaining(String titulo);
    
    // Outros métodos de manipulação de dados podem ser adicionados conforme necessário
    
}
