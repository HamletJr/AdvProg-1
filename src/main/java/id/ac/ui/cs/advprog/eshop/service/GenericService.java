package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;

public interface GenericService<T> {
    public T create(T obj);
    public T findById(String id);
    public List<T> findAll();
    public T update(T obj);
    public boolean delete(String id);
}
