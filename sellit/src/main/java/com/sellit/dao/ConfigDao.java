package com.sellit.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sellit.persistence.Config;

public interface ConfigDao extends JpaRepository<Config, Long> {

	Config findByConfigCategoryAndConfigName(String configCategory, String configName);

}
