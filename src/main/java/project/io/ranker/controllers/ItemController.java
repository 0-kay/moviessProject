package project.io.ranker.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.io.ranker.dto.ItemDTO;
import project.io.ranker.models.ItemModel;
import project.io.ranker.service.ItemService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping(path= "/api/items")
public class ItemController {

    public final ItemService itemService;

    //get all items
    @CrossOrigin
    @GetMapping(path = "/")//show all posts //anyone can view
    public ResponseEntity<List<ItemDTO>> AllPosts(){
        return new ResponseEntity<>(itemService.showallItems(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping(path = "/kollection-items/{id}")
    public ResponseEntity<List<ItemDTO>> kollectionItems(@PathVariable Long id) {
        return new ResponseEntity<>(itemService.getKollectionItems(id), HttpStatus.OK);
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
    //random item
    @CrossOrigin
    @GetMapping(path = "/{id}/vote")
    public ResponseEntity<List<Optional<ItemDTO>>> voterandom(@PathVariable Long id){
        return new ResponseEntity<>( itemService.getRandomItem(id), HttpStatus.OK);
    }

}
