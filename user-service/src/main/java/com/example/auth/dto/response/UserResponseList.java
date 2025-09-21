package com.example.auth.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserResponseList {
    private List<UserResponse> userResponses;
    private int totalCount;

    public UserResponseList()
    {
        userResponses = new ArrayList<>();
    }

    public void add(UserResponse userResponse)
    {
        userResponses.add(userResponse);
    }
}
