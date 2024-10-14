package org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.dao;

import org.iesalixar.daw2.keinerhurtado.dwese_ticket_logger_webapp.entity.Region;

import java.util.List;

public interface RegionDAO {
    public List<Region> listAllRegions();
    public void insertRegion(Region region);
    public void updateRegion(Region region);
    public void deleteRegion(int id);
    public Region getRegionById(int id);
    public boolean existsRegionByCode(String code);
    public boolean existsRegionByCodeAndNotId(String code, int id);

}
