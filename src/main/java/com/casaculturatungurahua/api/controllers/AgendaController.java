package com.casaculturatungurahua.api.controllers;

import com.casaculturatungurahua.api.entities.Agenda;
import com.casaculturatungurahua.api.services.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/agenda")
public class AgendaController {

    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping
    public ResponseEntity<List<Agenda>> findAll(){
        return ResponseEntity.ok(agendaService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Agenda> findById(@PathVariable final Long id){
        return ResponseEntity.ok(agendaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Agenda> save(@RequestBody final Agenda agenda){
        return ResponseEntity.ok(agendaService.save(agenda));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Agenda> update(@PathVariable final Long id,@RequestBody final Agenda agenda){
        return ResponseEntity.ok(agendaService.update(id,agenda));
    }

    @DeleteMapping()
    public ResponseEntity<Map<String, String>> delete(@RequestBody final List<Agenda> agendaList){
        agendaService.delete(agendaList);
        return ResponseEntity.ok(new HashMap<>() {{
            put("message", "Actividades eliminadas");
        }});
    }

}
