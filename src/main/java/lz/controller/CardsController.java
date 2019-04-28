package lz.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TreeSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lz.exception.CardFormatException;
import lz.model.Card;
import lz.model.CardsResponse;
import lz.model.Result;
import lz.model.SessionComponent;
import lz.service.CardMaskerService;
import lz.service.CardService;
import lz.service.CardValidatorService;
import lz.service.ServiceFactory;

@RestController
@RequestMapping(path = "api/cards")
public class CardsController {
    private final static Logger LOG = Logger.getLogger(CardsController.class.getName());

    @Autowired
    private ServiceFactory serviceFactory;

    @Autowired
    private SessionComponent sessionComponent;

    @GetMapping(path = "/", produces = "application/json")
    public CardsResponse getCards(HttpServletRequest request) {
	final String sessionId = request.getSession().getId();
	final List<Card> cards = serviceFactory.createCardService().getCards(sessionId);
	return createCardResponse(cards);
    }

    private CardsResponse createCardResponse(final List<Card> cards) {
	final CardMaskerService cardMaskerService = serviceFactory.createCardMaskerService();

	return new CardsResponse(
		cards.stream().map(cardMaskerService::maskCard).collect(Collectors.toCollection(TreeSet<Card>::new)));
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addCard(@Valid @RequestBody Card card, Errors errors, HttpServletRequest request) {
	if (errors.hasErrors()) {
	    return ResponseEntity.badRequest().body(createResult(null, errors));
	}

	final String sessionId = request.getSession().getId();
	final CardService createCardService = serviceFactory.createCardService();
	createCardService.createCard(card, sessionId);

	return ResponseEntity.ok(createResult(card, null));
    }

    private Result createResult(Card card, Errors errors) {
	if (card != null) {
	    return new Result(card, "success");
	} else if (errors != null) {
	    return new Result(null,
		    errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
	}
	return null;
    }

    @PostMapping("/upload")
    public String uploadCards(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
	    HttpServletRequest request) {
	if (file.isEmpty()) {
	    redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	    System.out.println("empty");
	    return "redirect:uploadStatus";
	}

	final CardService cardService = serviceFactory.createCardService();
	final CardValidatorService cardValidatorService = serviceFactory.createCardValidatorService();
	final FileProcessorService fileProcessorService = serviceFactory.createFileProcessorService();

	final String sessionId = request.getSession().getId();
	final CardRowMapper mapper = new CardRowMapper();
	String line;
	try (BufferedReader br = createBufferedReader(file)) {
	    while ((line = br.readLine()) != null) {
		final Card card = mapper.mapRow(line);
		cardValidatorService.validateCard(card);
		cardService.createCard(card, sessionId);
	    }

	} catch (final IOException e) {
	    throw new CardFormatException(e);
	}

	// fileProcessorService.processFile(file);

	redirectAttributes.addFlashAttribute("message",
		"You successfully uploaded '" + file.getOriginalFilename() + "'");

	return "redirect:/uploadStatus";
    }

    private BufferedReader createBufferedReader(MultipartFile file) throws IOException {
	return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {
	final CardService cardService = serviceFactory.createCardService();
	final String sessionId = request.getSession().getId();

	LOG.info("deleting data for session id: " + sessionId);

	cardService.deleteCards(sessionId);
	return "redirect:/";
    }

    private static class CardRowMapper implements RowMapper<Card> {
	@Override
	public Card mapRow(String record) {
	    final int attributeCount = Card.class.getDeclaredFields().length;
	    final String[] recordAttributes = record.split(",");
	    if (recordAttributes.length != attributeCount) {
		return null;
	    } else {
		final DateFormat format = new SimpleDateFormat("MMM-yyyy");
		try {
		    return new Card(recordAttributes[0], recordAttributes[1], format.parse(recordAttributes[2]));
		} catch (final ParseException e) {
		    throw new CardFormatException("invalid date");
		}
	    }
	}
    }
}
