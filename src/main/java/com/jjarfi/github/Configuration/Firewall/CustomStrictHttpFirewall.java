package com.jjarfi.github.Configuration.Firewall;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.firewall.FirewalledRequest;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.RequestRejectedException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class CustomStrictHttpFirewall implements HttpFirewall {

    private static final Set<String> ALLOW_ANY_HTTP_METHOD = Collections.unmodifiableSet(Collections.emptySet());

    private static final String ENCODED_PERCENT = "%25";

    private static final String PERCENT = "%";

    private static final List<String> FORBIDDEN_ENCODED_PERIOD = Collections.unmodifiableList(Arrays.asList("%2e", "%2E"));

    private static final List<String> FORBIDDEN_SEMICOLON = Collections.unmodifiableList(Arrays.asList(";", "%3b", "%3B"));

    private static final List<String> FORBIDDEN_FORWARDSLASH = Collections.unmodifiableList(Arrays.asList("%2f", "%2F"));

    private static final List<String> FORBIDDEN_BACKSLASH = Collections.unmodifiableList(Arrays.asList("\\", "%5c", "%5C"));

    private Set<String> encodedUrlBlacklist = new HashSet<String>();

    private Set<String> decodedUrlBlacklist = new HashSet<String>();

    private Set<String> allowedHttpMethods = createDefaultAllowedHttpMethods();

    public CustomStrictHttpFirewall() {
        urlBlacklistsAddAll(FORBIDDEN_SEMICOLON);
        urlBlacklistsAddAll(FORBIDDEN_FORWARDSLASH);
        urlBlacklistsAddAll(FORBIDDEN_BACKSLASH);

        this.encodedUrlBlacklist.add(ENCODED_PERCENT);
        this.encodedUrlBlacklist.addAll(FORBIDDEN_ENCODED_PERIOD);
        this.decodedUrlBlacklist.add(PERCENT);
    }

    public void setUnsafeAllowAnyHttpMethod(boolean unsafeAllowAnyHttpMethod) {
        this.allowedHttpMethods = unsafeAllowAnyHttpMethod ? ALLOW_ANY_HTTP_METHOD : createDefaultAllowedHttpMethods();
    }

    public void setAllowedHttpMethods(Collection<String> allowedHttpMethods) {
        if (allowedHttpMethods == null) {
            throw new IllegalArgumentException("allowedHttpMethods cannot be null");
        }
        if (allowedHttpMethods == ALLOW_ANY_HTTP_METHOD) {
            this.allowedHttpMethods = ALLOW_ANY_HTTP_METHOD;
        } else {
            this.allowedHttpMethods = new HashSet<>(allowedHttpMethods);
        }
    }

    public void setAllowSemicolon(boolean allowSemicolon) {
        if (allowSemicolon) {
            urlBlacklistsRemoveAll(FORBIDDEN_SEMICOLON);
        } else {
            urlBlacklistsAddAll(FORBIDDEN_SEMICOLON);
        }
    }

    public void setAllowUrlEncodedSlash(boolean allowUrlEncodedSlash) {
        if (allowUrlEncodedSlash) {
            urlBlacklistsRemoveAll(FORBIDDEN_FORWARDSLASH);
        } else {
            urlBlacklistsAddAll(FORBIDDEN_FORWARDSLASH);
        }
    }

    public void setAllowUrlEncodedPeriod(boolean allowUrlEncodedPeriod) {
        if (allowUrlEncodedPeriod) {
            this.encodedUrlBlacklist.removeAll(FORBIDDEN_ENCODED_PERIOD);
        } else {
            this.encodedUrlBlacklist.addAll(FORBIDDEN_ENCODED_PERIOD);
        }
    }

    public void setAllowBackSlash(boolean allowBackSlash) {
        if (allowBackSlash) {
            urlBlacklistsRemoveAll(FORBIDDEN_BACKSLASH);
        } else {
            urlBlacklistsAddAll(FORBIDDEN_BACKSLASH);
        }
    }

    public void setAllowUrlEncodedPercent(boolean allowUrlEncodedPercent) {
        if (allowUrlEncodedPercent) {
            this.encodedUrlBlacklist.remove(ENCODED_PERCENT);
            this.decodedUrlBlacklist.remove(PERCENT);
        } else {
            this.encodedUrlBlacklist.add(ENCODED_PERCENT);
            this.decodedUrlBlacklist.add(PERCENT);
        }
    }

    private void urlBlacklistsAddAll(Collection<String> values) {
        this.encodedUrlBlacklist.addAll(values);
        this.decodedUrlBlacklist.addAll(values);
    }

    private void urlBlacklistsRemoveAll(Collection<String> values) {
        this.encodedUrlBlacklist.removeAll(values);
        this.decodedUrlBlacklist.removeAll(values);
    }

    @Override
    public FirewalledRequest getFirewalledRequest(HttpServletRequest request) throws RequestRejectedException {
        rejectForbiddenHttpMethod(request);
        rejectedBlacklistedUrls(request);

        if (!isNormalized(request)) {
            request.setAttribute("isNormalized", new RequestRejectedException("The request was rejected because the URL was not normalized."));
        }

        String requestUri = request.getRequestURI();
        if (!containsOnlyPrintableAsciiCharacters(requestUri)) {
            request.setAttribute("isNormalized",  new RequestRejectedException("The requestURI was rejected because it can only contain printable ASCII characters."));
        }
        return new FirewalledRequest(request) {
            @Override
            public void reset() {
            }
        };
    }

    private void rejectForbiddenHttpMethod(HttpServletRequest request) {
        if (this.allowedHttpMethods == ALLOW_ANY_HTTP_METHOD) {
            return;
        }
        if (!this.allowedHttpMethods.contains(request.getMethod())) {
            request.setAttribute("isNormalized",  new RequestRejectedException("The request was rejected because the HTTP method \"" +
                    request.getMethod() +
                    "\" was not included within the whitelist " +
                    this.allowedHttpMethods));
        }
    }

    private void rejectedBlacklistedUrls(HttpServletRequest request) {
        for (String forbidden : this.encodedUrlBlacklist) {
            if (encodedUrlContains(request, forbidden)) {
                request.setAttribute("isNormalized",  new RequestRejectedException("The request was rejected because the URL contained a potentially malicious String \"" + forbidden + "\""));
            }
        }
        for (String forbidden : this.decodedUrlBlacklist) {
            if (decodedUrlContains(request, forbidden)) {
                request.setAttribute("isNormalized",  new RequestRejectedException("The request was rejected because the URL contained a potentially malicious String \"" + forbidden + "\""));
            }
        }
    }

    @Override
    public HttpServletResponse getFirewalledResponse(HttpServletResponse response) {
        return new FirewalledResponse(response);
    }

    private static Set<String> createDefaultAllowedHttpMethods() {
        Set<String> result = new HashSet<>();
        result.add(HttpMethod.DELETE.name());
        result.add(HttpMethod.GET.name());
        result.add(HttpMethod.HEAD.name());
        result.add(HttpMethod.OPTIONS.name());
        result.add(HttpMethod.PATCH.name());
        result.add(HttpMethod.POST.name());
        result.add(HttpMethod.PUT.name());
        return result;
    }

    private static boolean isNormalized(HttpServletRequest request) {
        if (!isNormalized(request.getRequestURI())) {
            return false;
        }
        if (!isNormalized(request.getContextPath())) {
            return false;
        }
        if (!isNormalized(request.getServletPath())) {
            return false;
        }
        if (!isNormalized(request.getPathInfo())) {
            return false;
        }
        return true;
    }

    private static boolean encodedUrlContains(HttpServletRequest request, String value) {
        if (valueContains(request.getContextPath(), value)) {
            return true;
        }
        return valueContains(request.getRequestURI(), value);
    }

    private static boolean decodedUrlContains(HttpServletRequest request, String value) {
        if (valueContains(request.getServletPath(), value)) {
            return true;
        }
        if (valueContains(request.getPathInfo(), value)) {
            return true;
        }
        return false;
    }

    private static boolean containsOnlyPrintableAsciiCharacters(String uri) {
        int length = uri.length();
        for (int i = 0; i < length; i++) {
            char c = uri.charAt(i);
            if (c < '\u0020' || c > '\u007e') {
                return false;
            }
        }

        return true;
    }

    private static boolean valueContains(String value, String contains) {
        return value != null && value.contains(contains);
    }

    private static boolean isNormalized(String path) {
        if (path == null) {
            return true;
        }

        if (path.indexOf("//") > -1) {
            return false;
        }

        for (int j = path.length(); j > 0;) {
            int i = path.lastIndexOf('/', j - 1);
            int gap = j - i;

            if (gap == 2 && path.charAt(i + 1) == '.') {
                // ".", "/./" or "/."
                return false;
            } else if (gap == 3 && path.charAt(i + 1) == '.' && path.charAt(i + 2) == '.') {
                return false;
            }

            j = i;
        }

        return true;
    }

}
