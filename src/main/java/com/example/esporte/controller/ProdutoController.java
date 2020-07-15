package com.example.esporte.controller;

import com.example.esporte.model.Produto;
import com.example.esporte.repository.CategoriaRepository;
import com.example.esporte.repository.MarcaRepository;
import com.example.esporte.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {


    public static String UPLOADED_FOLDER = System.getProperty("user.dir")+"/src/main/resources/static/images/";

    @Autowired
    MarcaRepository marcaRepository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @GetMapping("/listar")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("ListaProdutos");
        mv.addObject("produtos", produtoRepository.findAll());
        mv.addObject(new Produto());
        return mv;
    }

    @GetMapping("/adicionar")
    public ModelAndView adicionar(){
        ModelAndView mv = new ModelAndView("AdicionaProduto");
        mv.addObject("marcas", marcaRepository.findAll());
        mv.addObject("categorias", categoriaRepository.findAll());
        mv.addObject(new Produto());
        return mv;
    }

    @PostMapping("/adicionar")
    public ModelAndView adicionar(@Valid Produto produto,
                                  BindingResult result,
                                  RedirectAttributes attributes,
                                  @RequestParam("file") MultipartFile file){

        if (file.isEmpty()) {
            attributes.addFlashAttribute("message", "Please select a file to upload");
            ModelAndView mv4 = new ModelAndView("AdicionaProduto");
            mv4.addObject("marcas", marcaRepository.findAll());
            mv4.addObject("categorias", categoriaRepository.findAll());
            return mv4;
        }
        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            String caminho = "/images/" + file.getOriginalFilename();
            produto.setImagem(caminho);
            System.out.println(path.toString());
            Files.write(path, bytes);

            attributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        if(result.hasErrors()){
            ModelAndView mv2 = new ModelAndView("AdicionaProduto");
            mv2.addObject("marcas", marcaRepository.findAll());
            mv2.addObject("categorias", categoriaRepository.findAll());
            return mv2;
        }
        ModelAndView mv = new ModelAndView("redirect:/produtos/listar");
        if(produto.getId() != null){
            attributes.addFlashAttribute("mensagem", "Cliente editado com sucesso.");
        } else {
            attributes.addFlashAttribute("mensagem", "Cliente adicionado com sucesso.");
        }
        produtoRepository.save(produto);
        return mv;
    }

    @GetMapping("/remover/{id}")
    public ModelAndView remover(@PathVariable("id") Long id,
                                RedirectAttributes attributes){
        produtoRepository.deleteById(id);
        ModelAndView mv = new ModelAndView("redirect:/produtos/listar");
        attributes.addFlashAttribute("mensagem", "Produto removido com sucesso.");
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("AdicionaProduto");
        mv.addObject("produto", produtoRepository.findById(id));
        mv.addObject("marcas", marcaRepository.findAll());
        mv.addObject("categorias", categoriaRepository.findAll());
        mv.addObject("id", id);
        return mv;
    }

}
