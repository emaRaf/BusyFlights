package lz.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lz.exception.CardException;
import lz.model.Card;
import lz.model.CardsResponse;
import lz.model.Result;
import lz.model.SessionComponent;
import lz.service.CardMaskerService;
import lz.service.CardService;
import lz.service.ServiceFactory;

@RestController
@RequestMapping(path = "api/cards")
public class CardsController {

    @Autowired
    private ServiceFactory serviceFactory;

    @Autowired
    private SessionComponent sessionComponent;

    @GetMapping(path = "/", produces = "application/json")
    public CardsResponse getEmployees(HttpServletRequest request) {

	checkSession(request);

	final String sessionId = request.getSession().getId();
	final List<Card> cards = serviceFactory.createCardService().getCards(sessionId);
	return createCardResponse(cards);
    }

    private void checkSession(HttpServletRequest request) {

	List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
	System.out.println("session id " + request.getSession().getId());
	if (messages == null) {
	    messages = new ArrayList<>();
	    request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);
	}
	messages.add("new");
	request.getSession().setAttribute("MY_SESSION_MESSAGES", messages);

	System.out.println("session messages");
	messages.forEach(System.out::println);

	Integer sessionI = sessionComponent.getI();
	if (sessionI == null) {
	    sessionComponent.i = 0;
	} else {
	    sessionComponent.i = sessionI++;
	}

	System.out.println("session countuer " + sessionComponent.getI());
    }

    private CardsResponse createCardResponse(final List<Card> cards) {
	final CardMaskerService cardMaskerService = serviceFactory.createCardMaskerService();

	return new CardsResponse(
		cards.stream().map(cardMaskerService::maskCard).collect(Collectors.toCollection(TreeSet<Card>::new)));
    }

    @PostMapping(path = "/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addEmployee(@Valid @RequestBody Card card, Errors errors, HttpServletRequest request) {

	checkSession(request);

	final Result result = new Result();
	if (errors.hasErrors()) {
	    result.setMessage(
		    errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));

	    return ResponseEntity.badRequest().body(result);
	}

	if (true != true) {
	    throw new CardException("testing exception");
	}

	final String sessionId = request.getSession().getId();
	final CardService createCardService = serviceFactory.createCardService();
	createCardService.createCard(card, sessionId);
	// cardDao.addCard(card);

	result.setMessage("success");

	result.setResult(card);

	final URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
		.buildAndExpand(card.getCardNumber()).toUri();

	return ResponseEntity.ok(result);
	// return ResponseEntity.created(location).build();
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes,
	    HttpServletRequest request) {

	checkSession(request);

	if (file.isEmpty()) {
	    redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	    System.out.println("empty");
	    return "redirect:uploadStatus";
	}

	final CardService cardService = serviceFactory.createCardService();
	final String sessionId = request.getSession().getId();
	try (BufferedReader br = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())))) {
	    String line;
	    final CardRowMapper mapper = new CardRowMapper();
	    while ((line = br.readLine()) != null) {
		final Card card = mapper.mapRow(line);

		cardService.createCard(card, sessionId);
	    }

	    // Get the file and save it somewhere
//	    final byte[] bytes = file.getBytes();
//	    System.out.println("got size " + bytes.length);
	    // Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	    // Files.write(path, bytes);

	    redirectAttributes.addFlashAttribute("message",
		    "You successfully uploaded '" + file.getOriginalFilename() + "'");

	} catch (final IOException e) {
	    e.printStackTrace();
	}

	return "redirect:/uploadStatus";
    }

    @PostMapping("/destroy")
    public String destroySession(HttpServletRequest request) {

	checkSession(request);

	System.out.println("clearing session");
	System.out.println("session id " + request.getSession().getId());

	final CardService cardService = serviceFactory.createCardService();
	final String sessionId = request.getSession().getId();
	cardService.deleteCards(sessionId);

	final List<String> messages = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");
	messages.forEach(System.out::println);
	request.getSession().invalidate();
	System.out.println("cleeared session");
	System.out.println("session id " + request.getSession().getId());
	final List<String> messagesAfter = (List<String>) request.getSession().getAttribute("MY_SESSION_MESSAGES");

	// messagesAfter.forEach(System.out::println);

	return "redirect:/";
    }

    /*
     * public void readCsv() { final String csvFile =
     * "/Users/mkyong/csv/country.csv"; String line = ""; final String cvsSplitBy =
     * ",";
     *
     * // try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
     * try (BufferedReader br = new BufferedReader(new
     * ByteArrayInputStream(content))) {
     *
     *
     * new ByteArrayInputStream(content) while ((line = br.readLine()) != null) {
     *
     * // use comma as separator final String[] country = line.split(cvsSplitBy);
     *
     * System.out.println("Country [code= " + country[4] + " , name=" + country[5] +
     * "]");
     *
     * }
     *
     * } catch (final IOException e) { e.printStackTrace(); }
     *
     * }
     */
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
		    throw new CardException("invalid date");
		}
	    }
	}
    }

}
