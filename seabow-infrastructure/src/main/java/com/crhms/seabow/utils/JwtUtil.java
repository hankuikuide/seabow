package com.crhms.seabow.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.crhms.seabow.model.User;

public class JwtUtil {

    public static String getToken(User user) {
        String token= JWT.create()
                .withAudience(user.getId())
                .sign(Algorithm.HMAC256(user.getPassword()));

        return token;
    }
}
