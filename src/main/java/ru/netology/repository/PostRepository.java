package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
  private static final Set<Post> REPOSITORY = ConcurrentHashMap.newKeySet();
  private static final AtomicLong ID_COUNTER = new AtomicLong();

  public List<Post> all() {
      return new ArrayList<>(REPOSITORY);
  }

  public Optional<Post> getById(long id) {
    return REPOSITORY.stream().filter(x -> x.getId() == id).findFirst();
  }

  public Post save(Post post) {
    if (post.getId() == 0){
      post.setId(ID_COUNTER.getAndIncrement());
      REPOSITORY.add(post);
    }else{
      Optional<Post> currentPost = REPOSITORY.stream().filter(x -> x.getId() == post.getId()).findFirst();
      if(currentPost.isPresent()){
        currentPost.get().setContent(post.getContent());
      } else {
        post.setId(ID_COUNTER.getAndIncrement());
        REPOSITORY.add(post);
      }
    }
    return post;
  }

  public void removeById(long id) {
    REPOSITORY.removeIf(x -> x.getId() == id);
  }
}
