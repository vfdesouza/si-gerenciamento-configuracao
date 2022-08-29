package br.com.mudi.controllers;

import br.com.mudi.models.Request;
import br.com.mudi.models.StatusRequest;
import br.com.mudi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("formCad")
public class RequestController {
    @Autowired
    private RequestRepository rr;

    @GetMapping
    public String form(Request request) {
        return "formCad";
    }

    @PostMapping
    public String insert(@Valid Request request, BindingResult result){
        if(result.hasErrors()) {
            return "formCad";
        }
        request.setStatusRequest(StatusRequest.valueOf("WAITING"));
        rr.save(request);
        return "redirect:/home";
    }
}
