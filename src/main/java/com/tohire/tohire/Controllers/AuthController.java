package com.tohire.tohire.Controllers;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonObject;
import com.tohire.tohire.Models.AuthRequest;
import com.tohire.tohire.Models.AuthResponse;
import com.tohire.tohire.Models.User;
import com.tohire.tohire.Repositories.UserRepository;
import com.tohire.tohire.Util.JwtUtil;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import antlr.collections.List;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${linkedin.client.id}")
    private String client_id;

    @Value("${linkedin.client.secret}")
    private String client_secret;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest){
        System.out.println(authRequest.getUsername()+" "+authRequest.getPassword());
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody AuthRequest regRequest){
        System.out.println(regRequest.getUsername()+" "+regRequest.getPassword());
        try {
            userRepository.save(new User(regRequest.getUsername(),passwordEncoder.encode(regRequest.getPassword())));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return signIn(regRequest);
    }

    @GetMapping("/code")
    public String code(@RequestParam(name = "code") String code){
        String url = "https://www.linkedin.com/oauth/v2/accessToken?grant_type=authorization_code&code="+code+"&redirect_uri=http://localhost:8080/api/auth/code&client_id="+client_id+"&client_secret="+client_secret;
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> params = new ArrayList<>();
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        CloseableHttpResponse closeableHttpResponse=null;
        try {
            closeableHttpResponse = closeableHttpClient.execute(httpPost);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {

            
            JSONObject jsonObj = new JSONObject(EntityUtils.toString(closeableHttpResponse.getEntity()));
            System.out.println("jsonObj: "+jsonObj);
            String token = (String) jsonObj.get("access_token");
            System.out.println("Token: "+token);
            //return new RedirectView("http://localhost:8081/dashboard/project?token="+token);

            url = "https://api.linkedin.com/v2/me";
            httpPost = new HttpPost(url);
            httpPost.addHeader("Authorization","Bearer "+token);
            System.out.println("All/headers: "+httpPost.getAllHeaders().toString());
            System.out.println("httppost:/ "+httpPost.toString());
            // params = new ArrayList<>();
            // params.add(new BasicNameValuePair("Authorization","Bearer "+token));
            //httpPost.setEntity(new UrlEncodedFormEntity(params));
            closeableHttpResponse = closeableHttpClient.execute(httpPost);
            jsonObj = new JSONObject(EntityUtils.toString(closeableHttpResponse.getEntity()));
            System.out.println("jsonObj: "+jsonObj);
            return "request successful";




		} catch (ParseException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

    }

}
