package com.immimg.immimg.service;

import java.util.Map;

public interface LoginService {
    Map<String,Object> login(Map<String, String> map);

    Map<String,Object> sendVerifyNo(Map<String, String> map);

    Map<String,Object> loginByVerifyNo(Map<String, String> map);
}
