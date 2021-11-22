package org.rpc.provider.service;

import java.util.HashMap;
import java.util.Map;

import org.rpc.api.IUserService;
import org.rpc.pojo.User;
import org.rpc.provider.anno.RpcService;
import org.springframework.stereotype.Service;

@RpcService
@Service
public class UserServiceImpl implements IUserService {

	Map<Object, User> userMap = new HashMap();
	
	@Override
	public User getById(int id) {
		// TODO Auto-generated method stub
		if (userMap.size() == 0 ) {
			User user1 = new User();
			user1.setId(1);
			user1.setName("张三");
			User user2 = new User();
			user2.setId(2);
			user2.setName("里斯");
			
			userMap.put(user1.getId(), user1);
			userMap.put(user2.getId(), user2);
		}
		return userMap.get(id);
	}
	
}
