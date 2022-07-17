package io.github.geancarloslc.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import javax.persistence.*;

@Getter
@Setter
@ToString

@Entity
@Table(name = "item_pedido")
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "quantidade")
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;


    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

}
