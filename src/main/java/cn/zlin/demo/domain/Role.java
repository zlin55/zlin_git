package cn.zlin.demo.domain;

import java.io.Serializable;

public class Role extends Pages {
    private Long Id;
    private Long userId;
    private Long roleId;
    private String roleName;
    private Long updator;

    public Role(){
        super();
    }

    public Role( Long userId,Long roleId,Long updator){
        this.userId = userId;
        this.roleId = roleId;
        this.updator = updator;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getUpdator() {
        return updator;
    }

    public void setUpdator(Long updator) {
        this.updator = updator;
    }

    @Override
    public String toString() {
        return "Role{" +
                "Id=" + Id +
                ", userId=" + userId +
                ", roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", updator=" + updator +
                '}';
    }
}
