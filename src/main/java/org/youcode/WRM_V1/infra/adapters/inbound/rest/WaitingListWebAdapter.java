package org.youcode.WRM_V1.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.WRM_V1.app.ports.inbound.WaitingListService;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.CreateWaitingListDTO;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.WaitingListResponseDTO;
import org.youcode.WRM_V1.utils.DTOs.SuccessDTO;

import java.util.List;


@RestController
@RequestMapping("api/v1/waiting-lists")
public class WaitingListWebAdapter {

    private final WaitingListService waitingListService;

    public WaitingListWebAdapter(WaitingListService waitingListService){
        this.waitingListService = waitingListService;
    }

    @PostMapping()
    public ResponseEntity<SuccessDTO<WaitingListResponseDTO>> createWaitingList(@RequestBody @Valid CreateWaitingListDTO req){
        WaitingListResponseDTO res = waitingListService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" ,"Waiting List Created Successfully " , res) , HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SuccessDTO<List<WaitingListResponseDTO>>> getAllWaitingLists(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<WaitingListResponseDTO> res = waitingListService.getAll(pageable);
        return new ResponseEntity<>(new SuccessDTO<>("success", "Waiting List Of Page : " + page + " Fetched Successfully !", res.getContent()), HttpStatus.OK);
    }

}
