package com.immimg.immimg.util;

import cz.mallat.uasparser.OnlineUpdater;
import cz.mallat.uasparser.UASparser;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @version v1.0
 * @ProjectName: springboot0110
 * @ClassName: UserAgentUtils
 * @Description: TODO(一句话描述该类的功能)
 * @Author: Nlqiong
 * @Date: 2020/1/12 17:33
 */

public class UserAgentUtils {

    public static UASparser uaSparser = null;

    static{
        try {
            uaSparser = new UASparser(OnlineUpdater.getVendoredInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
