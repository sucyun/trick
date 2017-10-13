package com.trick.web.common.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 1、分别实现预处理、后处理(调用了Service并返回ModelAndView，但未进行页面渲染)、返回处理(已经渲染了页面) 
 * 2、在preHandle中，可以进行编码、安全控制等处理;
 * 3、在postHandle中，有机会修改ModelAndView; 
 * 4、在afterCompletion中，可以根据ex是否为null判断是否发生了异常，进行日志记录; 
 * 5、参数中的Object handler是下一个拦截器.
 * @author MrShuai
 *
 */
public class EventInterceptor extends HandlerInterceptorAdapter {

	private static final Logger log = Logger.getLogger(EventInterceptor.class);
	long costTime = 0;

	// Action之前执行:
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		boolean handlerOk = super.preHandle(request, response, handler);
		if (handlerOk) {
			String url = request.getRequestURL().toString();
			if (url.contains("/static/images/") || url.contains("/static/js/") || url.contains("/static/ztree/") || url.contains("/static/css/")) {

			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				log.info("------------访问此url的地址为：" + request.getRemoteAddr() + "  访问此地址的时间为：" + sdf.format(new Date()) + "-----------");
				costTime = System.currentTimeMillis();
				log.info("------------请求开始-------------");
				log.info("------------" + url + "--------------");
			}
		}
		return true;
	}

	// 生成视图之前执行
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

	}

	// 最后执行，可用于释放资源
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		boolean handlerOk = super.preHandle(request, response, handler);

		if (handlerOk) {
			String url = request.getRequestURL().toString();
			if (url.contains("/static/images/") || url.contains("/static/js/") || url.contains("/static/css/")) {

			} else {
				costTime = System.currentTimeMillis() - costTime;

				log.info("***********请求结束***********");
				log.info("***********" + url + "***********");
				log.info("******一个请求时间********" + costTime + "毫秒*************");
			}

		}
	}
}
