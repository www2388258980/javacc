package com.yj.t_2021_01;

import java.io.*;

public class ObjectClone {
		public static void main(String[] args) throws IOException, ClassNotFoundException {
				Adress adress = new Adress();
				adress.setProvince("上海市");
				adress.setCity("上海市");
				adress.setStreet("浦东新区");
				Person p = new Person("张三", "18", "男", adress);
				// 第一种克隆方法
				Person p1 = (Person) p.clone1();
				// 第二种克隆方法
				Person p2 = (Person) p.clone2();

				// 测试第一种克隆方法
				p1.setAge("20");
				System.out.println(p.getAge() + "," + p1.getAge()); // 18,20
//				p1.getAdress().setStreet("宝山区");
				// Adress{province='上海市', city='上海市', street='宝山区'}-----	Adress{province='上海市', city='上海市', street='宝山区'}
//				System.out.println(p.getAdress().toString() + "-----\t" + p1.getAdress().toString());

				// 测试第二种克隆方法
				p2.setAge("20");
				System.out.println(p.getAge() + "," + p2.getAge()); // 18,20
				p2.getAdress().setStreet("宝山区");
				System.out.println(p.getAdress().toString() + "-----\t" + p2.getAdress().toString());

		}
}

class Adress implements Serializable {
		String province;
		String city;
		String street;

		public Adress() {
		}

		public Adress(String province, String city, String street) {
				this.province = province;
				this.city = city;
				this.street = street;
		}

		public String getProvince() {
				return province;
		}

		public void setProvince(String province) {
				this.province = province;
		}

		public String getCity() {
				return city;
		}

		public void setCity(String city) {
				this.city = city;
		}

		public String getStreet() {
				return street;
		}

		public void setStreet(String street) {
				this.street = street;
		}

		@Override
		public String toString() {
				return "Adress{" +
								"province='" + province + '\'' +
								", city='" + city + '\'' +
								", street='" + street + '\'' +
								'}';
		}
}

class Person implements Serializable {

		String name;
		String age;
		String sex;
		Adress adress;

		public Person() {
		}

		public Person(String name, String age, String sex, Adress adress) {
				this.name = name;
				this.age = age;
				this.sex = sex;
				this.adress = adress;
		}

		public String getName() {
				return name;
		}

		public void setName(String name) {
				this.name = name;
		}

		public String getAge() {
				return age;
		}

		public void setAge(String age) {
				this.age = age;
		}

		public String getSex() {
				return sex;
		}

		public void setSex(String sex) {
				this.sex = sex;
		}

		public Adress getAdress() {
				return adress;
		}

		public void setAdress(Adress adress) {
				this.adress = adress;
		}


		public Object clone1() {
				Person p = new Person();
				p.name = this.name;
				p.age = this.age;
				p.sex = this.sex;
				p.adress = this.adress;
				return p;
		}

		public Object clone2() throws IOException, ClassNotFoundException {
				ObjectOutputStream oos = null;
				ObjectInputStream ois = null;
				File file = null;
				Person p = null;
				try {
						file = new File("temp.txt");


						oos = new ObjectOutputStream(new FileOutputStream(file));
						oos.writeObject(this);
						oos.flush();

						ois = new ObjectInputStream(new FileInputStream(file));
						p = (Person) ois.readObject();
				} finally {
						if (ois != null) {
								ois.close();
						}
						if (oos != null) {
								oos.close();
						}
						if (file != null) {
								file.delete();
						}
				}
				return p;
		}

}
