package community.whatever.onembackendjava.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import community.whatever.onembackendjava.exception.BusinessExceptionCode;
import community.whatever.onembackendjava.exception.BusinessLogicException;
import community.whatever.onembackendjava.repository.URLShortenRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ShortenURLService {

	private final URLShortenRepository repository;

	private final Random random = new Random();

	public String getOriginURL(String shortenedURL) {
		String originURL = repository.findByShortenedURL(shortenedURL)
			.orElseThrow(() -> new BusinessLogicException(BusinessExceptionCode.ORIGIN_URL_NOT_FOUND));

		return originURL;
	}

	public String createShortenedURL(String originURL) {
		String generatedShortenedURL = generateShortenedURL();
		String shortenedURL = repository.create(originURL, generatedShortenedURL);

		return shortenedURL;
	}

	private String generateShortenedURL() {
		return String.valueOf(random.nextInt());
	}

}
