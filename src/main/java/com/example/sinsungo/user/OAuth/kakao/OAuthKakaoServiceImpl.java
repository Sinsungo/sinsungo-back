package com.example.sinsungo.user.OAuth.kakao;

import com.example.sinsungo.common.RedisUtil;
import com.example.sinsungo.jwt.JwtUtil;
import com.example.sinsungo.user.OAuth.OAuthRoleEnum;
import com.example.sinsungo.user.User;
import com.example.sinsungo.user.UserRepository;
import com.example.sinsungo.user.UserRoleEnum;
import com.example.sinsungo.user.auth.dto.TokenResponseDto;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuthKakaoServiceImpl implements OAuthKakaoService {
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.kakao.authorization-grant-type}")
    private String grantType;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String redirectUri;

    @Override
    public TokenResponseDto getKakaoAccessToken(String code, HttpServletResponse response) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";
        String accessToken = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=").append(grantType);
            sb.append("&client_id=").append(clientId); // 신선고 REST_API_KEY 입력
            sb.append("&redirect_uri=").append(redirectUri); // 신선고 인가코드 받은 redirect_uri 입력
            sb.append("&code=").append(code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonElement element = JsonParser.parseString(result);
            log.info("element :"+element);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();

            User user = createKakaoUser(access_Token);

            // Access Token 생성 및 헤더에 추가
            accessToken = jwtUtil.createToken(user.getUsername(), user.getRole());
            refresh_Token = jwtUtil.createRefreshToken(user.getUsername(), user.getRole());
            redisUtil.saveRefreshToken(user.getUsername(), refresh_Token);

            response.addHeader(JwtUtil.AUTHORIZATION_HEADER, accessToken);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new TokenResponseDto(accessToken, refresh_Token);
    }

    @Override
    public User createKakaoUser(String token) {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        //access_token을 이용하여 사용자 정보 조회
        User user = null;
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //Gson 라이브러리로 JSON파싱
            JsonElement element = JsonParser.parseString(result);

            int id = element.getAsJsonObject().get("id").getAsInt();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            if (hasEmail) {
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }

            System.out.println("id : " + id);
            System.out.println("email : " + email);

            br.close();

            user = findUserByEmail(email);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepository.findByUsername(email).orElse(null);

        if (user == null) {
            user = new User(email, null, email , OAuthRoleEnum.KAKAO, UserRoleEnum.MEMBER);
            userRepository.save(user);
        }
        return user;
    }

}