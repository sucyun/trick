package com.trick.web.core.generator.plugins;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreeMarkerUtil {

	private static final String PROJECT_PATH = System.getProperty("user.dir");
	/**
	 * 获取模板
	 * @param name
	 * @return
	 */
	public Template getTemplate(String name) {
		try {
			// String pathUrl = MBGAutogenerationerServiceImpl.path();

			Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
			cfg.setDefaultEncoding("UTF-8");
			cfg.setDirectoryForTemplateLoading(new File("/run/idea_space/trick/src/main/resources/template/"));
			return cfg.getTemplate(name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 打印应用模板的结果到控制台
	 *
	 * @param name
	 * @param root
	 */
	public void print(String name, Map<String, Object> root) {
		try {
			Template template = this.getTemplate(name);
			template.process(root, new PrintWriter(System.out));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}

	public void fPrint(String name, String viewpath, Map<String, Object> root, String outFile) {
		Writer writer = null;
		// String view = PATH.split(PROJECT_PATH)[0]+"views/";
		String view = "/run/idea_space/trick/src/main/webapp/WEB-INF/views/";
		// String view = PROJECT_PATH+"/WebContent/WEB-INF/views/";
		try {
			File file = new File(view + viewpath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			File jspFile = new File(view + viewpath + "/" + outFile);
			if(!jspFile.exists())
				jspFile.createNewFile();
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(jspFile), "UTF-8"));
			Template template = getTemplate(name);
			template.process(root, writer);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
