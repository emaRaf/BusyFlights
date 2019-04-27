package lz.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceFactory {

    public CardService createCardService() {
	System.out.print("creating card serrvice");
	return new CardService();
    }
}
