package com.example.myapp.aspect;

import com.example.myapp.userCatalog.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

	@Before(value = "execution(* com.example.myapp.service.EmployeeService.*(..)) and args(name,empId)")
	public void beforeAdvice(JoinPoint joinPoint, String name, String empId) {
		System.out.println("Before method:" + joinPoint.getSignature());

		System.out.println("Creating Employee with name - " + name + " and id - " + empId);
	}

	@After(value = "execution(* com.example.myapp.service.EmployeeService.*(..)) and args(name,empId)")
	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
		System.out.println("After method:" + joinPoint.getSignature());

		System.out.println("Successfully created Employee with name - " + name + " and id - " + empId);
	}

	@Before(value = "execution(* com.example.myapp.Store.*(..))")
	public void beforeAdvice2(JoinPoint joinPoint) {
		System.out.println("something happened");
	}
	@Before(value = "execution(* com.example.myapp.database.UserMapper.*(..))")
	public void beforeAdvice3(JoinPoint joinPoint) {
		System.out.println("something happened in the User mapper");
	}

	@Before(value = "execution(* com.example.myapp.service.EmployeeService.*(..))")
	public void beforeAdvice1(JoinPoint joinPoint) {


		System.out.println("shit is litt");
	}
}
