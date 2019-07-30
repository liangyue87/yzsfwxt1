package com.ljx.hfgsjt.util.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class DownUtil {
    private static Configuration configuration = null;
    private static HashMap<String, Template> allTemplates = null;

    static {
        configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(DownUtil.class, "/ftl/");
        System.out.println(configuration);
        /*
         * allTemplates = new HashMap<>(); // Java 7 钻石语法
         */
        allTemplates = new HashMap<String, Template>();
        try {
            allTemplates.put("resume", configuration.getTemplate("勘察结果模版.ftl"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private DownUtil() {
        throw new AssertionError();
    }

    public static File createDoc(Map<?, ?> dataMap, String type) {
        String name = "temp" + (int) (Math.random() * 100000) + ".doc";
        File f = new File(name);
        Template t = allTemplates.get(type);
        try {
            // 这个地方不能使用FileWriter因为需要指定编码类型否则生成的Word文档会因为有无法识别的编码而无法打开
            Writer w = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
            t.process(dataMap, w);
            w.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return f;
    }

}
