package com.java.shopweb.filter;

import com.java.shopweb.dao.VisitCountDao;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class VisitCountFilter implements Filter {

    private FilterConfig filterConfig;
    private String excludePrefix;
    private final VisitCountDao visitCountDao = new VisitCountDao();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.excludePrefix = filterConfig.getInitParameter("excludePrefix");
        if(this.excludePrefix == null)
            this.excludePrefix ="/assets";


        try {
            long currentCount = visitCountDao.getCount();
            filterConfig.getServletContext().setAttribute("visitCount",currentCount);
            filterConfig.getServletContext().log(
                    "[VisitCountFilter] init - excludePrefix="+ excludePrefix
                    +"| currentCount=" +currentCount);
        } catch (Exception e){
            filterConfig.getServletContext().setAttribute("visitCount",0L);
            filterConfig.getServletContext().log(
                    "[VisitCountFilter] init WARN - cannot load visitCount from DB : "+e.getMessage());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String uri = ((jakarta.servlet.http.HttpServletRequest) request).getRequestURI();

        if(uri != null && !uri.contains(excludePrefix)){
            try {
                visitCountDao.increment();
                long count = visitCountDao.getCount();
                filterConfig.getServletContext().setAttribute("visitCount",count);
            } catch (Exception e) {

            }
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        filterConfig.getServletContext().log("[VisitCountFilter] destroy");
    }
}
