package com.jpetstore.order.app.config;

import java.lang.management.ManagementFactory;

import javax.annotation.PostConstruct;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

@Component
public class DataSourceMonitoring implements DataSourceMonitoringMBean {

	// DataSource가 여러개인 경우 원하는 DataSource 가져오기
	@Autowired
	HikariDataSource dataSource;

	@Override
	public int getnumActive() {
		// PoolMXBean 이 초기화 되지 않으면 null 에러 발생 방지
		return dataSource.getHikariPoolMXBean() != null ? dataSource.getHikariPoolMXBean().getActiveConnections() : 0;
	}

	@Override
	public int getnumIdle() {
		return dataSource.getHikariPoolMXBean() != null ? dataSource.getHikariPoolMXBean().getIdleConnections() : 0;
	}

	@Override
	public int getmaxTotal() {
		return dataSource.getHikariConfigMXBean().getMaximumPoolSize();
	}

	@PostConstruct
	public void init() {
		try {
			// type 이 DataSource 이어야 한다. name 은 아무거나 원하는 것 셋팅
			ManagementFactory.getPlatformMBeanServer().registerMBean(this,
					new ObjectName("com.jpetstore.order:type=DataSource,name=hikari-pool,context=/"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}