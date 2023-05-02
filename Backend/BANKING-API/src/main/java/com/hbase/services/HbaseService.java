package com.hbase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class HbaseService implements IHbaseService {
	private JdbcTemplate jdbcTemplate;

	public HbaseService(JdbcTemplate template) {
		this.jdbcTemplate = template;
	}

	public HbaseService() {
		Super();
	}

	private void Super() {
		// TODO Auto-generated method stub

	}
	@Override
	public void update(String querySql) {
		System.out.println(querySql);
		jdbcTemplate.update(querySql);
	}

	@Override
	public void batchUpdate(String updateSQL) {
		System.out.println("##########BATCH UPDATE:" + updateSQL);
		jdbcTemplate.batchUpdate(updateSQL);
		
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Autowired
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public List query(String querySql) {
		return jdbcTemplate.queryForList(querySql);
	}
}
