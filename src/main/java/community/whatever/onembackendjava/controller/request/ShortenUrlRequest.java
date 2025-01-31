package community.whatever.onembackendjava.controller.request;

public class ShortenUrlRequest {
    private String originUrl;

    public ShortenUrlRequest(String originUrl) {
        this.originUrl = originUrl;
    }

    public String getOriginUrl() {
        return originUrl;
    }

}
