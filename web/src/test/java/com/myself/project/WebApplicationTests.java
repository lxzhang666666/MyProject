package com.myself.project;

import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebApplicationTests {

	@Data
	class Phone{

		private String id;

		private String name;

		private String idCard;

		private int number;

		public void  addNumber(){
			number++;
		}

	}

	@Test
	public void contextLoads() {

		Phone phone = new Phone();

		new Thread(()->{
			phone.addNumber();
			System.out.println("phone = " + phone.getNumber());

		},"线程1").start();

		new Thread(()->{
			phone.addNumber();
			System.out.println("phone = " + phone.getNumber());

		},"线程2").start();


		new Thread(()->{
			phone.addNumber();
			System.out.println("phone = " + phone.getNumber());
		},"线程3").start();

	}

}
