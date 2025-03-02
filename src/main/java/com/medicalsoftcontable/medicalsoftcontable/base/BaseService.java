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

  public Optional<T> findById(Long id) {
    return baseRepository.findById(id); 
}

  public void delete(T object) {
    baseRepository.delete(object);
  }

  // Si prefieres el delete por ID
  public void deleteById(Long id) {
    baseRepository.deleteById(id);
  }

}
