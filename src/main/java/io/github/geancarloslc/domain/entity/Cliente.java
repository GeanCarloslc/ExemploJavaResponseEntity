package io.github.geancarloslc.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString

//JPA
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nome", length = 100)
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    //FetchType.LAZY não busca os pedidos do cliente ao carregar
    //mappedBy - Essa entidade não existe chave para pedido, quem possui a chave é a tabela de pedido,
    //utilizando o mappedBy é possível realizar esse fetch buscando os pedidos desse cliente
    //Variaveis que poderia ser usadas
    //Colletion
    //List
    //Set - Evita possiveis erros do hibernate, não aceita item repitidos
    private Set<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public Cliente(String nome) {
        this.nome = nome;
    }


}
