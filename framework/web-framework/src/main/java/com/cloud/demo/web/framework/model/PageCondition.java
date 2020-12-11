package com.cloud.demo.web.framework.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @ClassName PageModel
 * @Author JackZhou
 * @Date 2020/2/25  15:37
 **/
@Data
public class PageCondition {
    @ApiModelProperty(value="页数,默认1")
    @Min(1)
    private int page = 1;
    @ApiModelProperty(value="条数，默认10")
    private int size = 10;
}
