package org.rpc.api;

import org.rpc.pojo.User;

/**
 * 用户服务
 * 
 * TODO
 * @date 2021年11月22日
 * @author Keason
 */
public interface IUserService {

    /**
     * 根据ID查询用户
     *
     * @param id
     * @return
     */
    User getById(int id);
}
