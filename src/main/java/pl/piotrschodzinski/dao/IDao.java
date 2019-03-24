package pl.piotrschodzinski.dao;

import java.util.ArrayList;

public interface IDao<T> {

    T create(T object);

    T readById(int id);

    ArrayList<T> readAll();

    void update(T object, int id);

    void delete(int id);
}
