package project.io.ranker.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.io.ranker.dto.ItemDTO;
import project.io.ranker.service.ItemService;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path= "/items")
public class ItemController {

    public final ItemService itemService;

    //get all items
    @CrossOrigin
    @GetMapping(path = "/allItems")//show all posts //anyone can view
    public ResponseEntity<List<ItemDTO>> AllPosts(){
        return new ResponseEntity<>(itemService.showallItems(), HttpStatus.OK);
    }

    //update item
    @CrossOrigin
    @PutMapping(path = "/update/{id}")
    public ResponseEntity<String> UpdateItem(
            @PathVariable Long id,
            @RequestBody ItemDTO itemDTO){
        itemService.updateItem(id,itemDTO);
        return new ResponseEntity<>("item Updated",HttpStatus.OK);
    }

    //delete item
    @CrossOrigin
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> DeleteItem(@PathVariable Long id){
        itemService.DeleteItem(id);
        return new ResponseEntity<>("item Deleted",HttpStatus.OK);
    }
    //create item
    @CrossOrigin
    @PostMapping(path = "/create-item")
    public ResponseEntity<String> CreateItem(@RequestBody ItemDTO itemDTO){
        itemService.CreateItem(itemDTO);
        return new ResponseEntity<>("item added!",HttpStatus.OK);
    }

}
