package lz.controller;

import java.net.URI;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lz.dao.CardDAO;
import lz.exception.CardException;
import lz.model.Card;
import lz.model.Cards;
import lz.model.Result;
import lz.service.ServiceFactory;

@RestController
@RequestMapping(path = "/cards")
public class CardsController {

    @Autowired
    private CardDAO cardDao;

    @Autowired
    private ServiceFactory serviceFactory;

    @GetMapping(path = "/", produces = "application/json")
    public Cards getEmployees() {
	return cardDao.getAllEmployees();
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Card card, Errors errors) {
	final Result result = new Result();
	if (errors.hasErrors()) {
	    result.setMessage(
		    errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));

	    return ResponseEntity.badRequest().body(result);
	}

	if (true != true) {
	    throw new CardException("testing exception");
	}

	serviceFactory.createCardService();

	cardDao.addEmployee(card);

	result.setMessage("success");

	result.setResult(card);

	final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(card.getCardNumber()).toUri();

	return ResponseEntity.ok(result);
	// return ResponseEntity.created(location).build();
    }
}
