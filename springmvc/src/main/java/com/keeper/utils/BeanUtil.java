package com.keeper.utils;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import com.keeper.vo.User;

public class BeanUtil {

	public static void main(String[] args) {
		User user = new User();
		BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(user);
		beanWrapper.setPropertyValue("userName", "name");
		System.out.println(user.getUserName());
	}

}
