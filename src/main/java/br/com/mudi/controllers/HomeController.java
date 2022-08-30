package br.com.mudi.controllers;
import br.com.mudi.models.Request;
import br.com.mudi.models.StatusRequest;
import br.com.mudi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("/{statusRequest}")
    public String status(@PathVariable("statusRequest") String statusRequest, Model model) {
        List<Request> requests = rr.findByStatusRequest(StatusRequest.valueOf(statusRequest.toUpperCase()));
        model.addAttribute("requests", requests);
        model.addAttribute("statusRequest", statusRequest);
        return "home";
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:/home";
    }
}