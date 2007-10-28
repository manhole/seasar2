/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package examples.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 * 部署です。
 * 
 * @author higa
 * 
 */
@Entity
public class Department {

	/**
	 * 識別子です。
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer id;

	/**
	 * 名前です。
	 */
	public String name;

	/**
	 * 従業員のリストです。
	 */
	@OneToMany(mappedBy = "department")
	public List<Employee> employeeList;
	/**
	 * バージョンです。
	 */
	@Version
	public Integer version;
}
