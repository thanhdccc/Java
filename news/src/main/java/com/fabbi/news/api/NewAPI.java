package com.fabbi.news.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fabbi.news.dto.NewDTO;
import com.fabbi.news.service.INewService;

@CrossOrigin
@RestController
public class NewAPI {
	
	@Autowired
	private INewService newService;
	
	@GetMapping(value = "/api/new")
	public List<NewDTO> showNew() {
		List<NewDTO> results = newService.findAll();
		return results;
	}

	@PostMapping(value = "/api/new")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public NewDTO createNew(@RequestBody NewDTO model) {
		return newService.save(model);
	}
	
	@PutMapping(value = "/api/new/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
	public NewDTO updateNew(@RequestBody NewDTO model, @PathVariable("id") long id) {
		model.setId(id);
		return newService.save(model);
	}
	
	@DeleteMapping(value = "/api/new")
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteNew(@RequestBody long[] ids) {
		newService.delete(ids);
	}
}