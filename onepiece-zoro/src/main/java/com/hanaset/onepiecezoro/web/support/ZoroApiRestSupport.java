package com.hanaset.onepiecezoro.web.support;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ZoroApiRestSupport {

    protected static <T> ResponseEntity<?> response(T data) {
        return ResponseEntity.ok(
                ImmutableMap.of(
                        "code", "0",
                        "data", data != null ? data : new JSONObject()
                )
        );
    }

    protected static <T> ResponseEntity<?> zoroException(String code, String msg) {
        return new ResponseEntity<>(
                ImmutableMap.of(
                        "code", code,
                        "msg", msg,
                        "data", "{}"
                ), HttpStatus.OK
        );
    }
}
