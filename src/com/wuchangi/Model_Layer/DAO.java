package com.wuchangi.Model_Layer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/*
 * @program: JDBC_PeopleInformation
 * @description: DAO Layer
 * @author: WuchangI
 * @create: 2018-05-13-10-23
 **/

public class DAO
{
    //添加一个新的人员信息
    public void addPeopleInformation(PeopleInformation peopleInformation) throws SQLException
    {
        //拿到数据库的连接
        Connection connection = JDBC_Connection.getConnection();

        String sql = "" +
                " insert into mydatabase.people_information(userName, sex, age, birthday, email, mobile," +
                "createUser, createDate, updateUser, updateDate, isDel)"+
                " values(" + "?,?,?,?,?,?,?,current_date(),?,current_date(),?6)";

        //预编译SQL语句,并不直接执行
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //设置SQL语句的参数
        preparedStatement.setString(1, peopleInformation.getUserName());
        preparedStatement.setInt(2, peopleInformation.getSex());
        preparedStatement.setInt(3, peopleInformation.getAge());

        //注意getBirthday返回的是java.util.Date,而setDate的参数是java.sql.Date,需要进行转换
        preparedStatement.setDate(4, new Date(peopleInformation.getBirthday().getTime()));
        preparedStatement.setString(5, peopleInformation.getEmail());
        preparedStatement.setString(6, peopleInformation.getMobile());
        preparedStatement.setString(7, peopleInformation.getCreateUser());
        preparedStatement.setString(8, peopleInformation.getUpdateUser());
        preparedStatement.setInt(9, peopleInformation.getIsDel());

        //执行SQL语句
        preparedStatement.execute();
    }


    //更新id为peopleInformation.getId()的人员的信息
    public void updatePeopleInformation(PeopleInformation peopleInformation) throws SQLException
    {
        //拿到数据库的连接
        Connection connection = JDBC_Connection.getConnection();

        String sql = "" +
                " update mydatabase.people_information"+
                " set userName=?, sex=?, age=?, birthday=?, email=?, mobile=?, updateUser=?, updateDate=current_date(), isDel=?"+
                " where id=?";

        //预编译SQL语句,并不直接执行
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //设置SQL语句的参数
        preparedStatement.setString(1, peopleInformation.getUserName());
        preparedStatement.setInt(2, peopleInformation.getSex());
        preparedStatement.setInt(3, peopleInformation.getAge());
        //注意getBirthday返回的是java.util.Date,而setDate的参数是java.sql.Date,需要进行转换
        preparedStatement.setDate(4, new Date(peopleInformation.getBirthday().getTime()));
        preparedStatement.setString(5, peopleInformation.getEmail());
        preparedStatement.setString(6, peopleInformation.getMobile());
        preparedStatement.setString(7, peopleInformation.getUpdateUser());
        preparedStatement.setInt(8, peopleInformation.getIsDel());
        preparedStatement.setInt(9, peopleInformation.getId());

        //执行SQL语句
        preparedStatement.execute();
    }

    //删除指定id的人员的信息
    public void deletePeopleInformation(Integer id) throws SQLException
    {
        //拿到数据库的连接
        Connection connection = JDBC_Connection.getConnection();

        String sql = "" +
                " delete from mydatabase.people_information" +
                " where id=?";

        //预编译SQL语句,并不直接执行
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //设置SQL语句的参数
        preparedStatement.setInt(1, id);

        //执行SQL语句
        preparedStatement.execute();

    }


    //查询数据库中所有人员的基本信息（id + 姓名 + 年龄）
    public List<PeopleInformation> queryPeopleInformation() throws SQLException
    {
        Connection connection = JDBC_Connection.getConnection();

        //通过数据库的连接操纵数据库，实现增删查改
        Statement stmt = connection.createStatement();

        //其中ResultSet使用的是java.sql.ResultSet
        ResultSet resultSet = stmt.executeQuery("select id, userName, age from mydatabase.people_information");

        List<PeopleInformation> peopleInformations = new ArrayList<PeopleInformation>();
        PeopleInformation peopleInformation = null;

        while(resultSet.next())
        {
            peopleInformation = new PeopleInformation();
            peopleInformation.setId(resultSet.getInt("id"));
            peopleInformation.setUserName(resultSet.getString("userName"));
            peopleInformation.setAge(resultSet.getInt("age"));
            peopleInformations.add(peopleInformation);
        }

        return peopleInformations;
    }

