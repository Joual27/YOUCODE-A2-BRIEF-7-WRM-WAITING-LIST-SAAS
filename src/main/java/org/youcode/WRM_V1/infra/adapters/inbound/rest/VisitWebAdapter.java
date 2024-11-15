package org.youcode.WRM_V1.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.WRM_V1.app.ports.inbound.VisitService;
import org.youcode.WRM_V1.core.entities.embeddables.VisitKey;
import org.youcode.WRM_V1.core.entities.visit.DTOs.CreateVisitDTO;
import org.youcode.WRM_V1.core.entities.visit.DTOs.VisitResponseDTO;
import org.youcode.WRM_V1.utils.DTOs.SuccessDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/visits")
public class VisitWebAdapter {
    private final VisitService visitService;

    public VisitWebAdapter(VisitService visitService){
        this.visitService = visitService;
    }

    @PostMapping()
    public ResponseEntity<SuccessDTO<VisitResponseDTO>> createVisit(@RequestBody @Valid CreateVisitDTO req){
        VisitResponseDTO res = visitService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "visit created successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SuccessDTO<List<VisitResponseDTO>>> getAllVisits(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size ){
        PageRequest pageRequest = PageRequest.of(page , size);
        Page<VisitResponseDTO> res = visitService.getAllVisits(pageRequest);
        return new ResponseEntity<>(new SuccessDTO<>("success" ,"Retrieved Page "+ page +" Successfully" , res.getContent()) , HttpStatus.OK);
    }

    @GetMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<SuccessDTO<VisitResponseDTO>> getVisitByCredentials(@PathVariable("visitorId") Long visitorId , @PathVariable("waitingListId") Long waitingListId){
        VisitResponseDTO res = visitService.getVisitById(visitorId , waitingListId);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Visit Retrieved successfully !" , res) , HttpStatus.OK);
    }

    @DeleteMapping("/{visitorId}/{waitingListId}")
    public ResponseEntity<SuccessDTO<VisitResponseDTO>> deleteVisit(@PathVariable("visitorId") Long visitorId , @PathVariable("waitingListId") Long waitingListId){
        VisitResponseDTO res = visitService.delete(visitorId , waitingListId);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Visit Deleted successfully !" , res) , HttpStatus.OK);
    }

}
