package com.bikkadit.ectronic_store.helper;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileResponse {

    private String message;
    private boolean success;
    private HttpStatus status;


}
