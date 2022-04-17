package com.xpman.controller.view;

import com.xpman.model.SubCategory;
import com.xpman.service.SubCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("subCategory")
public class SubCategoryViewController {

    private final Logger logger = LoggerFactory.getLogger(SubCategoryViewController.class);

    @Autowired
    SubCategoryService subCategoryService;

    @GetMapping("/health")
    public String health(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        try {
            model.addAttribute("name", name);
            return "subCategory/health";
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
            logger.info("Requesting subCategory creation form");
            model.addAttribute("subCategory", new SubCategory());
            List<String> categorys = subCategoryService.getAllCategorysName();
            logger.info("Categorys {}", categorys);
            model.addAttribute("categorys", categorys);
            return "subCategory/create/create-form";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/all-subCategorys")
    public String getAllSubCategorys(Model model) {
        try {
            logger.info("Get all subCategorys");
            List<SubCategory> subCategorys = subCategoryService.getAll();
            model.addAttribute("subCategorys", subCategorys);
            System.out.println(subCategorys);
            return "subCategory/read/all-subCategorys";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/create")
    public String subCategoryFormSubmit(@ModelAttribute SubCategory subCategory, Model model) {
        try {
            subCategoryService.create(subCategory);
            model.addAttribute("subCategory", subCategory);
            logger.info("SubCategory created :{}", subCategory.toString());
            return "subCategory/create/create-success";
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
            SubCategory subCategory = subCategoryService.getSubCategoryById(id);
            logger.info("Edit form requested for SubCategory {} with id {} ", subCategory.getName(), id);
            model.addAttribute("subCategory", subCategory);
            return "subCategory/update/edit-form";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/edit")
    public String editFormSubmit(@ModelAttribute SubCategory subCategory, Model model) {
        logger.info("SubCategory Edit Request Received");
        if(subCategory == null){
            logger.info("SubCategory is null");
            model.addAttribute("error", "SubCategory is null. Please try again with correct values");
            return "common/client-error";
        }
        else if(subCategory.getId() == null || subCategory.getName() == null || subCategory.getIcon() == null){
            logger.info("Empty fields {} ", subCategory.toString());
            model.addAttribute("error", "Some fields are null. Please try again with correct values");
            return "common/client-error";
        }
        try {
            subCategoryService.updateSubCategoryById(subCategory);
            model.addAttribute("subCategory", subCategory);
            logger.info("SubCategory edited :{}", subCategory);
            return "subCategory/update/edit-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @GetMapping("/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Integer id, Model model) {
        logger.info("SubCategory Delete Confirmation Request Received");
        try {
            SubCategory subCategory = subCategoryService.getSubCategoryById(id);
            logger.info("Delete requested for SubCategory {} with id {} ", subCategory.getName(), id);
            model.addAttribute("subCategory", subCategory);
            return "subCategory/delete/delete-confirmation";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute SubCategory subCategory, Model model) {
        if(subCategory.getId() == null){
            logger.info("SubCategory or SubCategory Id is null");
            model.addAttribute("error", "SubCategory or SubCategory Id is null");
            return "common/client-error";
        }
        try {
            subCategoryService.deleteSubCategoryById(subCategory.getId());
            logger.info("SubCategory deleted with id :{}", subCategory.getId());
            return "subCategory/delete/delete-success";
        }
        catch (Exception e){
            model.addAttribute("error", e);
            e.printStackTrace();
            return "common/server-error";
        }
    }
}
