package com.how2java;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.how2java.pojo.Category;




public class TestMybatis {
public static void main(String[] args) throws IOException {
	/*1. 应用程序找Mybatis要数据
	2. mybatis从数据库中找来数据
	2.1 通过mybatis-config.xml 定位哪个数据库
	2.2 通过Category.xml执行对应的select语句
	2.3 基于Category.xml把返回的数据库记录封装在Category对象中
	2.4 把多个Category对象装在一个Category集合中
	3. 返回一个Category集合 */
	String resource = "mybatis-config.xml";
	InputStream inputStream = Resources.getResourceAsStream(resource);
	SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	SqlSession sqlsession = sqlSessionFactory.openSession();
	//新增
	Category cg = new Category();
	cg.setName("category3");
	sqlsession.insert("addCategory", cg);
	//删除
	Category cg2 = new Category();
    cg2.setId(6);
    sqlsession.delete("deleteCategory",cg2);
    //获取某一个
    Category cg3= sqlsession.selectOne("getCategory",3);
    System.out.println(cg3.getName());
    //修改某一个
    Category cg4= sqlsession.selectOne("getCategory",3);
    cg4.setName("修改了的Category名称");
    sqlsession.update("updateCategory",cg4);
	//查找所有
	listAll(sqlsession);
	//提交
	sqlsession.commit();
	sqlsession.close();
	
	
}
private static void listAll(SqlSession sqlsession){
	List<Category> cs = sqlsession.selectList("listCategory");
	for (Category c : cs) {
		System.out.println(c.getName());
	}
}	
}

