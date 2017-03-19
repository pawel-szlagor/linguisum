package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon;

import java.util.List;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;

/**
 * Created by Pawel on 2017-03-17.
 */
public interface HolonService {
    void save(List<HolonDto> holons);

    void save(HolonDto holon);

    List<HolonDto> findAll();

    List<HolonDto> findByRelevanceBetween(Double gte, Double le);
}
