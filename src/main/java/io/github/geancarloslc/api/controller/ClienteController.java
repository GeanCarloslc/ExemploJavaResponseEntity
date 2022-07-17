package io.github.geancarloslc.api.controller;


import io.github.geancarloslc.domain.entity.Cliente;
import io.github.geancarloslc.domain.repository.ClienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes/")
public class ClienteController {

    @Autowired
    ClienteDAO clienteDAO;

    @GetMapping("getClienteById/{id}") //Get para obter dados
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable("id") Integer id){

        Optional<Cliente> cliente = clienteDAO.findById(id);
        //Optional, usado pois o retorno da pesquisa pode estar presente ou nao

        if (cliente.isPresent()){
            //ResponseEntity, representa o corpo da resposta do JSON
            ResponseEntity<Cliente> responseEntity
                    = new ResponseEntity<>(cliente.get(), HttpStatus.OK);

            //Maneira mais simples
            return ResponseEntity.ok(cliente.get());

        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping("salvarCliente")
    @ResponseBody
    public ResponseEntity salvarCliente(@RequestBody Cliente cliente){
        Cliente clienteSalvo = clienteDAO.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }


    @DeleteMapping("deletarCliente/{id}")
    @ResponseBody
    public ResponseEntity deletarCliente(@PathVariable Integer id){
        Optional<Cliente> cliente = clienteDAO.findById(id);
        if (cliente.isPresent()){
            clienteDAO.delete(cliente.get());
            //Usado quando não é necessario voltar nenhum dado para a requisicao
            return ResponseEntity.noContent().build();//Status 204
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("atualizarCliente/{id}")
    //Atualizar integralmente um recurso no servidor
    @ResponseBody
    public ResponseEntity atualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente){

        return clienteDAO
                .findById(id)
                .map(clienteExistente ->{
                    cliente.setId(clienteExistente.getId());
                    clienteDAO.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet( () -> ResponseEntity.notFound().build());

    }

    @GetMapping("filtrarCliente")
    @ResponseBody
    public ResponseEntity filtrarCliente (Cliente filtroCliente){
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        //ExampleMatcher.StringMatcher.CONTAINING, similar ao like do SQL
        Example exampleCliente = Example.of(filtroCliente, exampleMatcher);
        List<Cliente> clienteLista = clienteDAO.findAll(exampleCliente);

        return ResponseEntity.ok(clienteLista);
    }


}
