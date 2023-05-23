package br.unesp.rc.springtutorial.service;

import br.unesp.rc.springtutorial.data.DetalheUsuarioData;
import br.unesp.rc.springtutorial.model.Acesso;
import br.unesp.rc.springtutorial.model.Fisica;
import br.unesp.rc.springtutorial.repository.FisicaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetalheUsuarioServiceImpl implements UserDetailsService {

    private final FisicaRepository repository;

    public DetalheUsuarioServiceImpl(FisicaRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Fisica> usuario = Optional.ofNullable(repository.findByAcesso_Usuario(username));
        if (usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuário [" + username + "] não encontrado.");
        }

        return new DetalheUsuarioData(usuario);
    }
}
