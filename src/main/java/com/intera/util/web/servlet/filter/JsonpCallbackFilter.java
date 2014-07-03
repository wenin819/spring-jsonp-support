package com.intera.util.web.servlet.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonpCallbackFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(JsonpCallbackFilter.class);

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        @SuppressWarnings("unchecked")
        Map<String, String[]> parms = httpRequest.getParameterMap();

        String callback = null;
        if(parms.containsKey("callback") && null != (callback = parms.get("callback")[0])) {
            if(log.isDebugEnabled()) {
                log.debug("Wrapping response with JSONP callback '" + callback + "'");
            }
            response = new MultiOutputHttpServletResponse((HttpServletResponse) response);

            PrintWriter out = response.getWriter();
            out.print(callback + "(");
            chain.doFilter(request, response);
            out.print(");");

            response.setContentType("text/javascript;charset=UTF-8");
        } else {
            chain.doFilter(request, response);
        }
    }

    public void destroy() {}

    public static class MultiOutputHttpServletResponse extends HttpServletResponseWrapper {

        public MultiOutputHttpServletResponse(HttpServletResponse response) {
            super(response);
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return new PrintWriter(getOutputStream());
        }
    }
}
