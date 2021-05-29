package com.kumbaya.backendapi.controller;

import com.kumbaya.backendapi.entity.Profession;
import com.kumbaya.backendapi.model.response.ApiResponse;
import com.kumbaya.backendapi.repository.ProfessionRepository;
import com.kumbaya.backendapi.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path = "/api/profession")
public class ProfessionController {
    @Autowired
    private ProfessionRepository professionRepository;

    @Autowired
    private ProfessionService professionService;

    @GetMapping(path = "/{id}")
    public @ResponseBody
    ResponseEntity getProfessionById(@PathVariable Integer id) {
        if (doesProfessionExist(id)) {
            Profession profession = professionRepository.findById(id).get();

            return ResponseEntity.ok(new ApiResponse(true, profession));
        }

        return ResponseEntity.ok(new ApiResponse(false, "Beroep is niet gevonden!"));
    }

    @PostMapping(path = "/add")
    public @ResponseBody
    ResponseEntity addProfession(@RequestBody Profession profession) {
        ApiResponse validateProfession = professionService.validateProfession(profession);

        if(!validateProfession.getSuccess()) {
            return ResponseEntity.ok(new ApiResponse(false, validateProfession.getMessage()));
        }

        professionRepository.save(profession);

        return ResponseEntity.ok(new ApiResponse(true, "Profession Added!", profession));
    }

    /**
     * This method checks if a profession exists with the specific id
     *
     * @param professionId Unique ID of the profession which has to be checked
     * @return Boolean If profession exists
     */
    public Boolean doesProfessionExist(Integer professionId) {
        Optional<Profession> professionOptional = professionRepository.findById(professionId);

        return professionOptional.isPresent();
    }
}
