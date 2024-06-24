package org.hikinonymous.back.cms.common.filter;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CmsFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("========== S FILTER ==========");
        filterChain.doFilter(servletRequest, servletResponse);
        logger.info("========== E FILTER ==========");
    }

}
