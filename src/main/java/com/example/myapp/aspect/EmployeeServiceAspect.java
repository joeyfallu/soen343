package com.example.myapp.aspect;

import com.example.myapp.database.UserMapper;
import com.example.myapp.userCatalog.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {


	@Before(value = "execution(* com.example.myapp.Store.*(..))")
	public void beforeAdvice2(JoinPoint joinPoint) {
		System.out.println("something happened");
	}

	@Before(value = "execution(* com.example.myapp.database.UserMapper.getUserCatalog(..))")
	public void beforeAdvice3(JoinPoint joinPoint) {
		System.out.println(((UserMapper)joinPoint.getThis()).get(362).toString());
		System.out.println(joinPoint.toString());
	}
	
}
