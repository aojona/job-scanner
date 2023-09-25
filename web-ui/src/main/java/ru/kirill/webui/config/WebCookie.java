package ru.kirill.webui.config;

import jakarta.servlet.http.Cookie;

public class WebCookie extends Cookie {

    public WebCookie(String name, String path) {
        super(name, null);
        this.setPath(path);
        this.setMaxAge(0);
    }
}
