package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;


public class PostRepositoryStubImpl implements PostRepository {
  private static final Map<Long, Post> REPOSITORY = new ConcurrentHashMap<>();
  private static final AtomicLong ID_COUNTER = new AtomicLong();

  public List<Post> all() {
      return new ArrayList<>(REPOSITORY.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.of(REPOSITORY.get(id));
  }

  public Post save(Post post) {
    if (REPOSITORY.containsKey(post.getId()) && post.getId() != 0){
      REPOSITORY.get(post.getId()).setContent(post.getContent());
    }else{
      post.setId(ID_COUNTER.getAndIncrement());
      REPOSITORY.put(ID_COUNTER.get(), post);
    }
    return post;
  }

  public void removeById(long id) {
      REPOSITORY.remove(id);
  }
}
