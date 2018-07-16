package com.wuchangi.View_Layer;

import com.wuchangi.Model_Layer.PeopleInformation;
import com.wuchangi.Control_Layer.Control;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/*
 * @program: JDBC_PeopleInformation
 * @description: View Layer
 * @author: WuchangI
 * @create: 2018-05-13-10-23
 **/

//使用控制台作为视图层
public class View
{
    //主菜单提示信息
    private static final String MAIN_MENU_CONTENT = "\t\t****欢迎使用人员信息管理系统!****\n\n" +
            "下面是该系统的功能列表：\n\n" +
            "[QUERY/Q]:  查看全部人员的基本信息(id、姓名、年龄)\n" +
            "[GET/G]:    查看某个人的具体信息\n" +
            "[ADD/A]:    添加新的人员信息\n" +
            "[UPDATE/U]: 更新人员信息\n" +
            "[DELETE/D]: 删除人员信息\n" +
            "[SEARCH/S]: 查询指定的人员信息(根据姓名、手机号码查询)\n" +
            "[EXIT/E]:   退出系统\n" +
            "\n请输入您想使用的功能项：";

    //相关操作标记
    private static final String OPERATION_QUERY = "QUERY";
    private static final String OPERATION_GET = "GET";
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_UPDATE = "UPDATE";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_SEARCH = "SEARCH";
    private static final String OPERATION_EXIT = "EXIT";
    private static final String OPERATION_BREAK = "BREAK";


