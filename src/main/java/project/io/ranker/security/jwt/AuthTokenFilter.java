package project.io.ranker.security.jwt;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.Implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import project.io.ranker.service.UserDetailsServiceimpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.ObjectUtils.isEmpty;

@Slf4j
public class AuthTokenFilter extends OncePerRequestFilter {
    // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/filter/OncePerRequestFilter.html

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceimpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {

            // get header from request ...
            final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (isEmpty(header) || !header.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            // get token from header ...
            final String token = header.split(" ")[1].trim();

            if (jwtUtils.validateJwtToken(token)) {
                // get username from the token ...
                String username = jwtUtils.getUsername(token);

                // find username + password against repository/database ...
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // use in built class for simple presentation of username and password...
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );

                // WebAuthenticationDetailsSource - Builds the details object from a request object, creating a WebAuthenticationDetails.
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // set details to security context - this stores the details of the currently authenticated user.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception e) {
            log.error("Cannot set user authentication: {}", e);
        }
        filterChain.doFilter(request, response);
    }
}
