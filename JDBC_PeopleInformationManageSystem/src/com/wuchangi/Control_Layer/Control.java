package com.wuchangi.Control_Layer;

import com.wuchangi.Model_Layer.DAO;
import com.wuchangi.Model_Layer.PeopleInformation;

import java.sql.SQLException;
import java.util.*;

/*
 * @program: JDBC_PeopleInformation
 * @description: Control Layer
 * @author: WuchangI
 * @create: 2018-05-13-10-23
 **/

public class Control
{
    //查看某个人的详细信息
    public PeopleInformation get(Integer id) throws SQLException
    {
        DAO dao = new DAO();
        return dao.getPeopleInformation(id);
    }

    //添加某个人的信息
    public void add(PeopleInformation peopleInformation) throws SQLException
    {
        DAO dao = new DAO();

        peopleInformation.setCreateUser("ADMIN");
        peopleInformation.setUpdateUser("ADMIN");
        peopleInformation.setIsDel(0);

        dao.addPeopleInformation(peopleInformation);
    }

    //更新某个人（id为peopleInformation.getId()）的人的信息
    public void update(PeopleInformation peopleInformation) throws SQLException
    {
        DAO dao = new DAO();

        peopleInformation.setCreateUser("ADMIN");
        peopleInformation.setUpdateUser("ADMIN");
        peopleInformation.setIsDel(0);

        dao.updatePeopleInformation(peopleInformation);
    }

    //删除某个人的信息
    public void del(Integer id) throws SQLException
    {
        DAO dao = new DAO();
        dao.deletePeopleInformation(id);
    }

    //查询数据库中所有人员的基本信息（id + 姓名 + 年龄）
    public List<PeopleInformation> query() throws SQLException
    {
        DAO dao = new DAO();
        List<PeopleInformation> peopleInformationList =dao.queryPeopleInformation();
        return peopleInformationList;
    }

    //查询数据库中符合指定条件的所有人员的信息
    public List<PeopleInformation> query(List<Map<String, Object>> params) throws SQLException
    {
        DAO dao = new DAO();
        List<PeopleInformation> peopleInformationsList = dao.queryPeopleInformation(params);
        return peopleInformationsList;
    }
}
