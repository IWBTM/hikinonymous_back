package org.hikinonymous.back.cms.interceptor;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.hikinonymous.back.core.service.ManagerService;
import org.hikinonymous.back.core.utils.CommonUtil;
import org.hikinonymous.back.core.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CmsInterceptor implements HandlerInterceptor {

    private final ManagerService managerService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isBlank(authorization)) throw new AuthenticationException();

        String token = authorization.substring(authorization.indexOf("Bearer") + 6);
        long seq = JwtUtil.decJwt(token.trim());
        if (seq == 0) throw new AuthenticationException();

        ManagerEntity managerEntity = managerService.findByManagerSeq(seq);
        if (Objects.isNull(managerEntity)) throw new AuthenticationException();

        ManagerDto manager = (ManagerDto) CommonUtil.bindToObjectFromObjObject(managerEntity, ManagerDto.class);
        request.setAttribute("manager", manager);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
