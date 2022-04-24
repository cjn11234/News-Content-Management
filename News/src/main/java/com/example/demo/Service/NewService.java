package com.example.demo.Service;

import com.example.demo.Entity.New;
import com.example.demo.Entity.User;
import com.example.demo.Repository.NewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Transactional
@Service
public class NewService {
    @Autowired
    private NewRepository newRepository;
    public boolean exists(New article) {
        return newRepository.existsBytitle(article.getTitle());
    }

    public List<New> findByName(String author) {
        return newRepository.findByName(author);
    }
    public New findByTitle(String title) {
        return newRepository.findByTitle(title);
    }
    public List<New> findAll() {
        return newRepository.findAll();
    }
    public boolean insert(New article) {
        newRepository.save(article);
        return true;
    }
    public List<New> search(String str){
        return newRepository.findByString(str);
    }
}
