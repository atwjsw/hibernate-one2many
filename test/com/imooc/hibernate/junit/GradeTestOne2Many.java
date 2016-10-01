package com.imooc.hibernate.junit;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.imooc.hibernate.entity.Grade;
import com.imooc.hibernate.entity.Student;
import com.imooc.hibernate.util.HibernateUtil;

/*
 * 单向一对多关系（班级--》学生）
 * 建立关联关系后，可以方便的从一个对象导航到另一个对象
 * 注意关联的方向是单向的，反向是查找不到的。
 */
public class GradeTestOne2Many {

	@Test
	public void testSaveGrade() {
		
		Grade grade = new Grade("Java班", "Java高级培训");
		Student stu1 = new Student("张三","男");
		Student stu2 = new Student("慕女神","女");	
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
