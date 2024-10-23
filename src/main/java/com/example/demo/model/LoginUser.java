package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//Lombokライブラリのアノテーションで、クラスに対してゲッター、セッター、toString()、equals()、hashCode()メソッドを自動生成します。
import lombok.Data;

//もしLoginUserもデータベースに保存する必要がある場合、@Entityや@Tableアノテーションを追加してエンティティとして定義する必要があります。

@Entity
@Data
@Table(name = "loginuser")
public class LoginUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String username;

	private String password;

	private String email;

	//	@ElementCollection(fetch = FetchType.EAGER)
	private String role;

}