package io.vicevil4.slogging.api.filter.internal;

import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletResponse;

public class ResponseWrapper extends ContentCachingResponseWrapper {
    /**
     * Create a new ContentCachingResponseWrapper for the given servlet response.
     *
     * @param response the original servlet response
     */
    public ResponseWrapper(HttpServletResponse response) {
        super(response);
    }
}
