package com.imooc.hibernate.junit;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.imooc.hibernate.entity.Grade;
import com.imooc.hibernate.entity.Student;
import com.imooc.hibernate.util.HibernateUtil;

/*
 * 单向多对一关系（学生--》班级）
 * 建立关联关系后，可以方便的从一个对象导航到另一个对象
 * 注意关联的方向是单向的，反向是查找不到的。
 */
public class StudentTestMany2One {

	@Test
	public void testSave() {
		
		Grade grade = new Grade("Java一班", "Java高级培训");
		Student stu1 = new Student("慕女神","女");	
		Student stu2 = new Student("小慕慕","男");
		stu1.setGrade(grade);
		stu2.setGrade(grade);
		grade.getStudents().add(stu1);
		grade.getStudents().add(stu2);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(grade);
		//session.save(stu1);
		//session.save(stu2);
		tx.commit();
		HibernateUtil.closeSession(session);		
	};	
	
	@Test
	public void testFindByNavigation() {
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Student s = (Student)session.get(Student.class, 2);
		System.out.println(s.getSname() + "   " + s.getSex());		
		//自动到数据库获取班级记录
		Grade grade = s.getGrade();
		System.out.println(grade.getGname() + "   " + grade.getGdesc());
		tx.commit();
		HibernateUtil.closeSession(session);		
	};
	
	@Test
	public void testMany2Many() {
		
		Grade grade = new Grade("Java一班", "Java高级培训");
		Student stu1 = new Student("慕女神","女");	
		Student stu2 = new Student("小慕慕","男");
		stu1.setGrade(grade);
		stu2.setGrade(grade);
		grade.getStudents().add(stu1);
		grade.getStudents().add(stu2);
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(grade);
		session.save(stu1);
		session.save(stu2);
		tx.commit();
		HibernateUtil.closeSession(session);		
	};	
	
	@Test
	public void testFindStudentsByGrade() {
		
		Session session = HibernateUtil.getSession();
		Grade grade = (Grade)session.get(Grade.class, 1);
		System.out.println(grade.getGname() + "   " + grade.getGdesc());
		Set<Student> students = grade.getStudents();
		for (Student s: students) {
			System.out.println(s.getSname() + "   " + s.getSex());
		}		
		HibernateUtil.closeSession(session);
		
	};	
	
	@Test
	public void testUpdateGrade() {
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Grade grade = new Grade("Java初级班", "Java初级培训");
		Student stu = (Student) session.get(Student.class, 2);
		grade.getStudents().add(stu);
		session.save(grade);
		tx.commit();
		HibernateUtil.closeSession(session);		
	};	
	
	@Test
	public void testDeleteStudent() {
		
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Student stu = (Student) session.get(Student.class, 2);
		session.delete(stu);
		tx.commit();
		HibernateUtil.closeSession(session);		
	};	
}
