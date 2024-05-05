package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.entity.RcHomeworkEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcHomeworkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 17:15
 **/
@Api("作业增删改查")
@Slf4j
@RequestMapping("/homework")
@RestController
public class RcHomeworkController {
    @Autowired
    private RcHomeworkService rcHomeworkService;


    @ApiOperation("通过选课id查询作业")
    @PostMapping("/getHomeworkByScId")
    public ResultVO<RcHomeworkEntity> getHomeworkByScId(@RequestParam("scId") Integer scId) {
        return rcHomeworkService.getHomeworkByScId(scId);
    }

    @ApiOperation("新增作业")
    @PostMapping("/add")
    public ResultVO<String> addHomework(@RequestBody RcHomeworkEntity entity){
        return rcHomeworkService.addHomework(entity);
    }

    @ApiOperation("更新作业")
    @PostMapping("/update")
    public ResultVO<String> updateHomework(@RequestBody RcHomeworkEntity entity){
        return rcHomeworkService.updateHomework(entity);
    }

    @ApiOperation("删除作业")
    @PostMapping("/delete")
    public ResultVO<String> deleteHomework(@RequestParam("homeworkId") String homeworkId){
        return rcHomeworkService.deleteHomework(homeworkId);
    }
}
