package org.example.filters;


import org.example.model.Role;
import org.example.util.ServletUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter extends HttpFilter {
    List<String> allowedFast;
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request;
        Map<Role, List<String>> map = ServletUtils.getRolesMapping();
        System.out.println(map.size());
        chain.doFilter(req, res);
    }
}
