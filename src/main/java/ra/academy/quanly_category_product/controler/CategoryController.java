package ra.academy.quanly_category_product.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.academy.quanly_category_product.model.entity.Category;
import ra.academy.quanly_category_product.model.service.CategoryService;

@Controller
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    Category category=new Category();

    @GetMapping("add")
    public String add(Model model) {
        model.addAttribute("category", category);
        return "/category/add_category";
    }

    @PostMapping("add")
    public String add(@ModelAttribute("category") Category category) {
        Category newCategory=categoryService.saveOrUpdate(category);
        if (newCategory!=null){
            return "redirect:/";
        }
        return "category/add_category";
    }
    @GetMapping("edit_category/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
category=categoryService.findById(id);
        model.addAttribute("category",category);
        return "/category/edit_category";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("category") Category category){
        Category updateCategory=categoryService.saveOrUpdate(category);
        if (updateCategory!=null){
            return "redirect:/";
        }
        return "/category/edit_category";
    }

    @GetMapping("/delete_category/{id}")
    public String delete(@PathVariable("id") Integer id){
        categoryService.delete(id);
        return "redirect:/";
    }
}
