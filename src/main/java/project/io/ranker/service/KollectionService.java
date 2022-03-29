package project.io.ranker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import project.io.ranker.Repositories.KollectionRepo;
import project.io.ranker.models.ItemModel;
import project.io.ranker.models.KollectionDto;
import project.io.ranker.models.KollectionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KollectionService {

    private final KollectionRepo kollectionRepo;

    public List<KollectionDto> findAll() {
        return kollectionRepo.findAll()
                .stream()
                .map(kollectionModel -> mapToDto(kollectionModel, new KollectionDto()))
                .collect(Collectors.toList());
    }

    public KollectionDto get(Long id) {
        return kollectionRepo.findById(id)
                .map(kollectionModel -> mapToDto(kollectionModel, new KollectionDto()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(KollectionDto kollectionDto) {
        KollectionModel kollectionModel = new KollectionModel();
        mapToEntity(kollectionDto, kollectionModel);
        return kollectionRepo.save(kollectionModel).getId();
    }

    public void update(Long id, KollectionDto kollectionDto) {
        KollectionModel kollectionModel = kollectionRepo.findById(id)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(kollectionDto, kollectionModel);
        kollectionRepo.save(kollectionModel);
    }

    public void delete(final Long id) {
        kollectionRepo.deleteById(id);
    }

    // mappings...
    private KollectionDto mapToDto(KollectionModel kollectionModel,
                                   KollectionDto kollectionDto) {
        ItemModel itemModel = new ItemModel();
        kollectionDto.setId(kollectionModel.getId());
        kollectionDto.setName(kollectionModel.getName());
//        kollectionDto.setItemModel(kollectionModel.getItemModel());
        return kollectionDto;
    }

    private KollectionModel mapToEntity(KollectionDto kollectionDto,
                                        KollectionModel kollectionModel) {
        kollectionModel.setName(kollectionDto.getName());
//        kollectionModel.setItemModel(kollectionDto.getItemModel());
        return kollectionModel;
    }
}
