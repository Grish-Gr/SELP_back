package grsh.grdv.SELP_back.service;

import grsh.grdv.SELP_back.dto.request.MoodStatRequestDto;
import grsh.grdv.SELP_back.dto.request.ResultTestRequestDto;
import grsh.grdv.SELP_back.dto.request.SleepStatRequestDto;
import grsh.grdv.SELP_back.entities.MoodStatistic;
import grsh.grdv.SELP_back.entities.ResultTestStatistic;
import grsh.grdv.SELP_back.entities.SleepStatistic;
import grsh.grdv.SELP_back.entities.User;
import grsh.grdv.SELP_back.repositories.MoodStatisticRepository;
import grsh.grdv.SELP_back.repositories.ResultTestStatisticRepository;
import grsh.grdv.SELP_back.repositories.SleepStatisticsRepository;
import grsh.grdv.SELP_back.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StatisticService {

    @Autowired
    private ResultTestStatisticRepository resultTestStatisticRepository;
    @Autowired
    private SleepStatisticsRepository sleepStatisticsRepository;
    @Autowired
    private MoodStatisticRepository moodStatisticRepository;
    @Autowired
    private UserRepository userRepository;

    public List<SleepStatistic> saveSleepStatistic(List<SleepStatRequestDto> sleepStats, Long idUser) {
        return sleepStatisticsRepository.saveAll(sleepStats.stream().map(sleepStat -> {
            User userInSystem = userRepository.getReferenceById(idUser);

            SleepStatistic sleepStatistic = new SleepStatistic();
            sleepStatistic.setDate(new Date(sleepStat.date()));
            sleepStatistic.setTimeSleepInHour(sleepStat.timeSleep());
            sleepStatistic.setUser(userInSystem);

            return sleepStatistic;
        }
        ).toList());
    }

    public List<MoodStatistic> saveMoodStatistic(List<MoodStatRequestDto> moodStats, Long idUser) {
        return moodStatisticRepository.saveAll(moodStats.stream().map(moodStat -> {
                User userInSystem = userRepository.getReferenceById(idUser);

                MoodStatistic moodStatistic = new MoodStatistic();
                moodStatistic.setDate(new Date(moodStat.date()));
                moodStatistic.setMoodState(moodStat.moodState());
                moodStatistic.setUser(userInSystem);

                return moodStatistic;
            }
        ).toList());
    }

    public List<ResultTestStatistic> saveResultTestStatistic(List<ResultTestRequestDto> resultTests, Long idUser) {
        return resultTestStatisticRepository.saveAll(resultTests.stream().map(resultTest -> {
                User userInSystem = userRepository.getReferenceById(idUser);

                ResultTestStatistic resultTestStatistic = new ResultTestStatistic();
                resultTestStatistic.setResultTestType(resultTest.resultTestType());
                resultTestStatistic.setResultTestState(resultTest.resultTestState());
                resultTestStatistic.setUser(userInSystem);

                return resultTestStatistic;
            }
        ).toList());
    }
}
