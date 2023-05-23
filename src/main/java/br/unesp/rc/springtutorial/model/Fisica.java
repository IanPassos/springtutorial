package br.unesp.rc.springtutorial.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "PessoaFisica")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false,exclude = {"dataNascimento"})
@PrimaryKeyJoinColumn(name="id")
@ToString(callSuper = true)
public class Fisica extends Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;


}