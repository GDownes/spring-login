package com.codeaim.twitter.login.common;

import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;

import com.codeaim.twitter.login.model.Principal;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;

public class AuthenticationUtilities
{
    public static Optional<Principal> getPrincipal(
            String principalSigningKey,
            String jsonWebToken
    )
    {
        try
        {
            Object principalBody = Jwts.parser()
                    .setSigningKey(principalSigningKey)
                    .parseClaimsJws(jsonWebToken)
                    .getBody()
                    .get("principal");

            Principal principal =
                    new ObjectMapper()
                            .convertValue(
                                    principalBody,
                                    Principal.class);

            return Optional.of(principal);
        } catch (Exception ex)
        {
            return Optional.empty();
        }
    }

    public static Optional<String[]> getBasicAuthorizationTokenParts(String basicAuthorizationToken)
    {
        byte[] decodedBytes = Base64.decodeBase64(basicAuthorizationToken.getBytes());
        String[] tokenParts = new String(decodedBytes).split(":");
        return tokenParts.length == 2 ? Optional.of(tokenParts) : Optional.<String[]>empty();
    }
}
