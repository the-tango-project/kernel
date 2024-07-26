package org.apeiron.kernel.web.util;

import java.net.URI;
import java.net.URISyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apeiron.kernel.service.exception.GeneralException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * Http headers utility class.
 */
@Slf4j
public final class HttpHeadersUtils {

    private HttpHeadersUtils() {
        // private for utilities
    }

    /**
     * Build page headers info.
     *
     * @param pageable
     * @param total
     * @return
     */
    public static HttpHeaders buildPageHeaders(Pageable pageable, Long total) {
        var headers = new HttpHeaders();

        if (pageable != null && total != null) {
            var pages = total / pageable.getPageSize();

            if (total % pageable.getPageSize() != 0) {
                pages++;
            }

            headers.add("X-Page", (pageable.getPageNumber() + 1) + "");
            headers.add("X-Per-Page", pageable.getPageSize() + "");
            headers.add("X-Total", total + "");
            headers.add("X-Total-Pages", pages + "");
        }

        return headers;
    }

    /**
     * Build a basic header info.
     *
     * @return
     */
    public static HttpHeaders buildSimpleJsonHeaders() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    /**
     * Build location header info.
     * @param applicationBase
     * @param subPath
     * @return
     */
    public static HttpHeaders buildLocationHeaders(String applicationBase, String subPath) {
        try {
            var uri = new StringBuilder(applicationBase).append('/').append(subPath).toString();

            var headers = new HttpHeaders();
            headers.setLocation(new URI(uri));
            headers.add("X-Location", uri);
            return headers;
        } catch (URISyntaxException ex) {
            log.debug("Build location header error", ex);
            throw new GeneralException(ex);
        }
    }

    /**
     * Build {@link URI} from given path
     * @param path
     * @return
     */
    public static URI createURI(String path) {
        try {
            return new URI(path);
        } catch (URISyntaxException ex) {
            log.debug("Create URI error", ex);
            throw new GeneralException(ex);
        }
    }
}
