package com.study.pengxin.proxy.autor;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MyClassLoader extends ClassLoader{

	private File file;
	
	public MyClassLoader(String path) {
		this.file = new File(path);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {

		if(file!=null) {
			File  classFile = new File(file,name+".class");
			if(classFile.exists()) {
				try {
					FileInputStream input = new FileInputStream(classFile);
					ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
					byte[] buffer = new byte[1024];
					int len;
					while((len=input.read(buffer))!=-1) {
						byteOut.write(buffer, 0, len);
					}
					
					//把字节流里面的内容加载到内存中
					return defineClass("com.study.pengxin.proxy.autor."+name, byteOut.toByteArray(), 0,byteOut.size());
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return super.findClass(name); 
	}
}
