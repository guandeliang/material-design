package com.jacob.tools.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件操作工具类
 */
public class FileUtil {


	public static List<String> read(String filePath) throws IOException {
		List<String> list = new ArrayList<>();
		InputStream is = new FileInputStream(filePath);
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = br.readLine();
		while (line != null){
			list.add(line);
			line = br.readLine();
		}

		br.close();
		isr.close();
		is.close();

		return list;
	}

	public static void  write(String filePath, List<String > list) throws IOException {
		OutputStream os = new FileOutputStream(filePath);
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		for(String line:list){
			bw.write(line);
			bw.newLine();
		}


		bw.close();
		osw.close();
		os.close();
	}
}
