package com.example.esporte.controller;

import com.example.esporte.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/")
    public ModelAndView index() {
       ModelAndView mv = new ModelAndView("Home");
       mv.addObject("produtos", produtoRepository.findAll());
       return mv;
    }
}
