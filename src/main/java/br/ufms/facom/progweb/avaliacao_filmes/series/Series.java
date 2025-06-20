package br.ufms.facom.progweb.avaliacao_filmes.series;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.Avaliacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Series implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String titulo;

    @Column
    private String genero;

    @Column
    private String diretor;

    @Column
    private Date anoLancamento;

    @Column
    private String sinopse;

    @OneToMany
    private List<Avaliacao> avaliacao;

    @Column
    int temporadas;

    @Column
    private String imagem;

    private String tipo;;

    public Series() {}

    public Series(String titulo, String genero, String diretor, int anoLancamento, String sinopse, int temporadas) {
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.anoLancamento = java.sql.Date.valueOf(anoLancamento + "-01-01");
        this.sinopse = sinopse;
        this.temporadas = temporadas;
        this.imagem = "";
        this.tipo = "SERIE"; 
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
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

    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }
    //public void setAvaliacao(int avaliacao) {
    //    this.avaliacao = avaliacao;
    //}
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
    public String getTipo() {
        return tipo;
    }
}
