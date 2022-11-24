package io.vicevil4.slogging.api.filter;

import io.vicevil4.slogging.api.filter.internal.RequestWrapper;
import io.vicevil4.slogging.api.filter.internal.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    private final static List<MediaType> LOGGING_MEDIA_TYPES = Arrays.asList(
            MediaType.valueOf("text/*")
            , MediaType.APPLICATION_FORM_URLENCODED
            , MediaType.APPLICATION_JSON
            , MediaType.APPLICATION_XML
            , MediaType.valueOf("application/*+json")
            , MediaType.valueOf("application/*+xml")
            , MediaType.MULTIPART_FORM_DATA
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        MDC.put("traceId", UUID.randomUUID().toString());

        if (isAsyncDispatch(request)) {
            filterChain.doFilter(request, response);
        } else {
            doFilterWrapped(new RequestWrapper(request), new ResponseWrapper(response), filterChain);
        }

        MDC.clear();
    }

    private void doFilterWrapped(RequestWrapper requestWrapper, ResponseWrapper responseWrapper, FilterChain filterChain) throws IOException, ServletException {

        try {
            loggingRequest(requestWrapper);
            filterChain.doFilter(requestWrapper, responseWrapper);
        } finally {
            loggingResponse(responseWrapper);
            responseWrapper.copyBodyToResponse();
        }
    }

    private void loggingRequest(RequestWrapper requestWrapper) throws IOException {

        String queryString = requestWrapper.getQueryString();
        String headerString = getHeaders(requestWrapper);
        log.info("Request {} {} {} Header: {}",
                requestWrapper.getMethod()
                , null == queryString ? requestWrapper.getRequestURI() : requestWrapper.getRequestURI() + queryString
                , requestWrapper.getProtocol()
                , headerString
        );

        loggingPayload("Request", requestWrapper.getContentType(), requestWrapper.getInputStream());
    }

    private String getHeaders(RequestWrapper requestWrapper) {
        Enumeration<String> headerNames = requestWrapper.getHeaderNames();
        if (null != headerNames) {
            StringBuilder sb = new StringBuilder();
            while (headerNames.hasMoreElements()) {
                String key = headerNames.nextElement();
                sb.append(key).append("=").append(requestWrapper.getHeader(key)).append("; ");
            }
            return sb.toString();
        }
        return "";
    }

    private void loggingResponse(ResponseWrapper responseWrapper) throws IOException {

        loggingPayload("Response", responseWrapper.getContentType(), responseWrapper.getContentInputStream());
    }

    private void loggingPayload(String prefix, String contentType, InputStream contentInputStream) throws IOException {

        boolean available = LOGGING_MEDIA_TYPES.stream()
                .anyMatch(type -> type.includes(MediaType.valueOf(null == contentType ? "application/json" : contentType)));
        if (available) {
            byte[] content = StreamUtils.copyToByteArray(contentInputStream);
            if (content.length > 0) {
                String contentString = new String(content);
                log.info("{} Payload={}", prefix, contentString);
            }
        } else {
            log.info("{} Payload is Binary Content.", prefix);
        }
    }

}
