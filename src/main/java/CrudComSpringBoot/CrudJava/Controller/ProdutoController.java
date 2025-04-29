package CrudComSpringBoot.CrudJava.Controller;
import CrudComSpringBoot.CrudJava.Dto.ProdutoDto;
import CrudComSpringBoot.CrudJava.Repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;

import CrudComSpringBoot.CrudJava.Model.Produto;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/produto")

public class ProdutoController {
    @Autowired
    ProdutoRepository repository;


    @GetMapping
    public ResponseEntity getAll(){
        List<Produto> listProdutos = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listProdutos);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity getById(@PathVariable (value = "id")Integer id){
        Optional produto = repository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto not found");

        }
        return ResponseEntity.status(HttpStatus.FOUND).body(produto.get());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody ProdutoDto dto){
        var produto = new Produto();
        BeanUtils.copyProperties(dto, produto);
        return  ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));

    }
    @PutMapping("/id")
    public ResponseEntity update(@PathVariable (value = "id") Integer id, @RequestBody ProdutoDto dto){
        Optional<Produto> produto = repository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto not found");

        }
        var ProdutoModel = produto.get();
        BeanUtils.copyProperties(dto, ProdutoModel);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(ProdutoModel));
    }


    @DeleteMapping("/id")
    public ResponseEntity delete(@PathVariable (value = "id")Integer id){
        Optional<Produto> produto = repository.findById(id);
        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("produto not found");

        }
        repository.delete(produto.get());
        return ResponseEntity.status(HttpStatus.OK).body("produto deletado");
    }

}
