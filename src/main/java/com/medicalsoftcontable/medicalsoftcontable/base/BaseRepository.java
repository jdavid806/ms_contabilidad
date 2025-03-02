package com.medicalsoftcontable.medicalsoftcontable.base;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Long> {
  @SuppressWarnings("null")
  @Override
  List<T> findAll();  

  @Override
  long count();

}
