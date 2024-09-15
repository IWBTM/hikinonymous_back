package org.hikinonymous.back.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.dto.MemberDto;

public class SecurityUtil {

    public static ManagerDto getCurrentManager(HttpServletRequest request) {
        return (ManagerDto) request.getSession().getAttribute("manager");
    }

    public static MemberDto getCurrentMember(HttpServletRequest request) {
        return (MemberDto) request.getSession().getAttribute("member");
    }

}
