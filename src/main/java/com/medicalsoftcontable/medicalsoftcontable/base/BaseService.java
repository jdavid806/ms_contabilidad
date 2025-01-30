package com.medicalsoftcontable.medicalsoftcontable.base;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public abstract class BaseService<T> {

  BaseRepository<T> baseRepository;

  public BaseService(BaseRepository<T> baseRepository) {
    this.baseRepository = baseRepository;
  }

  public List<T> findAll() {
    return baseRepository.findAll();
  }

  public T save(T object) {
    return baseRepository.save(object);
  }

  public T findById(Long id) {
    Optional<T> optional = baseRepository.findById(id);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      return null;
    }
  }

}
