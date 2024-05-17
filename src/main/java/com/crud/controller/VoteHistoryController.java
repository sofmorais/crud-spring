package com.crud.controller;

import com.crud.model.VoteHistory;
import com.crud.model.dto.CreateVotacaoRequest;
import com.crud.service.VoteHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("api/history")
@RestController
public class VoteHistoryController {

    private final VoteHistoryService historyService;

    @PostMapping
    public ResponseEntity<VoteHistory> create(@RequestBody CreateVotacaoRequest request) {
        this.historyService.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<VoteHistory>> findAll() {
        this.historyService.findAll();
        return ResponseEntity.ok().build();
    }
}
