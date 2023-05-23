package br.unesp.rc.springtutorial.service;


import br.unesp.rc.springtutorial.model.Fisica;
import br.unesp.rc.springtutorial.repository.FisicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FisicaService {

    @Autowired
    private FisicaRepository repository;

    public FisicaService(){

    }

    public Fisica findByCpf(String cpf){
        Fisica pessoaEncontrada = null;

        if (repository!=null){
            pessoaEncontrada = repository.findByCpf(cpf);
        }
        return pessoaEncontrada;
    }

    public Fisica findByEmail(String email){
        Fisica pessoaEncontrada = null;

        if (repository != null){
            pessoaEncontrada = repository.findByEmail(email);
        }

        return pessoaEncontrada;
    }

    public Optional<Fisica> findById(Long id){
        Optional<Fisica> pessoaEcontrada = null;

        if (repository!=null){
            pessoaEcontrada = repository.findById(id);
        }
        return pessoaEcontrada;
    }
    public Optional<Fisica> findByAcesso_Usuario(String usuario){
        Optional<Fisica> pessoaEncontrada = null;

        if (repository!=null){
            pessoaEncontrada = Optional.ofNullable(repository.findByAcesso_Usuario(usuario));
        }
        return pessoaEncontrada;
    }

    @Transactional
    public Fisica save(Fisica pessoa){
        Fisica pessoaNova = null;

        if (repository != null){
            pessoaNova = repository.save(pessoa);
        }

        return pessoaNova;
    }

    @Transactional
    public void delete(Fisica pessoa){

        if (repository != null){
            repository.delete(pessoa);
        }
    }

    public List<Fisica> findAll(){
        List<Fisica> list = null;

        if (repository != null){
            list = new ArrayList<>();
            list = repository.findAll();
        }

        return list;
    }

    public boolean existsByCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }
}
