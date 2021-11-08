package com.jkshop.mysqldemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface SQLDao {
	void insertTest(long id);
	
	void insertTest2(String sql);
}
