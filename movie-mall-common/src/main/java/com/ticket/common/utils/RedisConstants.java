package com.ticket.common.utils;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 2L;
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 36000L;

    public static final Long CACHE_NULL_TTL = 2L;

    public static final Long CACHE_FILM_TTL = 30L;
    public static final String CACHE_FILM_KEY = "cache:film:";

    public static final String LOCK_FILM_KEY = "lock:film:";
    public static final Long LOCK_FILM_TTL = 10L;

    public static final String LOCK_SEAT_KEY = "lock:seat:";

    public static final String USER_SIGN_KEY = "sign:";
}
