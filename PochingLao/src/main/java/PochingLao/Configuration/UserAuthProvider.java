package PochingLao.Configuration;

import PochingLao.exceptions.AppException;
import PochingLao.entity.User;
import PochingLao.dto.UserDto;
import PochingLao.Repo.UserRepository;
import PochingLao.mappers.UserMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(UserDto dto) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3_600_000);
        return Jwts.builder()
                .setIssuer(dto.getLogin())
                .setIssuedAt(now)
                .setExpiration(validity)
                .claim("firstName", dto.getFirstName())
                .claim("lastName", dto.getLastName())
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public Authentication validateToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        UserDto user = UserDto.builder()
                .login(claims.getIssuer())
                .firstName(claims.get("firstName", String.class))
                .lastName(claims.get("lastName", String.class))
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String login = claims.getIssuer();
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(user), null, Collections.emptyList());
    }
}
