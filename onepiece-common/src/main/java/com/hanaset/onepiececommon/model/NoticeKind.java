package com.hanaset.onepiececommon.model;

public enum NoticeKind {
    NOTICE("NOTICE"),
    EVENT("EVENT");

    String kind;

    NoticeKind(String kind) {
        this.kind = kind;
    }

    public String getKind() {
        return kind;
    }
}
