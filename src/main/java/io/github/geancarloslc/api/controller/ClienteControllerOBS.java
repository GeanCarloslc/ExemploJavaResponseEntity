package io.github.geancarloslc.api.controller;


import io.github.geancarloslc.domain.entity.Cliente;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clientes/")
public class ClienteControllerOBS {

    @RequestMapping(
            value = "hello/{nomeCliente}",
            method = RequestMethod.GET,
            //Como que o metodo ira receber a requisicao
            consumes = { "application/json", "application/xml" },
            //Como que o metodo ira retornar a requisicao
            produces = { "application/json", "application/xml" }
    )
    @ResponseBody
    //@ResponseBody, diz que esta sendo retornado o corpo da request
    public Cliente helloClientes(
            @PathVariable("nomeCliente") String nomeCliente,
            //@PathVariable, diz que ira receber uma variavel via URL
            @RequestBody Cliente cliente
    ){

    return null;

    }
}
