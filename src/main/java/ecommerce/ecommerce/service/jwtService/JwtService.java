package ecommerce.ecommerce.service.jwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String SECRET;

    public String generateToken(String username){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims,username);
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        String username =extractUser(token);
        Date expirationDate = extractExpiration(token);
        return userDetails.getUsername().equals(username) && !expirationDate.before(new Date());
    }

    private String createToken(Map<String,Object> claims,String username){
        return Jwts.builder()
                .setClaims(claims)                                              // token payload kısmına ek bilgileri ekle
                .setSubject(username)                                           // tokenın sahibi belirtilen kullanıcı adı ayarlanıyor
                .setIssuedAt(new Date(System.currentTimeMillis()))              // tokenin oluşturulma tarihi belirleniyor.
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*2*20))  // Token'ın geçerlilik süresi (2 dakika) ayarlanıyor.
                .signWith(getSignKey(), SignatureAlgorithm.HS256)               // token özel bir anahtar ve hs256 algoritmasıyla imazalnıyor
                .compact();                                                     // token oluşturulup string fotmatına döndür
    }

    private Key getSignKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);                       // SECRET string'i Base64 formatından çözümleniyor.
        return Keys.hmacShaKeyFor(keyBytes);                                    // HS256 algoritması için uygun bir anahtar (Key) oluşturuluyor.
    }

    public String extractUser(String token){
        Claims claims = Jwts
                .parserBuilder()                                                // JWT'nin parçalarını çözümlemek için bir parser (ayrıştırıcı) oluşturur.
                .setSigningKey(getSignKey())                                    // Token'ı imzalayan gizli anahtar belirtilir.
                .build()                                                        // Parser oluşturulur.
                .parseClaimsJws(token)                                          // Verilen token parse edilerek claim'leri (içindeki bilgiler) çıkarır.
                .getBody();                                                     // JWT'nin body (payload) kısmını alır.
        return claims.getSubject();                                             // Body'den "subject" alanını döner.
    }
    private Date extractExpiration(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getExpiration();
    }

}
