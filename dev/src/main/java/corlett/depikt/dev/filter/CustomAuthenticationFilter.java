package corlett.depikt.dev.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.FilterChain;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import corlett.depikt.dev.model.Member;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

//NOTE: this filter will only be called when the request is sent to the url specified by the setFilterProcessesUrl method
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
      System.out.println("init customauthenticationfilter");
      this.authenticationManager = authenticationManager;
      this.setFilterProcessesUrl("/api/v1/member/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
      System.out.println("in attemptAuthentication");
      String requestData = "";
      try {
          requestData = request.getReader().lines().collect(Collectors.joining());
      } catch (IOException e) {
        System.out.println("io exception in attemptauthentication");
        throw new AuthenticationCredentialsNotFoundException("invalid login details");
      }
      Gson gson = new Gson();
      Member member = gson.fromJson(requestData, Member.class);
      UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPassword());
      return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {     
      System.out.println("in successfulAuthentication");
      User user = (User)authentication.getPrincipal();      
      Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
      String accessToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
        .withIssuer(request.getRequestURL().toString())
        .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
        .sign(algorithm);
      String refreshToken = JWT.create()
        .withSubject(user.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
        .withIssuer(request.getRequestURL().toString())        
        .sign(algorithm);

      Map<String, String> tokens = new HashMap<>();
      tokens.put("access_token", accessToken);
      tokens.put("refresh_token", refreshToken);
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);

      System.out.println("access_token = " + accessToken);
      System.out.println("refresh_token = " + refreshToken);
      //response.setHeader("access_token", accessToken);
      //response.setHeader("refresh_token", refreshToken);
      new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }    
}