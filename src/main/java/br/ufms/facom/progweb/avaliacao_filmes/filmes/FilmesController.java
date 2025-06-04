package br.ufms.facom.progweb.avaliacao_filmes.filmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/filmes")
public class FilmesController {
    @Autowired
    FilmeRepository filmeRepository;

    @GetMapping("/info") // Endpoint para listar todos os filmes
    @ResponseBody // Endpoint para obter informações sobre o serviço
    public Iterable<Filmes> listarTodos() {
        return filmeRepository.findAll();
    }
}
