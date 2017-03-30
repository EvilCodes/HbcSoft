package com.hbcsoft;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AAA {

	public static void main(String[] args) {
		List<Object> aa = new ArrayList<Object>();
		
		aa.add(1);
		aa.add("dada");
		aa.add(new Date());
		aa.add(1d);
		aa.add(3l);
		
		for(int index=0; index < aa.size(); index++)
		{
			System.out.println(aa.get(index).getClass().getTypeName());
		}
		

	}

}
