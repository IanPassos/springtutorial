package br.unesp.rc.springtutorial.model;

import lombok.*;
import org.hibernate.validator.constraints.CodePointLength;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity(name = "Pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email",nullable = false)
    private String email;

    @Embedded
    private Acesso acesso;


}