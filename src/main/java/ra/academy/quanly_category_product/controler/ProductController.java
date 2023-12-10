package ra.academy.quanly_category_product.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ra.academy.quanly_category_product.model.entity.Category;
import ra.academy.quanly_category_product.model.entity.Product;
import ra.academy.quanly_category_product.model.service.CategoryService;
import ra.academy.quanly_category_product.model.service.ProductService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    Product product = new Product();
    Category category = new Category();

    @GetMapping("add-product")
    public String addProduct(Model model) {
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryList);
        return "product/add_product";
    }

    @PostMapping("add-product")
    public String addProduct(@ModelAttribute("product") Product product) {
        Product newProduct = productService.saveOrUpdate(product);
        if (newProduct != null) {
            return "redirect:/";
        }
        return "product/add_product";
    }

    @GetMapping("edit_product/{id}")
    public String editProduct(@PathVariable("id") Integer id, Model model) {
        List<Category> categoryList=categoryService.findAll();
        Product editProduct = productService.findById(id);
        if (editProduct != null) {
            model.addAttribute("editProduct", editProduct);
            model.addAttribute("categoryList",categoryList);
            return "/product/edit_product";
        }
        return "redirect:/";
    }
@PostMapping("update-product")
    public String updateProduct(@ModelAttribute("editProduct") Product product){
        Product updateProduct=productService.saveOrUpdate(product);
        if (updateProduct!=null){
            return "redirect:/";
        }
    return "/product/edit_product";
}

@GetMapping("delete_product/{id}")
    public String deleteProduct(@PathVariable("id") Integer id){
        productService.delete(id);
        return "redirect:/";
}



}
