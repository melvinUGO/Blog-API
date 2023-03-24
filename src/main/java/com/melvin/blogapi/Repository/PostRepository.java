package com.melvin.blogapi.Repository;

import com.melvin.blogapi.Entity.PostEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<PostEntity, Integer> {


}
