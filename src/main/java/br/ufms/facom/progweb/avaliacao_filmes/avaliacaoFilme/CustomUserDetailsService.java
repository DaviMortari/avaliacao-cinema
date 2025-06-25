package br.ufms.facom.progweb.avaliacao_filmes.avaliacaoFilme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import br.ufms.facom.progweb.avaliacao_filmes.usuarios.Usuarios;
import br.ufms.facom.progweb.avaliacao_filmes.usuarios.UsuariosRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuariosRepository usuariosRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = usuariosRepository.findByEmail(username);

        if(usuario == null) {
            throw new UsernameNotFoundException("Usuário não encontrado com o email: " + username);
        }

        return new User(
            usuario.getEmail(),
            usuario.getSenha(),
            new ArrayList<>()
        );
    }
}
