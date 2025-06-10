package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmesService {
    @Autowired
    private FilmeRepository repository;

    public Iterable<Filmes> listarTodos() {
        return repository.findAll();
    }

    public long contarPorGenero(String genero) {
        return repository.countByGenero(genero);
    }

    public Filmes encontrarFilme(String titulo){
        return repository.findByTituloContaining(titulo).stream().findFirst().orElse(null);
    }

    public void salvarFilme(FilmesDto dto) {
        Filmes newFilme = new Filmes(
            dto.getTitulo(),
            dto.getGenero(),
            dto.getDiretor(),
            dto.getAnoLancamento(),
            dto.getSinopse()
        );
        repository.save(newFilme);
    }
    
    public void excluirFilme(String id) {
        repository.deleteById(id);
    }
}
