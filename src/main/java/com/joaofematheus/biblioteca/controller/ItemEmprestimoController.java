package com.joaofematheus.biblioteca.controller;

import com.joaofematheus.biblioteca.model.ItemEmprestimo;
import com.joaofematheus.biblioteca.service.ItemEmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca/itememprestimo")
public class ItemEmprestimoController {

    @Autowired
    private ItemEmprestimoService itemEmprestimoService;

    @GetMapping("/{id}")
    public ResponseEntity<ItemEmprestimo> buscarItemPorId(@PathVariable int id) {
        ItemEmprestimo item = itemEmprestimoService.buscarPorId(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ItemEmprestimo>> listarTodosItens() {
        return ResponseEntity.ok(itemEmprestimoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<String> adicionarItem(@RequestBody ItemEmprestimo item) {
        itemEmprestimoService.adicionarItemEmprestimo(item);
        return ResponseEntity.ok("Item de empréstimo adicionado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarItem(@PathVariable int id, @RequestBody ItemEmprestimo item) {
        item.setId(id);
        itemEmprestimoService.atualizarItemEmprestimo(item);
        return ResponseEntity.ok("Item de empréstimo atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarItem(@PathVariable int id) {
        itemEmprestimoService.deletarItemEmprestimo(id);
        return ResponseEntity.ok("Item de empréstimo removido com sucesso!");
    }
}
