package ci.orange.digital.center.ahazzran.webservice.filters;


import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ci.orange.digital.center.ahazzran.webservice.services.ApprenantDetailsService;
import ci.orange.digital.center.ahazzran.webservice.services.UtilisateurDetailsService;
import ci.orange.digital.center.ahazzran.webservice.utils.JwtUtil;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component

public class JwtRequestFilter extends OncePerRequestFilter {

    private JwtUtil jwtUtil;
    private ApprenantDetailsService apprenantDetailsService;
    private UtilisateurDetailsService utilisateurDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, ApprenantDetailsService apprenantDetailsService, UtilisateurDetailsService utilisateurDetailsService) {
                this.jwtUtil = jwtUtil;
                this.apprenantDetailsService = apprenantDetailsService;
                this.utilisateurDetailsService = utilisateurDetailsService;
                }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

    final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String userTypeHeader = request.getHeader("UserType");

    if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        chain.doFilter(request, response);
        return;
    }

    final String token = authorizationHeader.split(" ")[1].trim();

    if (!jwtUtil.validateToken(token)) {
    chain.doFilter(request, response);
    return;

    }

    String email = jwtUtil.extractAllClaims(token).getSubject();
    UserDetails userDetails = null;

    if (userTypeHeader != null && userTypeHeader.equals("BackOffice")) {
        userDetails = utilisateurDetailsService.loadUserByUsername(email);

    } else {
        userDetails = apprenantDetailsService.loadUserByUsername(email);
    }

    if (userDetails != null) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        userDetails, null,
        userDetails.getAuthorities());

        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);  
    }

    chain.doFilter(request, response);

    }

}