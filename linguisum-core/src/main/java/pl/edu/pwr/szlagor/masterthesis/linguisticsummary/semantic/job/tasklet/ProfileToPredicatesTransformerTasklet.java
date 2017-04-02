package pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.job.tasklet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.business.service.summary.predicate.CategoryPredicateTypes;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.persistence.summary.SummaryProfile;
import pl.edu.pwr.szlagor.masterthesis.linguisticsummary.semantic.repository.SummaryProfileRepository;

/**
 * Created by Pawel on 2017-04-02.
 */
@Component
public class ProfileToPredicatesTransformerTasklet implements Tasklet {
    private final SummaryProfileRepository summaryProfileRepository;
    private final ProfilesCombinationsCache cache;

    @Autowired
    public ProfileToPredicatesTransformerTasklet(SummaryProfileRepository summaryProfileRepository, ProfilesCombinationsCache cache) {
        this.summaryProfileRepository = summaryProfileRepository;
        this.cache = cache;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        summaryProfileRepository.findAll().forEach(p -> cache.getProfilesCombinations().addAll(createCombination(p)));
        System.out.println("Stworzono: " + cache.getProfilesCombinations().size() + " kombinacji");
        return RepeatStatus.FINISHED;
    }

    private List<List<CategoryPredicateTypes>> createCombination(SummaryProfile p) {
        final Set<Set<CategoryPredicateTypes>> factorsComb = Sets.powerSet(p.getSummaryfactors());
        final List<List<CategoryPredicateTypes>> collect = factorsComb.stream().filter(s -> !s.isEmpty()).map(Lists::newArrayList).collect(
                Collectors.toList());
        collect.forEach(f -> f.add(p.getResultFactor()));
        return collect;
    }
}
