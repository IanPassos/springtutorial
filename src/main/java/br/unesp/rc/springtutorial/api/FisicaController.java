package br.unesp.rc.springtutorial.api;



import br.unesp.rc.springtutorial.dto.FisicaDto;
import br.unesp.rc.springtutorial.model.Fisica;
import br.unesp.rc.springtutorial.service.FisicaService;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tutorial/api")
public class FisicaController {

    @Autowired
    private FisicaService fisicaService;

    private final PasswordEncoder encoder;

    public FisicaController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    @PostMapping("/save")
    public ResponseEntity<Object> savePessoa(@RequestBody @Validated FisicaDto fisicaDto){
        if(fisicaService.existsByCpf(fisicaDto.getCpf())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cpf já está em uso.");
        }
        if(fisicaService.findByAcesso_Usuario(fisicaDto.getAcesso().getUsuario()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuário já está em uso.");
        }
        Fisica fisicaModel = new Fisica();
        BeanUtils.copyProperties(fisicaDto,fisicaModel);
        fisicaModel.getAcesso().setSenha(encoder.encode(fisicaModel.getAcesso().getSenha()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fisicaService.save(fisicaModel));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Fisica>> getAllFisica() {
        return ResponseEntity.status(HttpStatus.OK).body(fisicaService.findAll());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Object> getPessoaPorCpf(@PathVariable(value= "cpf")String cpf){
        Optional<Fisica> fisicaModelOptional = Optional.ofNullable(fisicaService.findByCpf(cpf));
        if(!fisicaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fisicaModelOptional.get());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getPessoaPorEmail(@PathVariable(value= "email")String email){
        Optional<Fisica> fisicaModelOptional = Optional.ofNullable(fisicaService.findByEmail(email));
        if(!fisicaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fisicaModelOptional.get());
    }

    @GetMapping("/usuario/{usuario}")
    public ResponseEntity<Object> getPessoaPorUsuario(@PathVariable(value= "usuario")String usuario){
        Optional<Fisica> fisicaModelOptional = fisicaService.findByAcesso_Usuario(usuario);
        if(!fisicaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(fisicaModelOptional.get());
    }

    @DeleteMapping("/delete/{cpf}")
    public ResponseEntity<Object> deletePessoaPorCpf(@PathVariable(value= "cpf")String cpf){
        Optional<Fisica> fisicaModelOptional = Optional.ofNullable(fisicaService.findByCpf(cpf));
        if(!fisicaModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pessoa não encontrada.");
        }
        fisicaService.delete(fisicaModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pessoa deletada com sucesso.");
    }
}
