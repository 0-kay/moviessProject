package project.io.ranker.service;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import project.io.ranker.Repositories.ItemRepositories;
import project.io.ranker.Repositories.KollectionRepo;
import project.io.ranker.dto.ItemDTO;
import project.io.ranker.models.ItemModel;
import project.io.ranker.models.KollectionModel;

import java.util.List;

import static java.util.stream.Collectors.toList;

@AllArgsConstructor
@Service
public class ItemService {

    private final ItemRepositories itemRepositories;
    private final KollectionRepo kollectionRepo;

    //create item
    @Transactional
    public void CreateItem(ItemDTO itemDTO){
        ItemModel itemModel=mapFromDtoToItem(itemDTO);
        itemRepositories.save(itemModel);

    }
    //delete item
    @Transactional
    public void DeleteItem(Long Id){
            itemRepositories.deleteById(Id);
    }

    //get all items
    @Transactional
    public List<ItemDTO> showallItems() {
        List<ItemModel> item = itemRepositories.findAll();
        return item.stream().map(this::mapFromItemtoDTO)
                .collect(toList());
    }
    //update item
    @Transactional
    public ItemDTO updateItem(Long id, ItemDTO itemDTO) {

        if (itemRepositories.findById(id).isPresent()) {
            ItemModel prevItem = itemRepositories.findById(id).get();
            prevItem.setName(itemDTO.getName());
            prevItem.setImgUrl(itemDTO.getImgUrl());
            prevItem.setPoints(itemDTO.getPoints());
            ItemModel updatedItem = itemRepositories.save(prevItem);

            return new ItemDTO(updatedItem.getId(),updatedItem.getName(),updatedItem.getImgUrl(), updatedItem.getPoints());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // get all items under a certain kollection
    public List<ItemDTO> getKollectionItems(Long id) {
        KollectionModel kollectionModel = kollectionRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return itemRepositories.findByKollectionItems(kollectionModel)
                .stream()
                .map(this::mapFromItemtoDTO).collect(toList());
    }

    //get 2 random items
//    public List<>
    private ItemModel mapFromDtoToItem(ItemDTO itemDTO) {
        ItemModel itemModel = new ItemModel();
        itemModel.setId(itemDTO.getId());
        itemModel.setName(itemDTO.getName());
        itemModel.setImgUrl(itemDTO.getImgUrl());
        itemModel.setPoints(0);
        if(itemDTO.getKollectionItems() != null
                && (itemModel.getKollectionItems() == null
                || !itemModel.getKollectionItems().getId().equals(itemDTO.getKollectionItems()))) {
            KollectionModel kollectionItems = kollectionRepo.findById(itemDTO.getKollectionItems())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kollection not found"));
            itemModel.setKollectionItems(kollectionItems);
        }
        return itemModel;
    }

    private ItemDTO mapFromItemtoDTO(ItemModel itemModel) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(itemModel.getId());
        itemDTO.setName(itemModel.getName());
        itemDTO.setImgUrl(itemModel.getImgUrl());
        itemDTO.setPoints(itemModel.getPoints());
        itemDTO.setKollectionItems(itemModel.getKollectionItems() == null ? null : itemModel.getKollectionItems().getId());
        return itemDTO;
    }

}
