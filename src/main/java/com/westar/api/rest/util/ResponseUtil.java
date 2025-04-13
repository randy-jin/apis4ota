package com.westar.api.rest.util;

import com.westar.api.rest.exceptions.EntityNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface ResponseUtil {
    static <X> ResponseEntity<X> wrapOrNotFound(Optional<X> maybeResponse, String entity, Long id) {
        return maybeResponse.map((response) -> {
            return ResponseEntity.ok().body(response);
        }).orElseThrow(() -> new EntityNotFoundException(entity, id));
    }
}
