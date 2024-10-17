package org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.dao;

import org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.entity.Province;

import java.util.List;

public interface ProvinceDAO {

    public List<Province> listAllProvinces();
    public void insertProvince(Province province);
    public void updateProvince(Province province);
    public void deleteProvince(int id);
    public Province getProvinceById(int id);

    boolean existsProvinceByCode(String code);

    boolean existsProvinceByCodeAndNotId(String code, int id);
}
