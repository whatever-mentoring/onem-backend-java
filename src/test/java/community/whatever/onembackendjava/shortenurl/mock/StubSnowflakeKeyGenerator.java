package community.whatever.onembackendjava.shortenurl.mock;

import community.whatever.onembackendjava.shortenurl.component.SnowflakeKeyGenerator;

public class StubSnowflakeKeyGenerator extends SnowflakeKeyGenerator {

    public StubSnowflakeKeyGenerator() {
        super(null);
    }

    @Override
    public String generate() {
        return "dev-NzdzaHhtbjlNWnU";
    }
}
