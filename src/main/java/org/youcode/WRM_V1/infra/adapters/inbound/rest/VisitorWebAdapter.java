package org.youcode.WRM_V1.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.WRM_V1.app.ports.inbound.VisitorService;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.CreateAndUpdateVisitorDTO;
import org.youcode.WRM_V1.core.entities.visitor.DTOs.VisitorResponseDTO;
import org.youcode.WRM_V1.utils.DTOs.SuccessDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visitors")
public class VisitorWebAdapter {

    private final VisitorService visitorService;

    public VisitorWebAdapter(VisitorService visitorService){
        this.visitorService = visitorService;
    }

    @PostMapping()
    public ResponseEntity<SuccessDTO<VisitorResponseDTO>> createVisitor(@RequestBody @Valid CreateAndUpdateVisitorDTO req){
        VisitorResponseDTO res = visitorService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Visitor Created Successfully !" , res) , HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessDTO<VisitorResponseDTO>> updateVisitor(@RequestBody @Valid CreateAndUpdateVisitorDTO req , @PathVariable("id") Long id){
        VisitorResponseDTO res = visitorService.update(req , id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Visitor Updated Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SuccessDTO<List<VisitorResponseDTO>>> getAllVisitors(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "5") int size){
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<VisitorResponseDTO> res = visitorService.getAllVisitors(pageRequest);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Visits of Page " + page +" Retrieved Successfully !" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessDTO<VisitorResponseDTO>> getVisitorById(@PathVariable("id") Long id){
        VisitorResponseDTO res = visitorService.getVisitorById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Visitor Retrieved Successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessDTO<VisitorResponseDTO>> delete(@PathVariable("id") Long id){
        VisitorResponseDTO res = visitorService.delete(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Visitor Deleted Successfully !" , res) , HttpStatus.OK);
    }
}
