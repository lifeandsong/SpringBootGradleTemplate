package org.swmaestro.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Base Model
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Data
public class Member extends BaseModel {

    @ApiModelProperty(value = "사용자 아이디", example = "gdhong")
    private String id;

    @ApiModelProperty(value = "비밀번호", example = "swMAE12#$")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ApiModelProperty(value = "이름", example = "홍길동")
    private String name;

}
