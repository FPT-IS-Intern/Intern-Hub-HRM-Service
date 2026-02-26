package com.fis.hrmservice.api.util;

import jakarta.servlet.http.HttpServletRequest;

/** Utility class for common HTTP request operations. */
public class WebUtils {

  /**
   * Extract client IP address from request headers. Checks multiple headers in order of priority to
   * handle various proxy configurations.
   */
  public static String getClientIpAddress(HttpServletRequest request) {
    String ip = request.getHeader("X-Forwarded-For");

    if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
      ip = request.getRemoteAddr();
    }

    // Handle multiple IPs in X-Forwarded-For
    if (ip != null && ip.contains(",")) {
      ip = ip.split(",")[0].trim();
    }

    return ip;
  }
}
