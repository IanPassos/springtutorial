package br.unesp.rc.springtutorial.repository;

import br.unesp.rc.springtutorial.model.Fisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;


@Repository
public interface FisicaRepository extends JpaRepository<Fisica, Long> {

    Fisica findByCpf(String cpf);

    Fisica findByEmail(String email);

    Fisica findByAcesso_Usuario(String usuario);

    boolean existsByCpf(String cpf);

}