package br.unesp.rc.springtutorial.data;

import br.unesp.rc.springtutorial.model.Acesso;
import br.unesp.rc.springtutorial.model.Fisica;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public class DetalheUsuarioData implements UserDetails {

    private final Optional<Fisica> user;

    public DetalheUsuarioData(Optional<Fisica> user) {
        this.user = user;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.orElse(new Fisica()).getAcesso().getSenha();
    }

    @Override
    public String getUsername() {
        return user.orElse(new Fisica()).getAcesso().getUsuario();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
