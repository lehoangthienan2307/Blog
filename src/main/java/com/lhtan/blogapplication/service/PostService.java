package com.lhtan.blogapplication.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhtan.blogapplication.entity.PostEntity;
import com.lhtan.blogapplication.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Optional<PostEntity> getById(Long id)
	{
		return postRepository.findById(id);
	}
	public List<PostEntity> getAll()
	{
		return postRepository.findAll();
	}
	public PostEntity save(PostEntity postEntity)
	{
		if (postEntity.getId() == null)
		{
			postEntity.setCreatedAt(LocalDateTime.now());
		}
		postEntity.setUpdatedAt(LocalDateTime.now());
		return postRepository.save(postEntity);
	}
	public void delete(PostEntity post) {
        postRepository.delete(post);
    }
}
