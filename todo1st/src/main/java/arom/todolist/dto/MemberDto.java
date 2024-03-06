package arom.todolist.dto;

import arom.todolist.entity.Authority;
import arom.todolist.entity.Member;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String nickname;

    private String email;

}
