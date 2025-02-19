package community.whatever.onembackendjava.shortenurl.mock;


import community.whatever.onembackendjava.shortenurl.component.ShortenUrlValidator;

public class StubShortenUrlValidator extends ShortenUrlValidator {

    public StubShortenUrlValidator() {
        super(null);
    }

    @Override
    public void validate(String url) {
    }
}
