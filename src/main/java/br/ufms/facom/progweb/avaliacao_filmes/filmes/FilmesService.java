package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.Avaliacao;
import jakarta.persistence.EntityNotFoundException;

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

    public Filmes encontrarFilmePorId(Long id) {
        Optional<Filmes> optionalFilme = repository.findById(id);
        return optionalFilme.orElseThrow(() -> new EntityNotFoundException("Filme com ID " + id + " não encontrado."));
    }

    public void salvarFilme(FilmesDto dto) {
        Filmes newFilme = new Filmes(
            dto.getTitulo(),
            dto.getGenero(),
            dto.getDiretor(),
            dto.getAnoLancamento(),
            dto.getSinopse(),
            dto.getImagem()
        );

        if(repository.existsByTitulo(newFilme.getTitulo())) {
            throw new IllegalArgumentException("Filme com título '" + newFilme.getTitulo() + "' já existe.");
        }

        repository.save(newFilme);
    }
    
    public void excluirFilme(long id) {
        repository.deleteById(id);
    }

    private FilmesCardDto converterParaCardDto(Filmes filme){
        FilmesCardDto dto = new FilmesCardDto();
        dto.setId(filme.getId());
        dto.setTitulo(filme.getTitulo());
        dto.setGenero(filme.getGenero());
        dto.setImagem(filme.getImagem());
        dto.setDiretor(filme.getDiretor());
        dto.setAnoLancamento(filme.getAnoLancamento());
        dto.setSinopse(filme.getSinopse());
        double mediaAvaliacoes = filme.getAvaliacoes().stream()
            .mapToDouble(avaliacao -> avaliacao.getNota())
            .average()
            .orElse(0.0);
        dto.setMediaAvaliacoes(mediaAvaliacoes);
        dto.setAvaliacoes(filme.getAvaliacoes());
        dto.setTipo(filme.getTipo());
        return dto;
    }

    public List<FilmesCardDto> buscarTodosOsFilmes(){
        List<Filmes> filmes = (List<Filmes>) repository.findAll();
        return filmes.stream()
            .map(this::converterParaCardDto)
            .collect(Collectors.toList());
    }

    public FilmesCardDto buscarFilmeComoCardDtoPorId(Long id) {
        Filmes filmeEntidade = encontrarFilmePorId(id); // ou repository.findById(id).orElseThrow(...)
        
        FilmesCardDto filmeDto = converterParaCardDto(filmeEntidade);

        return filmeDto;
    }

    // Metodo para salvar avaliação de filme
    public void salvarAvaliacaoFilme(Avaliacao avaliacao) {
        Filmes filme = encontrarFilmePorId(avaliacao.getFilme().getId());
        if (filme == null) {
            throw new EntityNotFoundException("Filme com ID " + avaliacao.getFilme().getId() + " não encontrado.");
        }
        avaliacao.setFilme(filme);
        // Aqui você deve adicionar lógica para salvar a avaliação no repositório de avaliações
        // Por exemplo: avaliacaoRepository.save(avaliacao);
    }
}
