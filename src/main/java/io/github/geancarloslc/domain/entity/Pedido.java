package io.github.geancarloslc.domain.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "data_pedido")
    private LocalDate dataPedido;

    @Column(name = "total", length = 20, precision = 2)
    private BigDecimal total;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ToString.Exclude
    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> listaItemPedidos;

}
