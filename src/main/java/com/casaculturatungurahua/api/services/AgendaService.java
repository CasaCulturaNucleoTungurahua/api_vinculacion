package com.casaculturatungurahua.api.services;

import com.casaculturatungurahua.api.entities.Agenda;
import com.casaculturatungurahua.api.repository.AgendaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AgendaService {
    private final AgendaRepository agendaRepository;

    public AgendaService(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    public Agenda save(Agenda agenda){
        return this.agendaRepository.save(agenda);
    }

    public Agenda update(Long id, Agenda agenda){
        Agenda agendaToUpdate = agendaRepository.findById(id).orElseThrow(()-> new RuntimeException("La actividad no existe"));
        agendaToUpdate.setTitle(agenda.getTitle());
        agendaToUpdate.setImageURL(agenda.getImageURL());
        return agendaRepository.save(agendaToUpdate);
    }

    public List<Agenda> findAll(){
        return agendaRepository.findAll();
    }

    public Agenda findById(Long id){
        return agendaRepository.findById(id).orElseThrow(()-> new RuntimeException("La actividad no existe"));
    }

    public void delete(List<Agenda> agendaList){
        agendaRepository.deleteAll(agendaList);
    }
}
