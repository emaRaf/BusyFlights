package lz.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {

    public CardService createCardService() {
	return new CardService();
    }

    public CardMaskerService createCardMaskerService() {
	return new CardMaskerService();
    }
}
