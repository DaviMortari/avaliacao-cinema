package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

// Este DTO representa os dados que CHEGAM do formulário do front-end.
// Ele é simples e contém apenas os IDs necessários.
public class AvaliacaoRequestDto {

    private Long filmeId;
    private double nota;
    private String comentario;
    private Long usuarioId;

    public AvaliacaoRequestDto() {}

    public AvaliacaoRequestDto(Long filmeId, double nota, String comentario, Long usuarioId) {
        this.filmeId = filmeId;
        this.nota = nota;
        this.comentario = comentario;
        this.usuarioId = usuarioId;
    }

    public long getFilmeId() {
        return filmeId;
    }
    public void setFilmeId(Long filmeId) {
        this.filmeId = filmeId;
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
    public long getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }
}