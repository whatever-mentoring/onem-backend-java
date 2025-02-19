package community.whatever.onembackendjava.shortenurl.mock;

import community.whatever.onembackendjava.shortenurl.component.ShortenUrlKeyGenerator;

public class StubShortenUrlKeyGenerator extends ShortenUrlKeyGenerator {

    public StubShortenUrlKeyGenerator() {
        super(null);
    }

    @Override
    public String generate() {
        return "dev-NzdzaHhtbjlNWnU";
    }
}
