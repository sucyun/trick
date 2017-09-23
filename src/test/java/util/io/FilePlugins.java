package util.io;

import java.io.File;

public class FilePlugins{

	public static void main(String[] args) {
		// F://ruyx//3-系统文档//3-版本管理//1-清算系统//20170819_V1.0.2//替换文件//cpt
//		String path = "F://ruyx//3-系统文档//3-版本管理//1-清算系统//20170904_V1.0.4//CPT";
		String path = "F://ruyx//3-系统文档//3-版本管理//1-清算系统//20170904_V1.0.4//jsp";
		getFile(path);
	}

	public static void getFile(String path) {
		// get file list where the path has
		File file = new File(path);
		// get the folder list
		File[] array = file.listFiles();
		for (int i = 0; i < array.length; i++) {
			if (array[i].isFile()) {
				System.out.println(array[i].getName());
				// only take file name
//				System.out.println("^^^^^" + array[i].getName());
				// take file path and name
//				System.out.println("#####" + array[i]);
				// take file path and name
//				System.out.println("*****" + array[i].getPath());
			} else if (array[i].isDirectory()) {
//				getFile(array[i].getPath());
			}
		}
	}
}
