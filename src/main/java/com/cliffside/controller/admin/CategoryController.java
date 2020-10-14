package com.cliffside.controller.admin;

import com.cliffside.entity.BlogCategory;
import com.cliffside.service.CategoryService;
import com.cliffside.util.PageQueryUtil;
import com.cliffside.util.Result;
import com.cliffside.util.ResultGenerator;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 处理分类的控制层
 * @author cliffside
 */
@Controller
@RequestMapping("/admin")
public class CategoryController {

    @Resource
    private CategoryService categoryService;


    @GetMapping("/categories")
    public String categoryPage(HttpServletRequest request) {
        request.setAttribute("path", "categories");
        return "admin/category";
    }

    /**
     * 实现分类列表的分页接口
     *
     * 列表接口负责接收前端传来的分页参数，如 page 、limit 等参数，之后将数据总数和对应页面的数据列表查询出来并封装为分页数据返回给前端。
     * @param params
     * @return
     */
    @RequestMapping(value = "/categories/list",method = RequestMethod.GET)
    @ResponseBody
    public Result list(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page"))||StringUtils.isEmpty(params.get("limit"))){
            return ResultGenerator.genFailResult("参数异常！！！");

        }
        PageQueryUtil pageQueryUtil = new PageQueryUtil(params);
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(categoryService.getBlogCategoryPage(pageUtil));
    }

    /**
     * 添加分类接口
     * 添加接口负责接收前端的 POST 请求并处理其中的参数，接收的参数为 categoryName 字段和 categoryIcon 字段，
     * categoryName 为分类名称，categoryIcon 字段为分类的图标字段。
     * @param categoryName 为分类名称
     * @param categoryIcon 为分类的图标字段
     * @return
     */
    @RequestMapping(value = "/categories/save")
    @ResponseBody
    public Result list(@RequestParam("categoryName") String categoryName ,
                       @RequestParam("categoryIcon") String categoryIcon){

        if (StringUtils.isEmpty(categoryName)){
            return ResultGenerator.genFailResult("请输入分类名称！！！");
        }
        if (StringUtils.isEmpty(categoryIcon)){
            return ResultGenerator.genFailResult("请选择分类图标！！！");
        }
        if (categoryService.saveCategory(categoryName,categoryIcon)){
            return ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("分类名重复");
        }
    }

    /**
     *
     * 删除分类接口
     * 删除接口负责接收前端的分类删除请求，处理前端传输过来的数据后，
     * 将这些记录从数据库中删除，这里的“删除”功能并不是真正意义上的删除，而是逻辑删除，
     * 我们将接受的参数设置为一个数组，可以同时删除多条记录，
     * 只需要在前端将用户选择的记录 id 封装好再传参到后端即可。
     * @param ids
     * @return
     */
    @RequestMapping(value = "/categories/delete",method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids){
        if(ids.length <1){
            return ResultGenerator.genFailResult("参数异常！！！");
        }
        if (categoryService.deleteBatch(ids)){
            return  ResultGenerator.genSuccessResult();
        }else {
            return ResultGenerator.genFailResult("删除失败！！！");
        }
    }


    /**
     *
     * @param categoryId
     * @param categoryName
     * @param categoryIcon
     * @return 是否修改成功分类
     */
    @RequestMapping(value = "/categories/update", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@RequestParam("categoryId") Integer categoryId,
                         @RequestParam("categoryName") String categoryName,
                         @RequestParam("categoryIcon") String categoryIcon) {
        if (categoryId == null || categoryId < 1) {
            return ResultGenerator.genFailResult("非法参数！");
        }
        if (StringUtils.isEmpty(categoryName)) {
            return ResultGenerator.genFailResult("请输入分类名称！");
        }
        if (StringUtils.isEmpty(categoryIcon)) {
            return ResultGenerator.genFailResult("请选择分类图标！");
        }
        if (categoryService.updateCategory(categoryId, categoryName, categoryIcon)) {
            return ResultGenerator.genSuccessResult();
        } else {
            return ResultGenerator.genFailResult("分类名称重复");
        }
    }

    /**
     * 详情
     */
    @GetMapping("/categories/info/{id}")
    @ResponseBody
    public Result info(@PathVariable("id") Integer id) {
        if (id == null || id < 1) {
            return ResultGenerator.genFailResult("非法参数！");
        }
        BlogCategory category = categoryService.selectById(id);
        return ResultGenerator.genSuccessResult(category);
    }

    /**
     * 根据 id 获取详情接口，路径为 categories/info/{id}，请求方法为 GET；
     * 分类修改接口，路径为 categories/update，请求方法为 POST。
     */
}
