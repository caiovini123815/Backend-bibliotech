package com.altis.library.dashboard.controllers;

import com.altis.library.dashboard.models.dtos.UserDashboardResponseDTO;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.altis.library.dashboard.models.dtos.AdminDashboardResponseDTO;
import com.altis.library.dashboard.services.DashboardServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardControllers {

    private final DashboardServices dashboardServices;

    public DashboardControllers(
            DashboardServices dashboardServices
    ) {
        this.dashboardServices = dashboardServices;
    }

    @GetMapping("/admin")
    public ResponseEntity<AdminDashboardResponseDTO> adminDashboard() {

        return ResponseEntity.ok(
                dashboardServices.adminDashboard()
        );
    }

    @GetMapping("/user")
    public ResponseEntity<UserDashboardResponseDTO> userDashboard(
            @AuthenticationPrincipal Jwt jwt
    ) {

        Long userId = jwt.getClaim("userId");

        return ResponseEntity.ok(
                dashboardServices.userDashboard(userId)
        );
    }
}