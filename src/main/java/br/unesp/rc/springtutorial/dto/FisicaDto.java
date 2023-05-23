package br.unesp.rc.springtutorial.dto;

import br.unesp.rc.springtutorial.model.Acesso;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class FisicaDto {


    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    @NotBlank
    private Acesso acesso;
}
