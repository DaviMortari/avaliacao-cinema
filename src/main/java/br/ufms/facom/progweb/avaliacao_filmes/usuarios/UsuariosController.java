package br.ufms.facom.progweb.avaliacao_filmes.usuarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService service;

    @GetMapping("/info")
    public Iterable<Usuarios> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("{cpf}")
    public Usuarios encontrarUsuarios(String cpf) {
        return service.encontrarUsuarios(cpf);
    }

    @PostMapping
    public ResponseEntity<UsuariosCreateDto> salvarUsuarios(@RequestBody UsuariosCreateDto dto) {
        service.salvarUsuarios(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @DeleteMapping("/excluir/{cpf}")
    public void excluirUsuarios(String cpf) {
        service.excluirUsuarios(cpf);
    }
}
