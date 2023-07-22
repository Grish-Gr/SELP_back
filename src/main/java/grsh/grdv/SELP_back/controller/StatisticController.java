package grsh.grdv.SELP_back.controller;

import grsh.grdv.SELP_back.dto.request.MoodStatRequestDto;
import grsh.grdv.SELP_back.dto.request.ResultTestRequestDto;
import grsh.grdv.SELP_back.dto.request.SleepStatRequestDto;
import grsh.grdv.SELP_back.entities.MoodStatistic;
import grsh.grdv.SELP_back.entities.ResultTestStatistic;
import grsh.grdv.SELP_back.entities.SleepStatistic;
import grsh.grdv.SELP_back.security.JwtAuthentication;
import grsh.grdv.SELP_back.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@PreAuthorize("hasAuthority('USER_PERMISSION')")
@RequestMapping("statistic")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @PostMapping("/save/sleep-stat")
    public ResponseEntity<?> saveSleepStat(List<SleepStatRequestDto> sleepStats, JwtAuthentication jwtAuthentication) {
        List<SleepStatistic> sleepStatistics = statisticService.saveSleepStatistic(sleepStats, jwtAuthentication.getUserID());
        return ResponseEntity.ok(sleepStatistics.stream().map(SleepStatistic::getId).toList());
    }

    @PostMapping("/save/mood-stat")
    public ResponseEntity<?> saveMoodStat(List<MoodStatRequestDto> moodStats, JwtAuthentication jwtAuthentication) {
        List<MoodStatistic> moodStatistics = statisticService.saveMoodStatistic(moodStats, jwtAuthentication.getUserID());
        return ResponseEntity.ok(moodStatistics.stream().map(MoodStatistic::getId).toList());
    }

    @PostMapping("/save/result-test")
    public ResponseEntity<?> saveResultTests(List<ResultTestRequestDto> resultTests, JwtAuthentication jwtAuthentication) {
        List<ResultTestStatistic> sleepStatistics = statisticService.saveResultTestStatistic(
            resultTests, jwtAuthentication.getUserID()
        );
        return ResponseEntity.ok(sleepStatistics.stream().map(ResultTestStatistic::getId).toList());
    }
}
