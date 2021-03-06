package com.trick.web.core.freemarker.tags;

import java.util.HashMap;
import java.util.Map;

import com.trick.web.common.utils.Log.LoggerUtils;
import com.trick.web.common.utils.SpringContextUtil;
import com.trick.web.core.statics.Constant;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * Freemarker 自定义标签 API公共入口
 * @version 1.0,2016年6月2日 <br/>
 * 
 */
public class APITemplateModel extends WYFTemplateModel {

	@Override
	@SuppressWarnings({  "unchecked" })
	protected Map<String, TemplateModel> putValue(Map params)
			throws TemplateModelException {
		
		Map<String, TemplateModel> paramWrap = null ;
		if(null != params && params.size() != 0 || null != params.get(Constant.TARGET)){
			String name =  params.get(Constant.TARGET).toString() ;
			paramWrap = new HashMap<String, TemplateModel>(params);
			
			/**
			 * 获取子类，用父类接收，
			 */
			SuperCustomTag tag =  SpringContextUtil.getBean(name,SuperCustomTag.class);
			//父类调用子类方法
			Object result = tag.result(params);
			
			//输出
			paramWrap.put(Constant.OUT_TAG_NAME, new DefaultObjectWrapper(Configuration.VERSION_2_3_23).wrap(result));
		}else{
			LoggerUtils.error(getClass(), "Cannot be null, must include a 'name' attribute!");
		}
		return paramWrap;
	}

	
	
	
	
	
}
