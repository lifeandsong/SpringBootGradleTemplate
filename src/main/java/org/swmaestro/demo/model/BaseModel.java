package org.swmaestro.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Base Model
 *
 * @since	2022-06-29
 * @author	ywkim
 */
@Data
public class BaseModel {

    @ApiModelProperty(value="사용 유무", example="Y")
    @JsonProperty(access = JsonProperty.Access.AUTO)
    protected String used;

/*
    @ApiModelProperty(name = "createdAt", value = "생성 일시", dataType = "Date", example = "2022-06-30 09:00:00")
    protected Date createdAt;

    @ApiModelProperty(name = "updatedAt", value = "최종 수정 일시", dataType = "Date", example = "2022-06-30 09:00:00")
    protected Date updatedAt;
 */

}
