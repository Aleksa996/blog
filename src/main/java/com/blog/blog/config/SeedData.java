package com.blog.blog.config;

import com.blog.blog.models.Post;
import com.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if(posts.size() == 0){
            Post post = new Post();
            post.setTitle("title 1");
            post.setBody("body 1");

            Post post1 = new Post();
            post1.setTitle("title 2");
            post1.setBody("body 2");

            postService.save(post);
            postService.save(post1);

        }

    }
}
