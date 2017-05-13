package com.mzps.web.tourneys;


import com.mzps.model.MatchResult;
import com.mzps.repository.MatchResultRepository;
import com.mzps.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("matchResultService")
@Transactional
public class MatchResultServiceImpl implements MatchResultService {

    @Autowired
    private MatchResultRepository matchResultRepository;


    @Override
    public MatchResult findById(Long id) {
        return matchResultRepository.findOne(id);
    }

    @Override
    public void saveMatchResult(MatchResult matchResult) {
        matchResultRepository.save(matchResult);
    }

    @Override
    public void updateMatchResult(MatchResult matchResult) {
        saveMatchResult(matchResult);
    }

    @Override
    public List<MatchResult> findAllMatchResults() {
        return matchResultRepository.findAll();
    }

    @Override
    public void deleteMatchResultById(Long id) {
        matchResultRepository.delete(id);
    }

    @Override
    public void deleteAllMatchResults() {
        matchResultRepository.deleteAll();
    }
}