    //根据指定条件检索数据库中的所有人员的信息
    //一个Map存储一个条件表达式，比如 userName = '小美'
    public List<PeopleInformation> queryPeopleInformation(List<Map<String, Object>> params) throws SQLException
    {
        //拿到数据库的连接
        Connection connection = JDBC_Connection.getConnection();

        StringBuilder sql = new StringBuilder();
        sql.append("select * from mydatabase.people_information where 1=1");

        //设置SQL语句的参数
        if(params!=null &&params.size() > 0)
        {
            for(int i = 0; i < params.size(); i++)
            {
                Map<String, Object> map = params.get(i);
                sql.append(" and " + map.get("name") + " " + map.get("relation") + " " + map.get("value"));
            }
        }

        //预编译SQL语句,并不直接执行
        PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());

        ResultSet resultSet = preparedStatement.executeQuery();

        List<PeopleInformation> peopleInformations = new ArrayList<PeopleInformation>();
        PeopleInformation peopleInformation = null;

        while(resultSet.next())
        {
            peopleInformation = new PeopleInformation();

            peopleInformation.setId(resultSet.getInt("id"));
            peopleInformation.setUserName(resultSet.getString("userName"));
            peopleInformation.setAge(resultSet.getInt("age"));
            peopleInformation.setSex(resultSet.getInt("sex"));
            //getDate()返回的是java.sql.Date，不过不必转换，直接使用，因为java.sql.Date是java.util.Date的子集
            peopleInformation.setBirthday(resultSet.getDate("birthday"));
            peopleInformation.setEmail(resultSet.getString("email"));
            peopleInformation.setMobile(resultSet.getString("mobile"));
            peopleInformation.setCreateUser(resultSet.getString("createUser"));
            peopleInformation.setCreateDate(resultSet.getDate("createDate"));
            peopleInformation.setUpdateUser(resultSet.getString("updateUser"));
            peopleInformation.setUpdateDate(resultSet.getDate("updateDate"));
            peopleInformation.setIsDel(resultSet.getInt("isDel"));

            peopleInformations.add(peopleInformation);
        }

        return peopleInformations;
    }


    //查询指定id的人员的信息（只能有一个）
    public PeopleInformation getPeopleInformation(Integer id) throws SQLException
    {

        PeopleInformation peopleInformation = null;

        //拿到数据库的连接
        Connection connection = JDBC_Connection.getConnection();

        String sql = "" +
                " select * from mydatabase.people_information"+
                " where id=?";

        //预编译SQL语句,并不直接执行
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //设置SQL语句的参数
        preparedStatement.setInt(1, id);

        //执行查询操作
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next())
        {
            peopleInformation = new PeopleInformation();

            peopleInformation.setId(resultSet.getInt("id"));
            peopleInformation.setUserName(resultSet.getString("userName"));
            peopleInformation.setAge(resultSet.getInt("age"));
            peopleInformation.setSex(resultSet.getInt("sex"));
            //getDate()返回的是java.sql.Date，不过不必转换，直接使用，因为java.sql.Date是java.util.Date的子集
            peopleInformation.setBirthday(resultSet.getDate("birthday"));
            peopleInformation.setEmail(resultSet.getString("email"));
            peopleInformation.setMobile(resultSet.getString("mobile"));
            peopleInformation.setCreateUser(resultSet.getString("createUser"));
            peopleInformation.setCreateDate(resultSet.getDate("createDate"));
            peopleInformation.setUpdateUser(resultSet.getString("updateUser"));
            peopleInformation.setUpdateDate(resultSet.getDate("updateDate"));
            peopleInformation.setIsDel(resultSet.getInt("isDel"));
        }

        return peopleInformation;
    }

}
