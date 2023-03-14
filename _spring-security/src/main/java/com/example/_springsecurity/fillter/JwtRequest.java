package com.example._springsecurity.fillter;

import com.example._springsecurity.service.security.MyUserDetailService;
import com.example._springsecurity.until.JwtUntil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class JwtRequest extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailService userDetailsService;

    @Autowired
    private JwtUntil jwtUntil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, ServletException {

        // Access to XMLHttpRequest has been blocked by CORS policy
        // Error lock URL back-end --> font-end
        // Link: https://stackoverflow.com/questions/53258297/access-to-xmlhttprequest-has-been-blocked-by-cors-policy
        // add link: https://howtodoinjava.com/java/servlets/java-cors-filter-example/
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS, HEAD");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers, Authorization");


        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } else {

            // Get value(token) from key "Authorization"
            final String authorizationHeader = request.getHeader("Authorization");

            String username = null;
            String jwt = null;

            // Set Var authorizationHeader start "Bearer " + ....<<<request(token)>>>
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                // Get value Var jwt from index 7 to end
                // Or
                // Var jwt = Var authorizationHeader (token)
                jwt = authorizationHeader.substring(7);

                // Get username from token
                username = this.jwtUntil.extractUsername(jwt);
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                if (this.jwtUntil.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
            // pass the request along the filter chain
            filterChain.doFilter(request, response);
        }
    }
}
