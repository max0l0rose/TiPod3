package com.osdb.octipod.jwt;

import com.osdb.octipod.model.RoleEnum;
import io.jsonwebtoken.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtTokenUtils {
    @Value("${jwt.token.secret}")
    String secret;

    @Value("${jwt.token.expired}")
    long validityInMilliseconds;


    //final UserDetailsService userDetailsService;


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }


    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
    }

    public String createToken(String username, List<RoleEnum> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", getRoleNames(roles));

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


//    public Authentication getAuthentication(String token) {
//        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
//    }


    public String getUsername(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }


    public String resolveToken(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, HttpHeaders.AUTHORIZATION);
        if (cookie == null)
            return null;
        String bearerToken = cookie.getValue();
        //String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }



//    //@CookieValue("foo") String fooCookie
//    public boolean validateToken(String token) //throws JwtAuthenticationException //JwtException, IllegalArgumentException
//    {
//        //MalformedJwtException
//        try {
//            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
//
//            if (claims.getBody().getExpiration().before(new Date()))
//                return false;
//
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            //throw new JwtAuthenticationException("JWT token is expired or invalid");
//            //throw new ResponseStatusException(HttpStatus.NOT_FOUND, "token error", e);
            //e.printStackTrace();
//        }
//        return false;
//    }


    private List<String> getRoleNames(List<RoleEnum> userRoles) {
        List<String> result = new ArrayList<>();

        if (userRoles == null)
            return result;

        userRoles.forEach(role -> {
            result.add(role.name()); //.getName()
        });

        return result;
    }


}


