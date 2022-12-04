package io.vicevil4.slogging.api.filter;

import io.vicevil4.slogging.api.filter.internal.RequestWrapper;
import io.vicevil4.slogging.api.filter.internal.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

        MediaType requestContentType = getRequestContentType(requestWrapper);

        final String requestInfo = getRequestInfo(requestWrapper);

        // 요청정보의 컨텐츠 타입이 FORM 타입인 경우 캐싱이 가능하므로 전체요청정보를 선행에서 로깅한다.
        if (MediaType.APPLICATION_FORM_URLENCODED.includes(requestContentType)) {
            log.info("Request {} Payload={}", requestInfo, getRequestFormParam(requestWrapper));
        }

        filterChain.doFilter(requestWrapper, responseWrapper);

        // FORM 타입이 아닌 경우는 전체요청정보를 후행에서 로깅한다.
        if (!MediaType.APPLICATION_FORM_URLENCODED.includes(requestContentType)) {
            log.info("Request {} Payload={}", requestInfo, getRequestBodyParam(requestWrapper));
        }
        loggingResponse(responseWrapper);
        responseWrapper.copyBodyToResponse();

        MDC.clear();
    }

    private MediaType getRequestContentType(RequestWrapper requestWrapper) {
        if (null == requestWrapper.getContentType()) return MediaType.APPLICATION_FORM_URLENCODED;
        try {
            MediaType mediaType = MediaType.valueOf(requestWrapper.getContentType());
            return mediaType;
        } catch (InvalidMediaTypeException e) {
            log.debug("Request ContentType is invalid {}", e.getMessage());
            return MediaType.APPLICATION_FORM_URLENCODED;
        }
    }

    private String getRequestInfo(RequestWrapper requestWrapper) {

        final StringBuilder sb = new StringBuilder();
        final String queryString = requestWrapper.getQueryString();
        String headerString = getHeaders(requestWrapper);
        sb.append(requestWrapper.getMethod())
                .append(" ")
                .append(requestWrapper.getRequestURI())
                .append(null != queryString ? "?" + queryString : "")
                .append(" ")
                .append(requestWrapper.getProtocol())
                .append(" ")
                .append(headerString);
        return sb.toString();
    }

    private String getRequestFormParam(RequestWrapper requestWrapper) throws IOException {
        final StringBuilder sb = new StringBuilder();
        final Enumeration<?> params = requestWrapper.getParameterNames();
        while (params.hasMoreElements()) {
            final String name = (String) params.nextElement();
            if (null != name) {
                sb.append(name).append("=").append(requestWrapper.getParameter(name));
            }
            if (params.hasMoreElements()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    private String getRequestBodyParam(RequestWrapper requestWrapper) throws IOException {

        byte[] content = requestWrapper.getContentAsByteArray();
        if (content.length > 0) {
            String contentString = new String(content, StandardCharsets.UTF_8);
            return contentString;
        }
        return "";
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
