package com.xpman.controller.view;

import com.xpman.model.Category;
import com.xpman.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryViewController {

    private final Logger logger = LoggerFactory.getLogger(CategoryViewController.class);

    @Autowired
    CategoryService categoryService;

    @GetMapping("/health")
    public String health(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        try {
            model.addAttribute("name", name);
            return "category/health";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/create-form")
    public String createForm(Model model) {
        try {
            logger.info("Requesting category creation form");
            model.addAttribute("category", new Category());
            return "category/create/create-form";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/all-categorys")
    public String getAllCategorys(Model model) {
        try {
            logger.info("Get all categorys");
            List<Category> categorys = categoryService.read();
            model.addAttribute("categorys", categorys);
            return "category/read/all-categorys";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/create")
    public String categoryFormSubmit(@ModelAttribute Category category, Model model) {
        try {
            categoryService.write(category);
            model.addAttribute("category", category);
            logger.info("Category created :{}", category.toString());
            return "category/create/create-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/edit-form/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        try {
            Category category = categoryService.getCategoryById(id);
            logger.info("Edit form requested for Category {} with id {} ", category.getName(), id);
            model.addAttribute("category", category);
            return "category/update/edit-form";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/edit")
    public String editFormSubmit(@ModelAttribute Category category, Model model) {
        logger.info("Category Edit Request Received");
        if(category == null){
            logger.info("Category is null");
            model.addAttribute("error", "Category is null. Please try again with correct values");
            return "common/client-error";
        }
        else if(category.getId() == null || category.getName() == null || category.getIcon() == null){
            logger.info("Empty fields {} ", category.toString());
            model.addAttribute("error", "Some fields are null. Please try again with correct values");
            return "common/client-error";
        }
        try {
            categoryService.updateCategoryById(category);
            model.addAttribute("category", category);
            logger.info("Category edited :{}", category);
            return "category/update/edit-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Integer id, Model model) {
        logger.info("Category Delete Confirmation Request Received");
        try {
            Category category = categoryService.getCategoryById(id);
            logger.info("Delete requested for Category {} with id {} ", category.getName(), id);
            model.addAttribute("category", category);
            return "category/delete/delete-confirmation";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Category category, Model model) {
        if(category.getId() == null){
            logger.info("Category or Category Id is null");
            model.addAttribute("error", "Category or Category Id is null");
            return "common/client-error";
        }
        try {
            categoryService.deleteCategoryById(category.getId());
            logger.info("Category deleted with id :{}", category.getId());
            return "category/delete/delete-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }
}
