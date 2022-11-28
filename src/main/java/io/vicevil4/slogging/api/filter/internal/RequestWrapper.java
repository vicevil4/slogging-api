package io.vicevil4.slogging.api.filter.internal;

import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestWrapper extends ContentCachingRequestWrapper {

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }
}
