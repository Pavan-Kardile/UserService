package com.micro.user.service.UserService.entities;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Rating {

    private String ratingId;
    private String userID;
    private String hotelId;
    private int rating;
    private String feedback;
}
