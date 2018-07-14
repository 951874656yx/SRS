package com.github.cumt.SRS.domain;

/**
 * @describe 用户公共类
 * @time 2017年6月16日下午2:40:21
 */
public abstract class Person {
	private String id; // 编号
	private String name; // 姓名
	private String password;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @method 带参数的构造函数
	 * @param id
	 * @param name
	 */
	public Person(String id,String name,String password){
		this.name = name;
		this.id = id;
		this.setPassword(password);
	}
	
	/**
	 * @method 不带参数的构造函数
	 */
	public Person(){
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public abstract String toString();
	
	/**
	 * @method 输出用户信息到console
	 */
	public void display(){
		System.out.println("用户个人信息：");
		System.out.println("\t姓名：  "+this.getName());
		System.out.println("\t编号: "+this.getId());
	}
}
