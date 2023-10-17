package com.marnikkamil.store

import com.marnikkamil.store.security.subject.domain.CustomUserDetails
import com.marnikkamil.store.security.subject.dto.SubjectDto
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder

trait SecurityContextProvider {

  void logInUser(SubjectDto user) {
    CustomUserDetails authenticatedUser = CustomUserDetails.builder()
        .userId(user.userId)
        .username(user.username)
        .role(CustomUserDetails.Role.valueOf(user.getRole().toString()))
        .build()
    SecurityContextHolder.getContext().setAuthentication(
        new UsernamePasswordAuthenticationToken(authenticatedUser, null)
    )
  }

}
