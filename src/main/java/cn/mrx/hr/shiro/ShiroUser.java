package cn.mrx.hr.shiro;

import cn.mrx.hr.model.Resource;
import cn.mrx.hr.model.Role;
import cn.mrx.hr.model.User;

import java.util.List;
import java.util.Set;

/**
 * Author: xialiangbo
 * Date: 2017/8/1 9:27
 * Description:
 */
public class ShiroUser {

    private User user;
    private Set<String> roleNameSet;
    private Set<String> resourceUrlSet;

    public ShiroUser() {

    }

    public ShiroUser(User user, Set<String> roleNameSet, Set<String> resourceUrlSet) {
        this.user = user;
        this.roleNameSet = roleNameSet;
        this.resourceUrlSet = resourceUrlSet;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<String> getRoleNameSet() {
        return roleNameSet;
    }

    public void setRoleNameSet(Set<String> roleNameSet) {
        this.roleNameSet = roleNameSet;
    }

    public Set<String> getResourceUrlSet() {
        return resourceUrlSet;
    }

    public void setResourceUrlSet(Set<String> resourceUrlSet) {
        this.resourceUrlSet = resourceUrlSet;
    }
}
