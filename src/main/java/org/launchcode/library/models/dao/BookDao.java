package org.launchcode.library.models.dao;

import org.launchcode.library.models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface BookDao extends CrudRepository<Book, Integer> {
    List<Book> findbyAuthor(int authorid);

    Book findByUid(int uid);
}
