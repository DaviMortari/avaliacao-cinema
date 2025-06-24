package br.ufms.facom.progweb.avaliacao_filmes.series;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.Avaliacao;

public class SeriesCardDto {
    private Long id;
    private String titulo;
    private String genero;
    private String imagem;
    private String diretor;
    private Date anoLancamento;
    private String sinopse;
    private double mediaAvaliacoes;
    private List<Avaliacao> avaliacoes = new ArrayList<>();
    private int temporadas;
    private String tipo;

    public SeriesCardDto(Long id, String titulo, String genero, String imagem, int temporadas,
            String diretor, Date anoLancamento, String sinopse, double mediaAvaliacoes) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.imagem = imagem;
        this.temporadas = temporadas;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
        this.sinopse = sinopse;
        this.mediaAvaliacoes = mediaAvaliacoes;
        this.tipo = "SERIE";
    }
    public SeriesCardDto() {}

    public Long getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }
    public String getImagem() {
        return imagem;
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    public void setMediaAvaliacoes(double mediaAvaliacoes) {
        this.mediaAvaliacoes = mediaAvaliacoes;
    }

    public int getTemporadas() {
        return temporadas;
    }
    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Date getAnoLancamento() {
        return anoLancamento;
    }
    public void setAnoLancamento(Date anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getSinopse() {
        return sinopse;
    }
    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
