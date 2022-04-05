package project.io.ranker.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.io.ranker.dto.KollectionDto;
import project.io.ranker.service.KollectionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/kollections")
@RequiredArgsConstructor
public class KollectionController implements SecuredRestController{

    private final KollectionService kollectionService;

    @GetMapping
    public ResponseEntity<List<KollectionDto>> getAllKollections() {
        return ResponseEntity.ok(kollectionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<KollectionDto> getKollection(@PathVariable Long id) {
        return ResponseEntity.ok(kollectionService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createKollection(
            @RequestBody @Valid KollectionDto kollectionDto) {
        return new ResponseEntity<>(kollectionService.create(kollectionDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateKollection(
        @PathVariable Long id, @RequestBody @Valid KollectionDto kollectionDto) {
        kollectionService.update(id, kollectionDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteKollection (@PathVariable Long id) {
        kollectionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
