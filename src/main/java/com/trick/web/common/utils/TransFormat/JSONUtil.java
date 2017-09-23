package com.trick.web.common.utils.TransFormat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class JSONUtil {
	public static void main(String[] args) {
		
		
	}
	
	public void test01() {
		JSONObject json1 = new JSONObject();
		json1.put("name", 1);
		JSONObject json2 = new JSONObject();
		json2.put("name", 2);
		JSONArray array = new JSONArray();
		array.add(json1);
		array.add(json2);
		for (Object object : array) {
			JSONObject obj = (JSONObject) object;
			if(obj.get("name").equals(2)){
				
			}
		}
	}
}
