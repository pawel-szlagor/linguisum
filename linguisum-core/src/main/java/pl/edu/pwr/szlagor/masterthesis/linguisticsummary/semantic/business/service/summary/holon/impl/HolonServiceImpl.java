package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.model.summary.HolonDto;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.holon.HolonService;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.Holon;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.HolonRepository;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.source.business.converter.LocalDateConverter;

/**
 * Created by Pawel on 2017-03-17.
 */
@Service
public class HolonServiceImpl implements HolonService {

    private final HolonRepository holonRepository;

    @Autowired
    public HolonServiceImpl(HolonRepository holonRepository) {
        this.holonRepository = holonRepository;
    }

    @Override
    public void save(List<HolonDto> holons) {
        holonRepository.save(getMapperFacade().mapAsList(holons, Holon.class));
    }

    @Override
    public void save(HolonDto holon) {
        holonRepository.save(getMapperFacade().map(holon, Holon.class));
    }

    @Override
    public List<HolonDto> findAll() {
        return getMapperFacade().mapAsList(holonRepository.findAll(), HolonDto.class);
    }

    @Override
    public List<HolonDto> findByRelevanceBetween(Double gte, Double le) {
        return getMapperFacade().mapAsList(holonRepository.findByRelevanceBetween(gte, le), HolonDto.class);
    }

    protected MapperFacade getMapperFacade() {
        MapperFactory factory = new DefaultMapperFactory.Builder().classMapBuilderFactory(new ClassMapBuilder.Factory()).build();
        factory.getConverterFactory().registerConverter(new LocalDateConverter());
        factory.classMap(HolonDto.class, Holon.class).exclude("children").exclude("predicate").byDefault().register();
        factory.classMap(Holon.class, HolonDto.class).exclude("predicate").byDefault().register();
        return factory.getMapperFacade();
    }
}
