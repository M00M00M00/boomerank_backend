package boomerank.service;

import boomerank.entity.Apart;
import boomerank.repository.ApartRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ApartService {
    private final ApartRepository apartRepository;

    public ApartService(ApartRepository apartRepository) {
        this.apartRepository = apartRepository;
    }

    public List<Map<String, Object>> getRank(){
//        List<Apart> aparts = apartRepository.findAll();
        List<Map<String, Object>> rankGeo1 = apartRepository.getRankGeo2();
        return rankGeo1;
    }
}
