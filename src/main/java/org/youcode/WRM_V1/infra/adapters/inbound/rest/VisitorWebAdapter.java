package org.youcode.WRM_V1.infra.adapters.inbound.rest;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.youcode.WRM_V1.app.ports.inbound.VisitorService;
import org.youcode.WRM_V1.utils.DTOs.SuccessDTO;

@RestController
@RequestMapping("/api/v1/visitors")
public class VisitorWebAdapter {

    private final VisitorService visitorService;

    public VisitorWebAdapter(VisitorService visitorService){
        this.visitorService = visitorService;
    }

//
//    @PostMapping()
//    public ResponseEntity<SuccessDTO<>>
}
