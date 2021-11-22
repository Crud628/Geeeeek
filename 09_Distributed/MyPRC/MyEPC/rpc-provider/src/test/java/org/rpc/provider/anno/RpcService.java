package org.rpc.provider.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * TODO
 * @date 2021年11月22日
 * @author Keason
 */
@Target(ElementType.TYPE) // 用于接口和类上
@Retention(RetentionPolicy.RUNTIME) // 运行时可以获取到
public @interface RpcService {

}
