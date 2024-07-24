package ci.orange.digital.center.ahazzran.webservice.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    @Value("${jwt.issuer-uri}")
    private String issuerUri;

    private String secret="private_key";



    public String generateToken(String email, Map<String, Object> additionalClaims) {
        return Jwts.builder()
                .setClaims(additionalClaims)
                .setIssuer(issuerUri)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24 heures
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}

// import java.security.SecureRandom;
// import java.util.Base64;

// public class SecretKeyGenerator {

//     public static String generateSecretKey() {
//         SecureRandom secureRandom = new SecureRandom();
//         byte[] key = new byte[32]; // 256 bits
//         secureRandom.nextBytes(key);
//         return Base64.getEncoder().encodeToString(key);
//     }

//     public static void main(String[] args) {
//         String secretKey = generateSecretKey();
//         System.out.println("Generated Secret Key: " + secretKey);
//     }


// # application.properties
// jwt.secret=${JWT_SECRET}

// }
