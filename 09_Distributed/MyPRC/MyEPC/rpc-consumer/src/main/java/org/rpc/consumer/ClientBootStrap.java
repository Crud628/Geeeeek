package org.rpc.consumer;

import org.rpc.api.IUserService;
import org.rpc.consumer.proxy.RpcClientProxy;
import org.rpc.pojo.User;

public class ClientBootStrap {
    public static void main(String[] args) {
        IUserService userService = (IUserService) RpcClientProxy.createProxy(IUserService.class);
        User user = userService.getById(1);
        System.out.println(user);
    }
}
