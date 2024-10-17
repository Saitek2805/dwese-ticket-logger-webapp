package org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.controllers;

import org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.dao.ProvinceDAO;
import org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.dao.RegionDAO;
import org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.entity.Province;
import org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.entity.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private ProvinceDAO provinceDAO;

    @Autowired
    private RegionDAO regionDAO; // Asegúrate de tener un DAO para las regiones

    private static final Logger logger = LoggerFactory.getLogger(ProvinceController.class);

    @GetMapping
    public String listProvinces(Model model) {
        logger.info("Solicitando la lista de todas las provincias...");
        List<Province> listProvinces = provinceDAO.listAllProvinces();
        logger.info("Se han cargado {} provincias.", listProvinces.size());
        model.addAttribute("listProvinces", listProvinces);
        return "province"; // Nombre de la plantilla Thymeleaf a renderizar
    }

    @GetMapping("/add")
    public String showAddProvinceForm(Model model) {
        logger.info("Mostrando formulario para agregar nueva provincia...");
        Province province = new Province(); // Crear un nuevo objeto provincia
        model.addAttribute("province", province);
        model.addAttribute("regions", regionDAO.listAllRegions()); // Obtener la lista de regiones
        return "province-form"; // Nombre de la plantilla Thymeleaf para el formulario
    }

    @PostMapping("/add")
    public String addProvince(Province province, Model model) {
        logger.info("Agregando nueva provincia: {}", province);
        if (provinceDAO.existsProvinceByCode(province.getCode())) {
            String errorMessage = messageSource.getMessage("msg.province.form.error.codeExists", null, null);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("province", province);
            model.addAttribute("regions", regionDAO.listAllRegions()); // Reenviar lista de regiones
            return "province-form"; // Volver a mostrar el formulario
        }
        provinceDAO.insertProvince(province);
        return "redirect:/provinces"; // Redirigir a la lista de provincias después de agregar
    }

    @GetMapping("/edit")
    public String showEditProvinceForm(@RequestParam("id") int id, Model model) {
        logger.info("Mostrando formulario para editar la provincia con id: {}", id);
        Province province = provinceDAO.getProvinceById(id);
        if (province == null) {
            // Manejar el caso donde no se encuentra la provincia
            logger.warn("No se encontró la provincia con id: {}", id);
            return "redirect:/provinces"; // Redirigir a la lista si no se encuentra
        }
        model.addAttribute("province", province);
        model.addAttribute("regions", regionDAO.listAllRegions()); // Obtener la lista de regiones
        return "province-form"; // Nombre de la plantilla Thymeleaf para el formulario
    }

    @PostMapping("/update")
    public String updateProvince(Province province, Model model) {
        logger.info("Actualizando provincia: {}", province);
        if (provinceDAO.existsProvinceByCodeAndNotId(province.getCode(), province.getId())) {
            String errorMessage = messageSource.getMessage("msg.province.form.error.codeExists", null, null);
            model.addAttribute("errorMessage", errorMessage);
            model.addAttribute("province", province);
            model.addAttribute("regions", regionDAO.listAllRegions()); // Reenviar lista de regiones
            return "province-form"; // Volver a mostrar el formulario
        }
        provinceDAO.updateProvince(province);
        return "redirect:/provinces"; // Redirigir a la lista de provincias después de actualizar
    }

    @PostMapping("/delete")
    public String deleteProvince(@RequestParam("id") int id) {
        logger.info("Eliminando provincia con id: {}", id);
        provinceDAO.deleteProvince(id);
        return "redirect:/provinces"; // Redirigir a la lista de provincias después de eliminar
    }
}
