package ru.kirill.webui.config;

import jakarta.servlet.http.Cookie;

public class WebCookie extends Cookie {

    public WebCookie(String name, String value, String path, int expiry) {
        super(name, value);
        this.setPath(path);
        this.setMaxAge(expiry);
    }
}
