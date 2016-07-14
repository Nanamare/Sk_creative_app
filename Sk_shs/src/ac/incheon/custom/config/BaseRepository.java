package ac.incheon.custom.config;

import org.apache.http.HttpException;


public interface BaseRepository<T> {

    int create(T t) throws HttpException;

    int update(T t) throws HttpException;

    int delete(T t);
}
