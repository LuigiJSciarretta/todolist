package br.com.luigisciarretta.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.luigisciarretta.todolist.user.UserModel;
import br.com.luigisciarretta.todolist.user.UserRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class FilterTaskAuth extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.startsWith("/tasks/")){
            var authorization = request.getHeader("Authorization");

            var authEnconded = authorization.substring("Basic".length()).trim();

            byte[] authDecode = Base64.getDecoder().decode(authEnconded);

            var authString = new String(authDecode);
            String[] credientials = authString.split(":");
            String username = credientials[0];
            String password = credientials[1];

            //Validar usuário
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                //Validar senha
                var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.password);
                if (passwordVerify.verified) {
                    //Enviando Id do usuario para o controller
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
            }
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
