package com.leidi.lteapp.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoBean {

    /**
     * msg : 操作成功
     * code : 200
     * user : {"searchValue":null,"createBy":"admin","createTime":"2022-08-22 20:02:17","updateBy":null,"updateTime":null,"remark":null,"params":{"@type":"java.util.HashMap"},"userId":110,"deptId":207,"userName":"zyuser","nickName":"zyuser","email":"","phonenumber":"","sex":"0","isLoginWeb":"1","isLoginApp":"1","isAdmin":"1","avatar":"","password":"$2a$10$onzgo5eTkuFm6CUlx.aU8.5g5su14jFZSIvPnnJVKjgz6mHXClqga","status":"0","delFlag":"0","loginIp":"172.16.200.210","loginDate":"2022-08-27T10:42:50.000+08:00","dept":{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{"@type":"java.util.HashMap"},"deptId":"207","parentId":"0","ancestors":"0","deptName":"电信","orderNum":0,"leader":null,"phone":null,"email":null,"status":"0","delFlag":null,"isSpecial":null,"level":null,"parentName":null,"children":[]},"roles":[{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{"@type":"java.util.HashMap"},"roleId":2,"roleName":"专业管理员","roleKey":"zyadmin","roleSort":"2","dataScope":"3","menuCheckStrictly":false,"deptCheckStrictly":false,"status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null,"admin":false}],"roleIds":null,"postIds":null,"roleId":null,"admin":false}
     * userGroup : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{},"id":2,"userId":"110","ancestors":null,"deptId":"207","deptName":"电信","dwId":"209","dwName":"电信-单位1","bzId":"210","bzName":"电信-单位1-班组1","isSpecial":"1"}
     */

    private String msg;
    private int code;
    private UserBean user;
    private UserGroupBean userGroup;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public UserGroupBean getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroupBean userGroup) {
        this.userGroup = userGroup;
    }

    public static class UserBean {
        /**
         * searchValue : null
         * createBy : admin
         * createTime : 2022-08-22 20:02:17
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {"@type":"java.util.HashMap"}
         * userId : 110
         * deptId : 207
         * userName : zyuser
         * nickName : zyuser
         * email :
         * phonenumber :
         * sex : 0
         * isLoginWeb : 1
         * isLoginApp : 1
         * isAdmin : 1
         * avatar :
         * password : $2a$10$onzgo5eTkuFm6CUlx.aU8.5g5su14jFZSIvPnnJVKjgz6mHXClqga
         * status : 0
         * delFlag : 0
         * loginIp : 172.16.200.210
         * loginDate : 2022-08-27T10:42:50.000+08:00
         * dept : {"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{"@type":"java.util.HashMap"},"deptId":"207","parentId":"0","ancestors":"0","deptName":"电信","orderNum":0,"leader":null,"phone":null,"email":null,"status":"0","delFlag":null,"isSpecial":null,"level":null,"parentName":null,"children":[]}
         * roles : [{"searchValue":null,"createBy":null,"createTime":null,"updateBy":null,"updateTime":null,"remark":null,"params":{"@type":"java.util.HashMap"},"roleId":2,"roleName":"专业管理员","roleKey":"zyadmin","roleSort":"2","dataScope":"3","menuCheckStrictly":false,"deptCheckStrictly":false,"status":"0","delFlag":null,"flag":false,"menuIds":null,"deptIds":null,"admin":false}]
         * roleIds : null
         * postIds : null
         * roleId : null
         * admin : false
         */

        private Object searchValue;
        private String createBy;
        private String createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBean params;
        private String userId;
        private String deptId;
        private String userName;
        private String nickName;
        private String email;
        private String phonenumber;
        private String sex;
        private String isLoginWeb;
        private String isLoginApp;
        private String isAdmin;
        private String avatar;
        private String password;
        private String status;
        private String delFlag;
        private String loginIp;
        private String loginDate;
        private DeptBean dept;
        private Object roleIds;
        private Object postIds;
        private Object roleId;
        private boolean admin;
        private List<RolesBean> roles;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBean getParams() {
            return params;
        }

        public void setParams(ParamsBean params) {
            this.params = params;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhonenumber() {
            return phonenumber;
        }

        public void setPhonenumber(String phonenumber) {
            this.phonenumber = phonenumber;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIsLoginWeb() {
            return isLoginWeb;
        }

        public void setIsLoginWeb(String isLoginWeb) {
            this.isLoginWeb = isLoginWeb;
        }

        public String getIsLoginApp() {
            return isLoginApp;
        }

        public void setIsLoginApp(String isLoginApp) {
            this.isLoginApp = isLoginApp;
        }

        public String getIsAdmin() {
            return isAdmin;
        }

        public void setIsAdmin(String isAdmin) {
            this.isAdmin = isAdmin;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(String delFlag) {
            this.delFlag = delFlag;
        }

        public String getLoginIp() {
            return loginIp;
        }

        public void setLoginIp(String loginIp) {
            this.loginIp = loginIp;
        }

        public String getLoginDate() {
            return loginDate;
        }

        public void setLoginDate(String loginDate) {
            this.loginDate = loginDate;
        }

        public DeptBean getDept() {
            return dept;
        }

        public void setDept(DeptBean dept) {
            this.dept = dept;
        }

        public Object getRoleIds() {
            return roleIds;
        }

        public void setRoleIds(Object roleIds) {
            this.roleIds = roleIds;
        }

        public Object getPostIds() {
            return postIds;
        }

        public void setPostIds(Object postIds) {
            this.postIds = postIds;
        }

        public Object getRoleId() {
            return roleId;
        }

        public void setRoleId(Object roleId) {
            this.roleId = roleId;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public List<RolesBean> getRoles() {
            return roles;
        }

        public void setRoles(List<RolesBean> roles) {
            this.roles = roles;
        }

        public static class ParamsBean {
            @SerializedName("@type")
            private String _$Type80; // FIXME check this code

            public String get_$Type80() {
                return _$Type80;
            }

            public void set_$Type80(String _$Type80) {
                this._$Type80 = _$Type80;
            }
        }

        public static class DeptBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {"@type":"java.util.HashMap"}
             * deptId : 207
             * parentId : 0
             * ancestors : 0
             * deptName : 电信
             * orderNum : 0
             * leader : null
             * phone : null
             * email : null
             * status : 0
             * delFlag : null
             * isSpecial : null
             * level : null
             * parentName : null
             * children : []
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private String deptId;
            private String parentId;
            private String ancestors;
            private String deptName;
            private int orderNum;
            private Object leader;
            private Object phone;
            private Object email;
            private String status;
            private Object delFlag;
            private Object isSpecial;
            private Object level;
            private Object parentName;
            private List<?> children;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public String getDeptId() {
                return deptId;
            }

            public void setDeptId(String deptId) {
                this.deptId = deptId;
            }

            public String getParentId() {
                return parentId;
            }

            public void setParentId(String parentId) {
                this.parentId = parentId;
            }

            public String getAncestors() {
                return ancestors;
            }

            public void setAncestors(String ancestors) {
                this.ancestors = ancestors;
            }

            public String getDeptName() {
                return deptName;
            }

            public void setDeptName(String deptName) {
                this.deptName = deptName;
            }

            public int getOrderNum() {
                return orderNum;
            }

            public void setOrderNum(int orderNum) {
                this.orderNum = orderNum;
            }

            public Object getLeader() {
                return leader;
            }

            public void setLeader(Object leader) {
                this.leader = leader;
            }

            public Object getPhone() {
                return phone;
            }

            public void setPhone(Object phone) {
                this.phone = phone;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
                this.delFlag = delFlag;
            }

            public Object getIsSpecial() {
                return isSpecial;
            }

            public void setIsSpecial(Object isSpecial) {
                this.isSpecial = isSpecial;
            }

            public Object getLevel() {
                return level;
            }

            public void setLevel(Object level) {
                this.level = level;
            }

            public Object getParentName() {
                return parentName;
            }

            public void setParentName(Object parentName) {
                this.parentName = parentName;
            }

            public List<?> getChildren() {
                return children;
            }

            public void setChildren(List<?> children) {
                this.children = children;
            }
        }

        public static class RolesBean {
            /**
             * searchValue : null
             * createBy : null
             * createTime : null
             * updateBy : null
             * updateTime : null
             * remark : null
             * params : {"@type":"java.util.HashMap"}
             * roleId : 2
             * roleName : 专业管理员
             * roleKey : zyadmin
             * roleSort : 2
             * dataScope : 3
             * menuCheckStrictly : false
             * deptCheckStrictly : false
             * status : 0
             * delFlag : null
             * flag : false
             * menuIds : null
             * deptIds : null
             * admin : false
             */

            private Object searchValue;
            private Object createBy;
            private Object createTime;
            private Object updateBy;
            private Object updateTime;
            private Object remark;
            private ParamsBean params;
            private int roleId;
            private String roleName;
            private String roleKey;
            private String roleSort;
            private String dataScope;
            private boolean menuCheckStrictly;
            private boolean deptCheckStrictly;
            private String status;
            private Object delFlag;
            private boolean flag;
            private Object menuIds;
            private Object deptIds;
            private boolean admin;

            public Object getSearchValue() {
                return searchValue;
            }

            public void setSearchValue(Object searchValue) {
                this.searchValue = searchValue;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(Object updateBy) {
                this.updateBy = updateBy;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }

            public Object getRemark() {
                return remark;
            }

            public void setRemark(Object remark) {
                this.remark = remark;
            }

            public ParamsBean getParams() {
                return params;
            }

            public void setParams(ParamsBean params) {
                this.params = params;
            }

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRoleKey() {
                return roleKey;
            }

            public void setRoleKey(String roleKey) {
                this.roleKey = roleKey;
            }

            public String getRoleSort() {
                return roleSort;
            }

            public void setRoleSort(String roleSort) {
                this.roleSort = roleSort;
            }

            public String getDataScope() {
                return dataScope;
            }

            public void setDataScope(String dataScope) {
                this.dataScope = dataScope;
            }

            public boolean isMenuCheckStrictly() {
                return menuCheckStrictly;
            }

            public void setMenuCheckStrictly(boolean menuCheckStrictly) {
                this.menuCheckStrictly = menuCheckStrictly;
            }

            public boolean isDeptCheckStrictly() {
                return deptCheckStrictly;
            }

            public void setDeptCheckStrictly(boolean deptCheckStrictly) {
                this.deptCheckStrictly = deptCheckStrictly;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public Object getDelFlag() {
                return delFlag;
            }

            public void setDelFlag(Object delFlag) {
                this.delFlag = delFlag;
            }

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            public Object getMenuIds() {
                return menuIds;
            }

            public void setMenuIds(Object menuIds) {
                this.menuIds = menuIds;
            }

            public Object getDeptIds() {
                return deptIds;
            }

            public void setDeptIds(Object deptIds) {
                this.deptIds = deptIds;
            }

            public boolean isAdmin() {
                return admin;
            }

            public void setAdmin(boolean admin) {
                this.admin = admin;
            }
        }
    }

    public static class UserGroupBean {
        /**
         * searchValue : null
         * createBy : null
         * createTime : null
         * updateBy : null
         * updateTime : null
         * remark : null
         * params : {}
         * id : 2
         * userId : 110
         * ancestors : null
         * deptId : 207
         * deptName : 电信
         * dwId : 209
         * dwName : 电信-单位1
         * bzId : 210
         * bzName : 电信-单位1-班组1
         * isSpecial : 1是（4个tab），0否（没有中间2个）
         */

        private Object searchValue;
        private Object createBy;
        private Object createTime;
        private Object updateBy;
        private Object updateTime;
        private Object remark;
        private ParamsBeanX params;
        private int id;
        private String userId;
        private Object ancestors;
        private String deptId;
        private String deptName;
        private String dwId;
        private String dwName;
        private String bzId;
        private String bzName;
        private String isSpecial;

        public Object getSearchValue() {
            return searchValue;
        }

        public void setSearchValue(Object searchValue) {
            this.searchValue = searchValue;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(Object updateBy) {
            this.updateBy = updateBy;
        }

        public Object getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(Object updateTime) {
            this.updateTime = updateTime;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public ParamsBeanX getParams() {
            return params;
        }

        public void setParams(ParamsBeanX params) {
            this.params = params;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getAncestors() {
            return ancestors;
        }

        public void setAncestors(Object ancestors) {
            this.ancestors = ancestors;
        }

        public String getDeptId() {
            return deptId;
        }

        public void setDeptId(String deptId) {
            this.deptId = deptId;
        }

        public String getDeptName() {
            return deptName;
        }

        public void setDeptName(String deptName) {
            this.deptName = deptName;
        }

        public String getDwId() {
            return dwId;
        }

        public void setDwId(String dwId) {
            this.dwId = dwId;
        }

        public String getDwName() {
            return dwName;
        }

        public void setDwName(String dwName) {
            this.dwName = dwName;
        }

        public String getBzId() {
            return bzId;
        }

        public void setBzId(String bzId) {
            this.bzId = bzId;
        }

        public String getBzName() {
            return bzName;
        }

        public void setBzName(String bzName) {
            this.bzName = bzName;
        }

        public String getIsSpecial() {
            return isSpecial;
        }

        public void setIsSpecial(String isSpecial) {
            this.isSpecial = isSpecial;
        }

        public static class ParamsBeanX {
        }
    }
}
