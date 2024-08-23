package com.blog.blog.config;

import com.blog.blog.models.Account;
import com.blog.blog.models.Post;
import com.blog.blog.services.AccountService;
import com.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(String... args) throws Exception {
        List<Post> posts = postService.getAll();

        if(posts.size() == 0){

            Account acc1 = new Account();
            Account acc2 = new Account();

            acc1.setFirstName("user");
            acc1.setLastName("user");
            acc1.setEmail("user.user@domain.com");
            acc1.setPassword("password");

            acc2.setFirstName("admin");
            acc2.setLastName("admin");
            acc2.setEmail("admin.admin@gmail.com");
            acc2.setPassword("password");

            accountService.save(acc1);
            accountService.save(acc2);

            Post post = new Post();
            post.setTitle("title 1");
            post.setBody("body 1");
            post.setAccount(acc1);

            Post post1 = new Post();
            post1.setTitle("title 2");
            post1.setBody("body 2");
            post1.setAccount(acc2);

            postService.save(post);
            postService.save(post1);



        }

    }
}
