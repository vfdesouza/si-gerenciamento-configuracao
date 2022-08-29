package br.com.mudi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    RequestRepository rr;
    @GetMapping
    public ModelAndView findAllOrfindByProductName(@RequestParam(value = "productName", required = false) String productName) {
        if (productName == null) {
            ModelAndView mv = new ModelAndView("home");
            Iterable<Request> requests = rr.findAll();
            mv.addObject("requests", requests);
            return mv;
        }
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("requests", rr.findByProductNameIgnoreCaseContaining(productName));
        return mv;
    }
}