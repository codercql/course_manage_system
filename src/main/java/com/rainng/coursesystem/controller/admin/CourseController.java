package com.rainng.coursesystem.controller.admin;

import com.alibaba.fastjson.JSONObject;
import com.rainng.coursesystem.config.themis.annotation.Admin;
import com.rainng.coursesystem.controller.BaseController;
import com.rainng.coursesystem.model.entity.CourseEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.admin.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Api("课程管理")
@Admin(Admin.COURSE_MANAGE)
@RequestMapping("/admin/course")
@RestController
public class CourseController extends BaseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @ApiOperation("新增课程")
    @PostMapping("/addCourse")
    public ResultVO<String> addCourse(@RequestParam("entity") String entity,
                              @RequestParam(value="multipartFile",required = false)MultipartFile multipartFile){
        CourseEntity courseEntity = JSONObject.parseObject(entity, CourseEntity.class);
        if(null != multipartFile){
            try {
                byte[] bytes = multipartFile.getBytes();
                courseEntity.setCover(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return service.create(courseEntity);
    }
    @PostMapping
    public ResultVO create(@RequestBody @Validated CourseEntity entity) {
        return service.create(entity);
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @PutMapping
    public ResultVO update(@RequestBody @Validated CourseEntity entity) {
        return service.update(entity);
    }

    @ApiOperation("更新课程")
    @PostMapping("/updateCourse")
    public ResultVO<String> updateCourse(@RequestParam("entity") String entity,
                              @RequestParam(value="multipartFile",required = false)MultipartFile multipartFile){
        CourseEntity courseEntity = JSONObject.parseObject(entity, CourseEntity.class);
        if(null != multipartFile){
            try {
                byte[] bytes = multipartFile.getBytes();
                courseEntity.setCover(bytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return service.update(courseEntity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return service.getPageCount(departmentName, teacherName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String teacherName, String name) {
        return service.getPage(1, departmentName, teacherName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String teacherName, String name) {
        return service.getPage(index, departmentName, teacherName, name);
    }

    @ApiOperation("通过课程Id获取封面")
    @GetMapping("/getCoverByCourseId")
    public ResultVO<String> getCoverByCourseId(@RequestParam("courseId") Integer courseId){
        return service.getCoverByCourseId(courseId);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }
}
