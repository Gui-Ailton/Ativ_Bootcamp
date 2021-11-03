package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptionns.ResourceNotFound;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	public EventDTO update(Long id, EventDTO dto){
		
		try {
			Event event = repository.getOne(id);
			event.setName(dto.getName());
			event.setUrl(dto.getUrl());
			event.setDate(dto.getDate());
			event.setCity(new City(null,"Minshs"));
			event= repository.save(event);
			return new EventDTO(event);
			
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFound("Not found");
		}
	}
}
