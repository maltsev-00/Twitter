package com.simple.twitter;

import com.simple.twitter.enums.UserRole;
import com.simple.twitter.model.AdditionalTweets;
import com.simple.twitter.model.Like;
import com.simple.twitter.model.Twitter;
import com.simple.twitter.model.User;
import com.simple.twitter.repository.TwitterRepository;
import com.simple.twitter.repository.UserRepository;
import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@Data
public class TwitterApplication implements CommandLineRunner {

private final TwitterRepository twitterRepository;

	public static void main(String[] args) {
		SpringApplication.run(TwitterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

//		Set<Message> messages = new HashSet<>();
//		messages.add(new Message(UUID.randomUUID(),"Text message 42"));
//
//		Set<Product> products = new HashSet<>();
//		products.add(new Product(UUID.randomUUID(),"Text prodcut 12"));
//
//        Post post = Post.builder()
//			 .id(UUID.randomUUID())
//			 .text("text post 25")
//			 .messages(messages)
//			 .products(products)
//			 .build();
//
//     //   postRepo.save(post);
//
//
//		System.out.println(postRepo.findPostByMessagesText("Text message 42"));



	}


}
