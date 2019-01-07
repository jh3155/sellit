package com.sellit.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sellit.dao.ConfigDao;
import com.sellit.persistence.Config;

@Service
@Transactional
public class ConfigServiceImpl implements ConfigService {

	@Autowired
	private ConfigDao configDao;

	@Override
	public String findByConfigCategoryAndConfigName(final String configCategory, final String configName) {
		final Config config = configDao.findByConfigCategoryAndConfigName(configCategory, configName);

		if (config == null) {
			return null;
		}

		return config.getConfigValue();
	}

}
