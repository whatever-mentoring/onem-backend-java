package community.whatever.onembackendjava;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;

@WebFilter("/*")
@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final String REQUEST_URI_PREFIX = "request uri: ";
    private static final String REQUEST_METHOD_PREFIX = "request method: ";
    private static final String REQUEST_BODY_PREFIX = "request body: ";
    private static final String LINE_SEPARATOR = System.lineSeparator();
    private static final String EMPTY_SIGN = "Empty";

    private final Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    protected void doFilterInternal(
            @NonNull final HttpServletRequest request,
            @NonNull final HttpServletResponse response,
            @NonNull final FilterChain filterChain
    ) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        try {
            filterChain.doFilter(requestWrapper, responseWrapper);
        } catch (Exception e) {
            logger.error(buildRequestLog(requestWrapper));
            throw e;
        } finally {
            responseWrapper.copyBodyToResponse();
        }
    }

    private String buildRequestLog(final ContentCachingRequestWrapper requestWrapper) throws IOException {
        Appendable sb = new StringBuffer();
        sb.append(LINE_SEPARATOR);
        sb.append(REQUEST_URI_PREFIX).append(requestWrapper.getRequestURI()).append(LINE_SEPARATOR);
        sb.append(REQUEST_METHOD_PREFIX).append(requestWrapper.getMethod()).append(LINE_SEPARATOR);
        sb.append(REQUEST_BODY_PREFIX).append(getRequestBody(requestWrapper));

        return sb.toString();
    }

    private String getRequestBody(final ContentCachingRequestWrapper requestWrapper) {
        String requestBody = requestWrapper.getContentAsString();
        return ObjectUtils.isEmpty(requestBody) ? EMPTY_SIGN : requestBody;
    }

}
