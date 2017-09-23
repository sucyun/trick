package com.trick.web.core.freemarker.extend;

import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import com.trick.web.common.utils.LoggerUtils;
import com.trick.web.core.statics.Constant;

public class FreeMarkerViewExtend extends FreeMarkerView {

	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) {
		try {
			super.exposeHelpers(model, request);
		} catch (Exception e) {
			LoggerUtils.fmtError(FreeMarkerViewExtend.class, e, "FreeMarkerViewExtend 加载父类出现异常。请检查。");
		}
		model.put(Constant.CONTEXT_PATH, request.getContextPath());
		model.putAll(Ferrmarker.initMap);
		model.put("_time", new Date().getTime());
		model.put("NOW_YEAY", Constant.NOW_YEAY);// 今年
		model.put("_v", Constant.VERSION);// 版本号，重启的时间
		model.put("cdn", Constant.DOMAIN_CDN);// CDN域名
	}
}
