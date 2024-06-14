package PochingLao.Configuration;

import PochingLao.Configuration.UserAuthProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;


import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header != null) {
            String[] authElements = header.split(" ");

            if (authElements.length == 2 && BEARER_PREFIX.equals(authElements[0])) {
                try {

                    Authentication authentication = "GET".equals(request.getMethod()) ?
                            userAuthProvider.validateToken(authElements[1]) :
                            userAuthProvider.validateTokenStrongly(authElements[1]);


                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } catch (RuntimeException e) {

                    SecurityContextHolder.clearContext();
                    throw e;
                }
            }
        }


        filterChain.doFilter(request, response);
    }
}
