/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.spring1.controllers;


import com.mycompany.spring1.beans.Pracownik;
import com.mycompany.spring1.dao.PracownikDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 *
 * @author student
 */
@Controller
public class PracownikController {
    @Autowired 
    PracownikDao dao; //wstrzyknięcie dao z pliku XML 
    
    @RequestMapping("/addForm") 
    public String showform(Model m){ 
        m.addAttribute("command", new Pracownik()); 
        return "addForm"; //przekiekrowanie do addForm.jsp
    } 
   
    @RequestMapping(value="/save",method = RequestMethod.POST) 
    public String save(@ModelAttribute("pr") Pracownik pr){ 
        dao.save(pr); 
        return "redirect:/viewAll";
        //przekierowanie do endpointa o URL: /viewAll
    } 
    
   /* Metoda pobiera listę pracowników z BD i umieszcza je w modelu */ 
    @RequestMapping("/viewAll") 
    public String viewAll(Model m){ 
        List<Pracownik> list=dao.getAll(); 
        m.addAttribute("list",list); 
        return "viewAll"; //przejście do widoku viewAll.jsp 
    } 
}
