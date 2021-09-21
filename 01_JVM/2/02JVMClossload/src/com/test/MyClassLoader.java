package com.test;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

/**
 * 
 * TODO 自定义ClassLoader
 * @date 2021年9月20日
 * @author Keason
 */
public class MyClassLoader extends ClassLoader {
	public static void main(String[] args) throws Exception{
		// 文件路径
		String fullFilePath = "Hello";
		
		// 执行方法
		String methodName = "hello";
		
		// 得到class对象
		MyClassLoader myClassLoader = new MyClassLoader();
		Class<?> clazz = myClassLoader.loadClass(fullFilePath);
		Object hello = clazz.getDeclaredConstructor().newInstance();
		
		// 调用hello方法
		Method method = clazz.getMethod(methodName);
		method.invoke(hello);
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 如果支持包名, 则需要进行路径转换
        String resourcePath = name.replace(".", "/");
        // 文件后缀
        final String suffix = ".xlass";
		// 输入流
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourcePath + suffix);
        try {
            // 读取数据
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            // 转换
            byte[] classBytes = decode(byteArray);
            // 通知底层定义这个类
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            close(inputStream);
        }
	}
	
    /**
     * 解密  取反码
     * 
     * @param byteArray 加密的字节数组
     * @return 解密的字节数组
     */
    private static byte[] decode(byte[] byteArray) {
        byte[] targetArray = new byte[byteArray.length];
        for (int i = 0; i < byteArray.length; i++) {
            targetArray[i] = (byte) (255 - byteArray[i]);
        }
        return targetArray;
    }

    /**
     * 关闭io
     * 
     * @param res 资源
     */
    private static void close(Closeable res) {
        if (null != res) {
            try {
                res.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
