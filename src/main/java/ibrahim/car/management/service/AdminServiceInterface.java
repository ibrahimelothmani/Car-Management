package ibrahim.car.management.service;

import ibrahim.car.management.model.Admin;

import java.util.List;

public interface AdminServiceInterface {

    Admin addAdmin(Admin admin);
    List<Admin> getAllAdmin();
    Admin findAdminById(Long id);
    Admin updateAdmin(Admin admin);
    void deleteAdmin(Long id);
}
