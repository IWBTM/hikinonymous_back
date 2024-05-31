package org.hikinonymous.back.core.utils;

import org.modelmapper.ModelMapper;

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
    public static Object bindToObjectFromObjObject(Object object, Class classType) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(object, classType);
    }

}
