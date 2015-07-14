package com.bigbata.craftsman.config.datasource;

import com.bigbata.craftsman.config.Constants;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 通过数据源，获取当前数据源
 * Created by lixianghui on 15-7-13.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public DynamicDataSource() {
        this.setDefaultTargetDataSource(DataSourceContainer.get(Constants.DEFAULT_DATA_SOURCE));
        this.setTargetDataSources(DataSourceContainer.getDataSources());
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContainer.getCurrentDateSource();
    }
}
