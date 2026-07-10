package com.boos.backend.Security;

import com.fasterxml.jackson.annotation.JsonProperty;

//Clase 3
public record JwtResponse (@JsonProperty(value = "access_token") String accessToken) {
}
