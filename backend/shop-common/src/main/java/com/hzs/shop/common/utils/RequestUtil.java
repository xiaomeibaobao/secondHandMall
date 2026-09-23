package com.hzs.shop.common.utils;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author 220419
 * @description
 * @date 2026/9/23
 */
public class RequestUtil {
    public static String getBaseUrl(HttpServletRequest request) {
        String scheme = request.getHeader("X-Forwarded-Proto");
        if (scheme == null) {
            scheme = request.getScheme();
        }

        String host = request.getHeader("X-Forwarded-Host");
        if (host == null) {
            host = request.getServerName();
            int port = request.getServerPort();
            if (!(("http".equals(scheme) && port == 80)
                    || ("https".equals(scheme) && port == 443))) {
                host += ":" + port;
            }
        }

        return scheme + "://" + host;
    }
}
