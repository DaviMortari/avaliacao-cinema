package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

// import org.hibernate.annotations.CascadeType; // Removed incorrect import
import jakarta.persistence.CascadeType;

import br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme.Avaliacao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Filmes implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "filme", cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JsonManagedReference
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @Column
    private String imagem;

    private String tipo;

    public Filmes() {}

    public Filmes(String titulo, String genero, String diretor, int anoLancamento, String sinopse, String imagem) {
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.anoLancamento = java.sql.Date.valueOf(anoLancamento + "-01-01");
        this.sinopse = sinopse;
        this.imagem = imagem;
        this.tipo = "filme";
    }

    public Long getId() {
        return id;
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

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
    //public void setAvaliacao(Avaliacao avaliacoes) {
    //    this.avaliacoes = avaliacoes;
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
