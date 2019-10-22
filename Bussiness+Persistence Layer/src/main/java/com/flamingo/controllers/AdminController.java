package com.flamingo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flamingo.entities.Admin;
import com.flamingo.services.AdminServices;

@RestController
public class AdminController {
	@Autowired
//	@Qualifier("admindao")
	private AdminServices adminService;

	@GetMapping("/admin/{id}")
	public Admin findAdminById(@PathVariable("id") int adminId) {
		return adminService.findAdminById(adminId);
	}

	@PostMapping("/admin/add")
	public String add(@RequestBody Admin admin) {
		adminService.addAdmin(admin);
		return "new admin is  : " + admin.getAdminId();

	}

	@PutMapping("/admin/update")
	public String update(@RequestBody Admin admin) {
		if (adminService.findAdminById(admin.getAdminId())!= null) {
			adminService.updateAdmin(admin);

			return "admin updated successfuly :" + admin.getAdminId();
		} else {
			return "sorry admin not found!";
		}
	}
	@DeleteMapping("/admin/remove")
	public String removeAdmin(@RequestBody Admin admin) {
		if (adminService.findAdminById(admin.getAdminId()) != null) {
			adminService.removeAdmin(admin);

			return "admin deleted successfuly :" + admin.getAdminId();
		} else {
			return "sorry admin not found!";
		}
	}
}