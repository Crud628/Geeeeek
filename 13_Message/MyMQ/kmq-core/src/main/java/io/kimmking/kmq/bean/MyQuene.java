package io.kimmking.kmq.bean;

import lombok.Data;

/**
 * 自定义消息队列
 * TODO
 * @date 2021年12月20日
 * @author Keason
 */
@Data
public class MyQuene {
	/** 头*/
	private Object head;
	/** 尾*/
	private Object tail;
	/** 内容*/
	private Object message;
}
