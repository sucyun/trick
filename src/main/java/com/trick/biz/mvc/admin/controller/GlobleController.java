package com.trick.biz.mvc.admin.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.trick.biz.mvc.admin.model.*;
import com.trick.biz.mvc.admin.service.AdminService;
import com.trick.biz.utils.system.VerifyCodeUtil;
import com.trick.web.common.code.BaseCode;
import com.trick.web.common.controller.BaseController;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/globle")
public class GlobleController extends BaseController {
	
	@Resource
	private AdminService adminService;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ResponseBody
	@RequestMapping("/navi")
	public String navilist(HttpServletRequest request, HttpServletResponse response){
		response.setCharacterEncoding("utf-8");
		Subject currentUser = SecurityUtils.getSubject();
		System.out.println(currentUser);
		Admin admin = adminService.selectPrivilegeByAdmName("admin");
		List<TreeVO> parentTreeVO = new ArrayList<TreeVO>();
		Map<Integer, TreeVO> map = new HashMap<Integer, TreeVO>();
		for(Role role:admin.getRole()){
			for(Privilege privilege:role.getPrivilege()){
				TreeVO tr = TreeVO.getInstance(privilege);
				TreeVO trInMap = map.get(tr.getId());
				if(trInMap != null){
					tr.setChildren(trInMap.getChildren());
				}
				map.put(tr.getId(), tr);
				if(privilege.getParentId() == null){
					parentTreeVO.add(tr);
				}else {
					TreeVO parentTr = map.get(tr.getParentId());
					if(parentTr == null){
						parentTr = new TreeVO();
						parentTr.setId(tr.getParentId());
						parentTr.setChildren(new ArrayList<TreeVO>());
						map.put(parentTr.getId(), parentTr);
					}
					parentTr.getChildren().add(tr);
				}
			}
		}
		List<Object> list = new ArrayList<Object>();
		list = (List)parentTreeVO;
		JSONArray array = new JSONArray(list);
		return array.toString();
	}
	/**
	 * Describe: 登录跳转
	 * MrShuai 2016年12月22日
	 */
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request) {
		return "globle/login/login";
	}
	
	/**
	 * 用户登录：使用shiro权限认证进行配置
	 */
	@ResponseBody
	@RequestMapping(value = "/submitlogin",method = RequestMethod.POST)
	public String submitlogin(HttpServletRequest request) {
		try {
			Pojo pojo = handleParams(request, Pojo.class);
			System.out.println(pojo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//验证码逻辑
		/*// 获取HttpSession中的验证码
		String verifyCode = (String) request.getSession().getAttribute("verifyCode");
		// 获取用户请求表单中输入的验证码
		String submitCode = WebUtils.getCleanParam(request, "verifyCode");
		System.out.println("用户[" + username + "]登录时输入的验证码为[" + submitCode + "],HttpSession中的验证码为[" + verifyCode + "]");
		if (StringUtils.isEmpty(submitCode) || !StringUtils.equals(verifyCode, submitCode.toLowerCase())) {
			request.setAttribute(BaseCode.RESULT_STATUS, "验证码不正确");
			return resultPageURL+"global/login.jsp";
		}*/
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		System.out.println("为了验证登录用户而封装的token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		// 获取当前的Subject
		Subject currentUser = SecurityUtils.getSubject();
		try {
			super.resultMap.put(BaseCode.RESULT_STATUS, -1);
			System.out.println("对用户[" + username + "]进行登录验证..验证开始");
			//login 会执行 MyRealm.doGetAuthenticationInfo()
			currentUser.login(token);
			System.out.println("对用户[" + username + "]进行登录验证..验证通过");
			super.resultMap.put(BaseCode.RESULT_STATUS, 200);
			super.resultMap.put(BaseCode.RESULT_MESSAGE, "登录成功");
			super.resultMap.put(BaseCode.RESULT_BACK_URL, request.getContextPath() + "/globle/welcome.jsp");
		} catch (UnknownAccountException uae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,未知账户");
			super.resultMap.put(BaseCode.RESULT_MESSAGE, "未知账户");
		} catch (IncorrectCredentialsException ice) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误的凭证");
			super.resultMap.put(BaseCode.RESULT_MESSAGE, "密码不正确");
		} catch (LockedAccountException lae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,账户已锁定");
			super.resultMap.put(BaseCode.RESULT_MESSAGE, "账户已锁定");
		} catch (ExcessiveAttemptsException eae) {
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,错误次数过多");
			super.resultMap.put(BaseCode.RESULT_MESSAGE, "用户名或密码错误次数过多");
		} catch (AuthenticationException ae) {
			// 通过处理Shiro的运行时AuthenticationException就可以控制用户登录失败或密码错误时的情景
			System.out.println("对用户[" + username + "]进行登录验证..验证未通过,堆栈轨迹如下");
			super.resultMap.put(BaseCode.RESULT_MESSAGE, "用户名或密码不正确");
		}
		return JSONObject.toJSONString(resultMap);
	}

	/**
	 * 用户登出：清楚用户session并调整至登录页面
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/";
	}

	/**
	 * 获取验证码图片和文本(验证码文本会保存在HttpSession中)
	 */
	@RequestMapping("/getVerifyCodeImage")
	public void getVerifyCodeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 设置页面不缓存
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		String verifyCode = VerifyCodeUtil.generateTextCode(VerifyCodeUtil.TYPE_NUM_ONLY, 4, null);
		// 将验证码放到HttpSession里面
		request.getSession().setAttribute("verifyCode", verifyCode);
		System.out.println("本次生成的验证码为[" + verifyCode + "],已存放到HttpSession中");
		// 设置输出的内容的类型为JPEG图像
		response.setContentType("image/jpeg");
		BufferedImage bufferedImage = VerifyCodeUtil.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		// 写给浏览器
		ImageIO.write(bufferedImage, "JPEG", response.getOutputStream());
	}
}
