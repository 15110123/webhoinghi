package com.confproject.confproject.dao;

import com.confproject.confproject.model.News;
import org.springframework.data.repository.CrudRepository;

public interface NewsDAO extends CrudRepository<News, Integer> {

}
