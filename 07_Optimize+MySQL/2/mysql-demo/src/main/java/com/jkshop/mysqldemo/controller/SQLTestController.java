package com.jkshop.mysqldemo.controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jkshop.mysqldemo.dao.SQLDao;
import com.jkshop.mysqldemo.util.IdWorker;

/**
 * 控制层 接收前端选择
 * 
 * TODO
 * 
 * @date 2021年11月8日
 * @author Keason
 */
@Controller
public class SQLTestController {

	@Autowired
	private SQLDao sqlDao;

	@Autowired
	private IdWorker idWorker;
	
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	private final static String sss = "INSERT INTO jkshop.orders (order_id,user_id,untitled,receiver_name,receiver_mobile,receiver_address,total_amount,actual_amount,pay_type,order_remark,status,delivery_type,delivery_flow_id,order_freight,delete_status,create_time,update_time,pay_time,delivery_time,flish_time,cancel_time,close_type) VALUES ";

	@GetMapping("/pre")
	@ResponseBody
	public long pre() {
		long start = System.currentTimeMillis();
//		for (int i = 0; i < 1e7; i++)
//			sqlDao.insertTest(idWorker.nextId());
    	StringBuffer sb = new StringBuffer();
    	sb.append(sss);
    	for (int i = 1;i <= 1e7;i++) {
    		 if (i % 10000 == 0) {
    			 jdbcTemplate.execute(sb.substring(0, sb.length() - 1));
    			 // sqlDao.insertTest2(sb.substring(0, sb.length() - 1));
    			 sb.delete(0,sb.length());
    			 sb.append(sss);
    			 System.out.println(System.currentTimeMillis() - start);
    		}
    		sb.append("('");
    		sb.append(idWorker.nextId());
    		sb.append("', '', '', '', '', '', 0, 0, 0, '', '', '', '',0.00000000, 0, '20211108', '20211108', '20211108', '20211108', '20211108', '20211108', 0),");
    		
    	}
		long count = System.currentTimeMillis() - start;
		return count;
	}

	@GetMapping("bat")
	@ResponseBody
	public long bat() {
		long start = System.currentTimeMillis();
		ExecutorService executorService = new ThreadPoolExecutor(4, Runtime.getRuntime().availableProcessors() * 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        for (int i = 0; i < 20; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
//	            	for (int i = 0;i < 1e5;i++) {
//	            		sqlDao.insertTest(idWorker.nextId());
//	            	}
                	StringBuffer sb = new StringBuffer();
                	sb.append(sss);
                	for (int i = 1;i <= 50000;i++) {
                		 if (i % 5000 == 0) { //packet
                			 jdbcTemplate.execute(sb.substring(0, sb.length() - 1));
                			 // sqlDao.insertTest2(sb.substring(0, sb.length() - 1));
                			 sb.delete(0,sb.length());
                			 sb.append(sss);
                			 System.out.println(System.currentTimeMillis() - start);
                		}
                		sb.append("('");
                		sb.append(idWorker.nextId());
                		sb.append("', '', '', '', '', '', 0, 0, 0, '', '', '', '',0.00000000, 0, '20211108', '20211108', '20211108', '20211108', '20211108', '20211108', 0),");
                		
                	}
                }
            });
        }
		long count = System.currentTimeMillis() - start;
		executorService.shutdown();
		return count;
	}

	@GetMapping("loac")
	@ResponseBody
	public long loca() {
		return 3;
	}
}
