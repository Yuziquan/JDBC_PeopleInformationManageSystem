package com.wuchangi.Model_Layer;

import java.util.Date;


/*
 * @program: JDBC_PeopleInformation
 * @description: PeopleInformation Model Class
 * @author: WuchangI
 * @create: 2018-05-13-10-23
 **/

public class PeopleInformation
{
    //人员唯一的编号，设置为主键且自增
    private Integer id;
    //人员的姓名
    private String userName;

    //人员的性别，0为男性，1为女性
    private Integer sex;
    //人员的年龄
    private Integer age;
    //人员的生日
    private Date birthday;
    //人员的邮箱
    private String email;
    //人员的手机号码
    private String mobile;
    //创建该记录的人的名称
    private String createUser;
    //更新该记录的人的名称
    private String updateUser;
    //该记录创建日期
    private Date createDate;
    //该记录更新日期
    private Date updateDate;
    //是否删除，1为删除，0为不删除
    private Integer isDel;


    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public Integer getSex()
    {
        return sex;
    }

    public void setSex(Integer sex)
    {
        this.sex = sex;
    }

    public Integer getAge()
    {
        return age;
    }

    public void setAge(Integer age)
    {
        this.age = age;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public void setBirthday(Date birthday)
    {
        this.birthday = birthday;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getCreateUser()
    {
        return createUser;
    }

    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(String updateUser)
    {
        this.updateUser = updateUser;
    }

    public Date getCreateDate()
    {
        return createDate;
    }

    public void setCreateDate(Date createDate)
    {
        this.createDate = createDate;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Integer getIsDel()
    {
        return isDel;
    }

    public void setIsDel(Integer isDel)
    {
        this.isDel = isDel;
    }

    @Override
    public String toString()
    {
        return "PeopleInformation{" + "id=" + id + ", userName='" + userName + '\'' + ", sex=" + sex + ", age=" + age + ", birthday=" + birthday + ", email='" + email + '\'' + ", mobile='" + mobile + '\'' + ", createUser='" + createUser + '\'' + ", updateUser='" + updateUser + '\'' + ", createDate=" + createDate + ", updateDate=" + updateDate + ", isDel=" + isDel + '}';
    }

}