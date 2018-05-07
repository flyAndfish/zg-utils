package com.study.pengxin.proxy.autor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class MyProxy {

	private static String rt = "\r\n";

	public static Object newProxyInstance(ClassLoader loader, Class<?> intf, InvocationHandler h) {

		String path = "D:/software/eclipse/workspace2/zg-utils/src/main/java/com/study/pengxin/proxy/autor/$Proxy0.java";
		// 拼接代理类字符串
		String proxyClass = get$Proxy0(intf);
		// 把字符串类输入到文件
		outputFile(proxyClass, path);
		// 编译文件
		compilerJava(path);
		// 加载入内存
		return loaderClassToJvm(h);
	}

	private static Object loaderClassToJvm(InvocationHandler h) {
		MyClassLoader loader = new MyClassLoader(
				"D:/software/eclipse/workspace2/zg-utils/src/main/java/com/study/pengxin/proxy/autor");
		try {
			Class<?> proxyClass = loader.findClass("$Proxy0");

			Constructor<?> construcotr = proxyClass.getDeclaredConstructor(InvocationHandler.class);
			return construcotr.newInstance(h);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private static String get$Proxy0(Class<?> intf) {
		Method[] methods = intf.getMethods();
		StringBuilder proxyClass = new StringBuilder("package com.study.pengxin.proxy.autor;").append(rt);
		proxyClass.append("import java.lang.reflect.InvocationHandler;").append(rt);
		proxyClass.append("import java.lang.reflect.Method;").append(rt);
		proxyClass.append("public class $Proxy0 implements ").append(intf.getName()).append("{").append(rt);
		proxyClass.append("InvocationHandler h;").append(rt);
		proxyClass.append("public $Proxy0(InvocationHandler h){").append(rt);
		proxyClass.append("this.h = h;").append(rt).append("}").append(rt).append(getMethodString(methods, intf))
				.append(rt).append("}");

		return proxyClass.toString();
	}

	private static String getMethodString(Method[] methods, Class<?> intf, Object... a) {
		StringBuilder methodStr = new StringBuilder();
		for (Method m : methods) {
			methodStr.append("public ").append(m.getReturnType().getSimpleName()).append(" ").append(m.getName());
			methodStr.append("(").append(getParamTypeAndValueString(m));
			methodStr.append(") throws Throwable").append("{").append(rt).append("Method md = ").append(intf.getName())
					.append(".class.getMethod(\"");
			methodStr.append(m.getName()).append("\",").append(getParamTypeString(m)).append(");").append(rt);
			methodStr.append("return (").append(m.getReturnType().getSimpleName()).append(")this.h.invoke(this,md,")
					.append(getParamString(m)).append(");").append(rt).append("}").append(rt);

		}
		return methodStr.toString();
	}

	private static String getParamTypeAndValueString(Method m) {
		StringBuilder sb = new StringBuilder();
		Class<?>[] parameter = m.getParameterTypes();
		for (Class<?> cls : parameter) {
			sb.append(cls.getSimpleName()).append(" param").append(cls.getSimpleName()).append(",");
		}
		if (parameter.length > 0)
			sb.deleteCharAt(sb.lastIndexOf(","));

		return sb.toString();
	}

	private static String getParamString(Method m) {
		StringBuilder sb = new StringBuilder();
		Class<?>[] parameter = m.getParameterTypes();
		sb.append("new Object[]{");
		for (Class<?> cls : parameter) {
			sb.append("param").append(cls.getSimpleName()).append(",");
		}
		if (parameter.length > 0) {
			sb.deleteCharAt(sb.lastIndexOf(",")).append("}");
		}
		if (!sb.toString().equals("new Object[]{")) {
			return sb.toString();
		}
		return null;
	}

	private static String getParamTypeString(Method m) {
		StringBuilder sb = new StringBuilder();
		Class<?>[] parameter = m.getParameterTypes();
		sb.append("new Class[]{");
		for (Class<?> cls : parameter) {
			sb.append(cls.getTypeName()).append(".class ,");
		}
		if (parameter.length > 0) {
			sb.deleteCharAt(sb.lastIndexOf(","));
		}
		sb.append("}");

		return sb.toString();
	}

	private static void outputFile(String proxyClass, String path) {
		File f = new File(path);
		try {
			FileWriter fw = new FileWriter(f);
			fw.write(proxyClass);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void compilerJava(String fileName) {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager sfm = compiler.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> javafileObject = sfm.getJavaFileObjects(fileName);
			CompilationTask task = compiler.getTask(null, sfm, null, null, null, javafileObject);
			task.call();
			sfm.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
