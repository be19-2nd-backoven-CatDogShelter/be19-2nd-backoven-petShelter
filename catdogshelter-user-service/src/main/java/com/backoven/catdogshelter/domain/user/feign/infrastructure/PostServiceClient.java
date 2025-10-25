package com.backoven.catdogshelter.domain.user.feign.infrastructure;

import com.backoven.catdogshelter.domain.shelterhead.feign.dto.VolunteerAssociationDTO;
import com.backoven.catdogshelter.domain.user.feign.dto.AdoptionUserResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "CATDOGSHELTER-POST-SERVICE"
            ,url = "localhost:8000"
            , configuration = FeignClientConfig.class)
public interface PostServiceClient {
    @GetMapping("/adoption-post/user/{userId}/adoption")
    List<AdoptionUserResponseDTO> getAdoptionUser(@PathVariable("userId") Integer userId);

    @GetMapping("/post-service/association-posts/shelter-heads/{headId}/associations")
    List<VolunteerAssociationDTO> getHeadsAssociations(@PathVariable("headId") Integer headId);
}


//@FeignClient(name = "CATDOGSHELTER-POST-SERVICE"
//            ,url = "localhost:8000"
//            , configuration = FeignClientConfig.class)
//public interface PostServiceClient {
//    @GetMapping("/post-service/mypage/{userId}/posts")
//    List<ResponsePostDTO> getUserPosts(@PathVariable String userId);
//}
