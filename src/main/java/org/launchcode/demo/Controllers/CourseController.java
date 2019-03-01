package org.launchcode.demo.Controllers;

import org.launchcode.demo.models.Course;
import org.launchcode.demo.models.data.CourseDao;
import org.launchcode.demo.models.data.RecipeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private RecipeDao recipeDao;

    @RequestMapping(value="")
    public String index(Model model){
        model.addAttribute("title", "Courses");
        model.addAttribute("courses", courseDao.findAll());
        return "course/index";
    }

    @RequestMapping(value="add", method = RequestMethod.GET)
    public String displayAddForm(Model model){
        model.addAttribute("title", "Add Course");
        model.addAttribute("course", new Course());
        return "course/add";
    }

    @RequestMapping(value="add", method = RequestMethod.POST)
    public String processAddForm(Model model, @ModelAttribute @Valid Course course, Errors errors){
        if (errors.hasErrors()){
            model.addAttribute("title", "Add Course");
            return "course/add";
        }
        courseDao.save(course);
        model.addAttribute("message", "Successfully added!");
        return "course/message";
        //return "redirect:";
    }

    // delete each course
    @RequestMapping(value = "delete/{courseId}")
    public String delete(@PathVariable int courseId, Model model){
        courseDao.delete(courseId);
        model.addAttribute("message", "Successfully deleted!");
        return "course/message";
        //return "redirect:/course";
    }
}
