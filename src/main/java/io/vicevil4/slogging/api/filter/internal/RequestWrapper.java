package io.vicevil4.slogging.api.filter.internal;

import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;

public class RequestWrapper extends ContentCachingRequestWrapper {

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }
}
