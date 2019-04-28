package lz.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import org.springframework.web.multipart.MultipartFile;

public class FileProcessorService {

    public void processFile(MultipartFile file) {

	final RowMapper mapper;
	final Consumer rowValidator;
	final Consumer rowConsumer;

	final String line;
	/*
	 * try (BufferedReader br = createBufferedReader(file)) { while ((line =
	 * br.readLine()) != null) { final Card card = mapper.mapRow(line);
	 * cardValidatorService.validateCard(card); cardService.createCard(card,
	 * sessionId); }
	 * 
	 * } catch (final IOException e) { throw new CardFormatException(e); }
	 */
    }

    private BufferedReader createBufferedReader(MultipartFile file) throws IOException {
	return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file.getBytes())));
    }

}
