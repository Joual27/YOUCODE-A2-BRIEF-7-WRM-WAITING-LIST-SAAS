package org.youcode.WRM_V1.infra.adapters.inbound.rest;


import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.youcode.WRM_V1.app.ports.inbound.WaitingListService;
import org.youcode.WRM_V1.core.entities.waitingList.DTOs.CreateAndUpdateWaitingListDTO;
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
    public ResponseEntity<SuccessDTO<WaitingListResponseDTO>> createWaitingList(@RequestBody @Valid CreateAndUpdateWaitingListDTO req){
        WaitingListResponseDTO res = waitingListService.save(req);
        return new ResponseEntity<>(new SuccessDTO<>("success" ,"Waiting List Created Successfully " , res) , HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<SuccessDTO<List<WaitingListResponseDTO>>> getAllWaitingLists(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<WaitingListResponseDTO> res = waitingListService.getAll(pageable);
        return new ResponseEntity<>(new SuccessDTO<>("success", "Waiting List Of Page : " + page + " Fetched Successfully !", res.getContent()), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessDTO<WaitingListResponseDTO>> updateWaitingList(@PathVariable Long id , @RequestBody CreateAndUpdateWaitingListDTO req){
       WaitingListResponseDTO res = waitingListService.update(id , req);
       return new ResponseEntity<>(new SuccessDTO<>("success" , "Waiting List "+  id +" Updated Successfully !" , res) , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessDTO<WaitingListResponseDTO>> getWaitingListByID(@PathVariable Long id){
        WaitingListResponseDTO res = waitingListService.getWaitingListById(id);
        return new ResponseEntity<>(new SuccessDTO<>("success" , "Waiting List "+  id +" Retrieved Successfully !" , res) , HttpStatus.OK);
    }

}
