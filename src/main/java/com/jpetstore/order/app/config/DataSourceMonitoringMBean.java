package com.jpetstore.order.app.config;

public interface DataSourceMonitoringMBean {
	int getnumActive();

	int getnumIdle();

	int getmaxTotal();
}