package io.vicevil4.slogging.api.filter;

import io.vicevil4.slogging.api.filter.internal.RequestWrapper;
import io.vicevil4.slogging.api.filter.internal.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

@Order(0)
@Component
@Slf4j
public class LoggingFilter extends OncePerRequestFilter {

    private final static List<MediaType> LOGGING_MEDIA_TYPES = Arrays.asList(
            MediaType.APPLICATION_FORM_URLENCODED
            , MediaType.APPLICATION_JSON
//            , MediaType.APPLICATION_XML
//            , MediaType.valueOf("text/*")
//            , MediaType.valueOf("application/*+json")
//            , MediaType.valueOf("application/*+xml")
//            , MediaType.MULTIPART_FORM_DATA
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        MDC.put("traceId", UUID.randomUUID().toString());

        RequestWrapper requestWrapper = new RequestWrapper(request);
        ResponseWrapper responseWrapper = new ResponseWrapper(response);

        loggingRequest(requestWrapper);

        filterChain.doFilter(requestWrapper, responseWrapper);

        loggingResponse(responseWrapper);
        responseWrapper.copyBodyToResponse();

        MDC.clear();
    }

    private void loggingRequest(RequestWrapper requestWrapper) {

        String queryString = requestWrapper.getQueryString();
        String headerString = getHeaders(requestWrapper);
        log.info("Request {} {}{} {} Header: {}",
                requestWrapper.getMethod()
                , requestWrapper.getRequestURI()
                , null != queryString ? "?" + queryString : ""
                , requestWrapper.getProtocol()
                , headerString
        );

        if (isLoggingAvailable(requestWrapper.getContentType())) {
            byte[] content = requestWrapper.getContentAsByteArray();
            if (content.length > 0) {
                String contentString = new String(content);
                log.info("Request Payload={}", contentString);
            }
        } else {
            log.debug("Request Payload is Binary Content.");
        }
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
        if (isLoggingAvailable(responseWrapper.getContentType())) {
            byte[] content = StreamUtils.copyToByteArray(responseWrapper.getContentInputStream());
            if (content.length > 0) {
                String contentString = new String(content);
                log.info("Response Payload={}", contentString);
            }
        } else {
            log.debug("Response Payload is Binary Content.");
        }
    }

    private boolean isLoggingAvailable(String contentType) {
        return LOGGING_MEDIA_TYPES.stream()
                .anyMatch(type -> type.includes(MediaType.valueOf(null == contentType ? "application/json" : contentType)));
    }

}
