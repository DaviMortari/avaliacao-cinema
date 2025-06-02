package br.ufms.facom.progweb.avaliacao_filmes.filmes;

public class Filmes {
    private int id;
    private String titulo;
    private String genero;
    private String diretor;
    private int anoLancamento;
    private String sinopse;
    private int avaliacao;
    private String imagem;

    public Filmes(String titulo, String genero, String diretor, int anoLancamento, String sinopse) {
        this.titulo = titulo;
        this.genero = genero;
        this.diretor = diretor;
        this.anoLancamento = anoLancamento;
        this.sinopse = sinopse;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public String getSinopse() {
        return sinopse;
    }

    public int getAvaliacao() {
        return avaliacao;
    }
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }
    public String getImagem() {
        return imagem;
    }
    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
    
}
