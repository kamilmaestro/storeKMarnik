package com.marnikkamil.store.security.subject.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  SubjectRepository subjectRepository;

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    final Subject subject = subjectRepository.findByUsername(userName)
        .orElseThrow(() -> new UsernameNotFoundException("Can not find user with such username: " + userName));

    return CustomUserDetails.builder()
        .userId(subject.getId().toString())
        .username(subject.getUsername())
        .password(subject.getPassword())
        .role(CustomUserDetails.Role.valueOf(subject.getRole().toString()))
        .build();
  }

}
