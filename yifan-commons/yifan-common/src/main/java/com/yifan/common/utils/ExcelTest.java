package com.yifan.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;

public class ExcelTest {

    public static void main(String[] args) {

        ExcelReader reader = ExcelUtil.getReader("D:\\凡哥凡哥.xlsx", 0);
        List<Map<String, Object>> readAll = reader.readAll();
        Map<String, Long> countMap = new HashMap<>();
        readAll.forEach(map -> map
                .forEach((k, v) -> {
                    if (countMap.containsKey(String.valueOf(v).trim())) {
                        countMap.put(String.valueOf(v).trim(), countMap.get(String.valueOf(v).trim()) + 1);

                    } else {
                        countMap.put(String.valueOf(v).trim(), 1L);
                    }
                }));
        List<Map<String, Object>> list = new ArrayList<>();
        countMap.forEach((k, v) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("姓名", k);
            map.put("次数", v);
            list.add(map);
        });
        // 通过工具类创建writer
        ExcelWriter writer = ExcelUtil.getWriter("D:\\2.xlsx");
        writer.write(list, true);
        writer.close();
    }

}
