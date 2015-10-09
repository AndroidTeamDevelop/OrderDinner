package com.shit.orderdinner.common;

import org.junit.Assert;
import org.junit.Test;

import com.shit.orderdinner.common.CommonUtils;

public class CommonUtilsTest {
	
	@Test
	public void testIsTelNum001() {
		String str = "13382299867";
		Assert.assertTrue(CommonUtils.isTelNum(str));
	}
	
	@Test
	public void testIsEMail() {
		String str = "729746172@qq.com";
		Assert.assertTrue(CommonUtils.isEMail(str));
	}
}
