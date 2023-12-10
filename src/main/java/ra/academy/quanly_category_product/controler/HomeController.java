package ra.academy.quanly_category_product.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ra.academy.quanly_category_product.model.entity.Category;
import ra.academy.quanly_category_product.model.entity.Product;
import ra.academy.quanly_category_product.model.service.CategoryService;
import ra.academy.quanly_category_product.model.service.ProductService;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @GetMapping("/")
    public String home(Model model){
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        List<Product> productList=productService.findAll();
        model.addAttribute("productList",productList);
        return "/home";
    }
}
