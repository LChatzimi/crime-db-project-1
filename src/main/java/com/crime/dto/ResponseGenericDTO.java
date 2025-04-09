package com.crime.dto;

import lombok.*;

/**
 * This class is used to return a response to the http client
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@EqualsAndHashCode
public class ResponseGenericDTO {
    private String message;
    private boolean success;

}
