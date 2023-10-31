package com.example.sinsungo.user.OAuth;

public enum OAuthRoleEnum {
    ORIGIN(OAuth.ORIGIN),
    KAKAO(OAuth.KAKAO),

    GOOGLE(OAuth.GOOGLE);

    private final String provider;

    OAuthRoleEnum(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return this.provider;
    }

    public static class OAuth {
        public static final String ORIGIN = "ORIGIN";
        public static final String KAKAO = "KAKAO";
        public static final String GOOGLE = "GOOGLE";

    }
}
