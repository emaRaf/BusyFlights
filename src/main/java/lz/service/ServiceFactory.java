package lz.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {

    private final CardService cardService;
    private final CardMaskerService cardMaskerService;

    public ServiceFactory() {
	cardService = new CardService();
	cardMaskerService = new CardMaskerService();
    }

    public CardService createCardService() {
	return cardService;
    }

    public CardMaskerService createCardMaskerService() {
	return cardMaskerService;
    }
}
