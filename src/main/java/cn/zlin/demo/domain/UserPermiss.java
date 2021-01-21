package cn.zlin.demo.domain;

public class UserPermiss {
    private Long roleId;
    private String roleName;

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

    @Override
    public String toString() {
        return "UserPermiss{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
