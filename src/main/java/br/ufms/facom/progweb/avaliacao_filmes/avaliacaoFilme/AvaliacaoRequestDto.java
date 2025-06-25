package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

// Este DTO representa os dados que CHEGAM do formulário do front-end.
// Ele é simples e contém apenas os IDs necessários.
public class AvaliacaoRequestDto {

    private Long itemId;
    private double nota;
    private String comentario;
    private String tipoItemAvaliado;

    public AvaliacaoRequestDto() {}

    public AvaliacaoRequestDto(Long itemId, double nota, String comentario, String tipoItemAvaliado) {
        this.itemId = itemId;
        this.nota = nota;
        this.comentario = comentario;
        this.tipoItemAvaliado = tipoItemAvaliado;
    }

    public long getFilmeId() {
        return itemId;
    }
    public void setFilmeId(Long itemId) {
        this.itemId = itemId;
    }

    public long getSerieId() {
        return itemId;
    }
    public void setSerieId(Long itemId) {
        this.itemId = itemId;
    }

    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getTipoItemAvaliado() {
        return tipoItemAvaliado;
    }
    public void setTipoItemAvaliado(String tipoItemAvaliado) {
        this.tipoItemAvaliado = tipoItemAvaliado;
    }
}