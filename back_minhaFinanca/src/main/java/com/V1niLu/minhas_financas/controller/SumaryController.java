package com.V1niLu.minhas_financas.controller;

import com.V1niLu.minhas_financas.dto.SumaryResponseDTO;
import com.V1niLu.minhas_financas.service.SumaryService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sumary")
@CrossOrigin("*")
public class SumaryController {

    SumaryService sumaryService;

    SumaryController(SumaryService sumaryService){
        this.sumaryService = sumaryService;
    }

    @GetMapping
    public SumaryResponseDTO get(){
        return sumaryService.compute();
    }

}
