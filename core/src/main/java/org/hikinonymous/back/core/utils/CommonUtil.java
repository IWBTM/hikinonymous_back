package org.hikinonymous.back.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.hikinonymous.back.core.dto.CommonDto;
import org.hikinonymous.back.core.dto.ManagerDto;
import org.hikinonymous.back.core.entity.ManagerEntity;
import org.modelmapper.ModelMapper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CommonUtil {

    /**
     * 랜덤 문자열 A ~ a ~ 0
     */
    public static String getRandomStr(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        String chars[] = "A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,1,2,3,4,5,6,7,8,9,0".split(",");
        for (int i = 0; i < length; i++) sb.append(chars[random.nextInt(chars.length)]);
        return sb.toString();
    }

    /**
     * 객체간 바인딩
     */
    public static Object bindToObjectFromObject(Object object, Class classType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(object, classType);
    }

    /**
     * 총 14자리의 날짜를 입력 받아
     * 포맷 후 리턴 yyyy.MM.dd
     */
    public static String getDayByStrDate(String date) {
        if (StringUtils.isBlank(date) || date.length() < 14) return "-";
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        return year + "-" + month + '-' + day;
    }

    /**
     * 총 14자리의 날짜를 입력 받아
     * 포맷 후 리턴 yyyy.MM.dd HH:mm:ss
     */
    public static String getDayWithMinuitByStrDate(String date) {
        if (StringUtils.isBlank(date) || date.length() < 14) return "-";
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        String hour = date.substring(8, 10);
        String minuit = date.substring(10, 12);
        return year + "-" + month + '-' + day + " " + hour + ":" + minuit;
    }

    /**
     * 날짜 형식을 입력 받아
     * Date 타입 리턴
     */
    public static String getFormatDateByDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }

    /**
     * CommonDto set 등록자 정보
     */
    public static void setClientInfo(HttpServletRequest request, CommonDto commonDto, ManagerDto manager) {
        String ip = getClientIp(request);

        commonDto.setRegDate(CommonUtil.getFormatDateByDate("yyyyMMddHHmmss"));
        commonDto.setRegister(ManagerEntity.builder().managerSeq(manager.getManagerSeq()).build());
        commonDto.setRegisterIp(ip);
        commonDto.setUpdDate(CommonUtil.getFormatDateByDate("yyyyMMddHHmmss"));
        commonDto.setUpdater(ManagerEntity.builder().managerSeq(manager.getManagerSeq()).build());
        commonDto.setUpdaterIp(ip);
    }

    /**
     * Servlet Request Get IP
     */
    public static String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || ip.length() == 0) ip = request.getHeader("Proxy-Client-IP");
        if (ip == null || ip.length() == 0) ip = request.getHeader("WL-Proxy-Client-IP");
        if (ip == null || ip.length() == 0) ip = request.getRemoteAddr() ;

        return ip;
    }

}
