package com.hbase.services;

import java.util.List;

public interface IHbaseService {

	public List query(String querySql) ;
	public void update(String querySql);
	public void batchUpdate(String updateSQL) ;
}
