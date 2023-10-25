package com.example.sinsungo.jwt;

import com.example.sinsungo.user.UserRoleEnum;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
@Slf4j(topic = "JWT")
public class JwtUtil {

    // Header key 값
    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER_PREFIX = "Bearer ";
    private final long TOKEN_TIME = 60 * 60 * 1000L; // 60분 (millisec)

    @Value("${jwt.secret.key}") // Base64 Encode 한 SecretKey(application.properties)
    private String secretKey;
    //secretkey 담을 객체 (jwt 암호화, 복호화에 사용)
    private Key key;
    // enum, 사용할 암호화 알고리즘
    public final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public static final String AUTHORIZATION_KEY = "auth";

    // 생성자가 호출된 이후 (객체가 만들어진 이후) 코드 실행
    @PostConstruct
    public void init(){
        // secretKey : 이미 base64로 인코딩 된 값
        // 사용하려면 디코딩
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 토큰 생성
    public String createToken(String username,  UserRoleEnum role){
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username) // 사용자 id
                        .claim(AUTHORIZATION_KEY, role)
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 만료 시간
                        .setIssuedAt(date) // 발급 시간
                        .signWith(key,signatureAlgorithm) // 키, 암호화 알고리즘
                        .compact(); // 완성
    }

    //가져왔을 때 'Bearer ' 이만큼 떼어내야함
    // JWT 토큰 substring
    public String substringToken(String tokenValue) {
        // 공백과 null이 아닌지 확인 && 'Bearer '로 시작하는지 확인
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            // 'Bearer ' 잘라내기
            return tokenValue.substring(7);
        }
        log.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) { // 변조, 파괴
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) { // 만료
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // body 부분에서 claims(데이터들의 집합) 가져오기
    // 토큰에서 사용자 정보 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        // token 이 null 또는 공백인지 체크 && 토큰이 정상적으로 Bearer 를 가지고 있는지 체크
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7); // JWT 토큰 substring
        }
        return null;
    }
}
