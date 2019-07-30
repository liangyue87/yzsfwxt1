package com.ljx.hfgsjt.workflow.utils;

import java.util.Map;

public class ThreadLocalContext {

    public static ThreadLocal<Map<String,String>> threadLocal = new ThreadLocal<>();

}