    public static void main(String[] args)
    {
        //展示主菜单界面
        System.out.println(MAIN_MENU_CONTENT);

        Scanner scan = new Scanner(System.in);

        Control control = new Control();

        //记录之前执行的条件分支
        String previousBranch = "";

        Integer step = 1;

        String inputValue = null;

        PeopleInformation peopleInformation = new PeopleInformation();

        List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();

        Map<String, Object> map = new HashMap<String, Object>();

        while (scan.hasNext())
        {
            inputValue = scan.next().toString();

            if (inputValue.toUpperCase().equals(OPERATION_EXIT) || inputValue.toUpperCase().equals(OPERATION_EXIT.substring(0, 1)))
            {
                System.out.println("您已成功退出系统！");
                break;
            }
            else if (inputValue.toUpperCase().equals(OPERATION_QUERY) || inputValue.toUpperCase().equals(OPERATION_QUERY.substring(0, 1)))
            {
                try
                {
                    System.out.println("当前数据库中已有的人员基本信息如下：\n");
                    List<PeopleInformation> plist = control.query();
                    for (PeopleInformation p : plist)
                    {
                        System.out.println("id:" + p.getId() + " , 姓名:" + p.getUserName() + ", 年龄:" + p.getAge());
                    }

                    System.out.println("\n退出当前功能，返回主菜单，请输入[BREAK/B]; 退出系统，请输入[EXIT/E]：");
                }
                catch (SQLException e)
                {
                    System.out.println("查看人员基本信息失败！");
                    e.printStackTrace();
                }

            }
            else if (inputValue.toUpperCase().equals(OPERATION_GET) || inputValue.toUpperCase().equals(OPERATION_GET.substring(0, 1)) || previousBranch.equals(OPERATION_GET))
            {
                previousBranch = OPERATION_GET;

                switch (step)
                {
                    case 1:
                        System.out.println("请输入您所要查询的人员的id号：");
                        break;

                    case 2:
                        Integer idTobeSearched = Integer.valueOf(inputValue);
                        try
                        {
                            PeopleInformation p = control.get(idTobeSearched);
                            System.out.println("该人员的具体信息如下：");
                            System.out.println("id：" + p.getId() + " ,姓名：" + p.getUserName() + " ,性别：" + p.getSex() +
                            " ,年龄：" + p.getAge() + " ,生日：" + p.getBirthday() + " ,邮箱：" + p.getEmail() + " ,手机号码：" +
                            p.getMobile());
                        }
                        catch (SQLException e)
                        {
                            System.out.println("查询人员信息失败！");
                            e.printStackTrace();
                        }
                        break;
                }

                if (step == 2)
                {
                    step = 1;
                    previousBranch = "";
                    System.out.println("\n退出当前功能，返回主菜单，请输入[BREAK/B]; 退出系统，请输入[EXIT/E]：");
                }
                else
                {
                    step++;
                }
            }
            //添加新的人员信息(只需添加新增人员的 姓名 + 性别 + 年龄+ 生日 + 邮箱 + 手机号码)
            else if (inputValue.toUpperCase().equals(OPERATION_ADD) || inputValue.toUpperCase().equals(OPERATION_ADD.substring(0, 1)) || previousBranch.equals(OPERATION_ADD))
            {
                previousBranch = OPERATION_ADD;

                switch (step)
                {
                    case 1:
                        System.out.println("请输入新增人员的[姓名]：");
                        break;

                    case 2:
                        peopleInformation.setUserName(inputValue);
                        System.out.println("请输入新增人员的[性别]：(0为男性，1为女性)");
                        break;

                    case 3:
                        System.out.println(Integer.valueOf(inputValue));
                        peopleInformation.setSex(Integer.valueOf(inputValue));
                        System.out.println("请输入新增人员的[年龄]：");
                        break;

                    case 4:
                        peopleInformation.setAge(Integer.valueOf(inputValue));
                        System.out.println("请输入新增人员的[生日]：（格式为：yyyy-MM-dd）");
                        break;

                    case 5:
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date birthday = null;

                        try
                        {
                            birthday = sdf.parse(inputValue);
                            peopleInformation.setBirthday(birthday);
                            System.out.println("请输入新增人员的[邮箱]：");
                        }
                        catch (ParseException e)
                        {
                            System.out.println("您输入的格式有误！");
                            e.printStackTrace();
                        }
                        break;

                    case 6:
                        peopleInformation.setEmail(inputValue);
                        System.out.println("请输入新增人员的[手机号码]：");
                        break;

                    case 7:
                        peopleInformation.setMobile(inputValue);
                        try
                        {
                            control.add(peopleInformation);
                            System.out.println("添加新的人员信息成功！");
                        }
                        catch (SQLException e)
                        {
                            System.out.println("添加新的人员信息失败！");
                            e.printStackTrace();
                        }
                        break;
                }

                if (step == 7)
                {
                    step = 1;
                    previousBranch = "";
                    System.out.println("\n退出当前功能，返回主菜单，请输入[BREAK/B]; 退出系统，请输入[EXIT/E]：");
                }
                else
                {
                    step++;
                }
            }
            else if (inputValue.toUpperCase().equals(OPERATION_UPDATE) || inputValue.toUpperCase().equals(OPERATION_UPDATE.substring(0, 1)) || previousBranch.equals(OPERATION_UPDATE))
            {
                previousBranch = OPERATION_UPDATE;

                switch (step)
                {
                    case 1:
                        System.out.println("请输入待更新的人员的[姓名]：");
                        break;

                    case 2:
                        peopleInformation.setUserName(inputValue);
                        System.out.println("请输入待更新人员的[性别]：(0为男性，1为女性)");
                        break;

                    case 3:
                        peopleInformation.setSex(Integer.valueOf(inputValue));
                        System.out.println("请输入待更新人员的[年龄]：");
                        break;

                    case 4:
                        peopleInformation.setAge(Integer.valueOf(inputValue));
                        System.out.println("请输入待更新人员的[生日]：（格式为：yyyy-MM-dd）");
                        break;

                    case 5:
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date birthday = null;

                        try
                        {
                            birthday = sdf.parse(inputValue);
                            peopleInformation.setBirthday(birthday);
                            System.out.println("请输入待更新人员的[邮箱]：");
                        }
                        catch (ParseException e)
                        {
                            e.printStackTrace();
                            System.out.println("您输入的格式有误，请重新输入!");
                            step = 4;
                        }
                        break;

                    case 6:
                        peopleInformation.setEmail(inputValue);
                        System.out.println("请输入待更新人员的[手机号码]：");
                        break;

                    case 7:
                        peopleInformation.setMobile(inputValue);
                        System.out.println("请输入待更新人员的[id]：");
                        break;

                    case 8:
                        peopleInformation.setId(Integer.valueOf(inputValue));
                        try
                        {
                            control.update(peopleInformation);
                            System.out.println("更新人员信息成功！");

                        }
                        catch (SQLException e)
                        {
                            System.out.println("更新人员信息失败！");
                            e.printStackTrace();
                        }
                        break;
                }

                if (step == 8)
                {
                    step = 1;
                    previousBranch = "";
                    System.out.println("\n退出当前功能，返回主菜单，请输入[BREAK/B]; 退出系统，请输入[EXIT/E]：");
                }
                else
                {
                    step++;
                }

            }
            else if (inputValue.toUpperCase().equals(OPERATION_DELETE) || inputValue.toUpperCase().equals(OPERATION_DELETE.substring(0, 1)) || previousBranch.equals(OPERATION_DELETE))
            {
                previousBranch = OPERATION_DELETE;

                switch (step)
                {
                    case 1:
                        System.out.println("请输入您要删除的人员的id号：");
                        break;

                    case 2:
                        Integer idTobeSearched = Integer.valueOf(inputValue);
                        try
                        {
                            control.del(idTobeSearched);
                            System.out.println("删除该人员信息成功！");
                        }
                        catch (SQLException e)
                        {
                            System.out.println("删除该人员信息失败！");
                            e.printStackTrace();
                        }
                        break;
                }

                if (step == 2)
                {
                    step = 1;
                    previousBranch = "";
                    System.out.println("\n退出当前功能，返回主菜单，请输入[BREAK/B]; 退出系统，请输入[EXIT/E]：");
                }
                else
                {
                    step++;
                }

            }
            else if (inputValue.toUpperCase().equals(OPERATION_SEARCH) || inputValue.toUpperCase().equals(OPERATION_SEARCH.substring(0, 1)) || previousBranch.equals(OPERATION_SEARCH))
            {
                previousBranch = OPERATION_SEARCH;

                switch(step)
                {
                    case 1:
                        System.out.println("请输入所要查找的人员的姓名：");
                        map.put("name", "userName");
                        map.put("relation", "=");
                        break;
                    case 2:
                        map.put("value", inputValue);
                        params.add(map);
                        System.out.println("请输入所要查找的人员的手机号码：");
                        map.put("name", "mobile");
                        map.put("relation", "=");
                        break;
                    case 3:
                        map.put("value", inputValue);
                        params.add(map);
                        break;
                }

                if (step == 3)
                {
                    try
                    {
                        System.out.println("符合您查询条件的人员信息如下：");
                        List<PeopleInformation> pList = control.query(params);
                        for(PeopleInformation p: pList)
                        {
                            System.out.println("id：" + p.getId() + " ,姓名：" + p.getUserName() + " ,性别：" + p.getSex() +
                                    " ,年龄：" + p.getAge() + " ,生日：" + p.getBirthday() + " ,邮箱：" + p.getEmail() + " ,手机号码：" +
                                    p.getMobile());
                        }
                    }
                    catch (SQLException e)
                    {
                        System.out.println("查询人员信息失败！");
                        e.printStackTrace();
                    }

                    step = 1;
                    previousBranch = "";
                    params = new ArrayList<Map<String, Object>>();
                    System.out.println("\n退出当前功能，返回主菜单，请输入[BREAK/B]; 退出系统，请输入[EXIT/E]：");
                }
                else
                {
                    step++;
                }
            }
            else if (inputValue.toUpperCase().equals(OPERATION_BREAK) || inputValue.toUpperCase().equals(OPERATION_BREAK.substring(0, 1)))
            {
                System.out.println("\n" + MAIN_MENU_CONTENT);
            }
        }

    }

}
