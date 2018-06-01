package com.ciandt.paul;

import com.ciandt.paul.context.Context;
import com.ciandt.paul.entity.FifaRank;
import com.ciandt.paul.entity.Match;
import com.ciandt.paul.entity.Prediction;
import com.ciandt.paul.entity.TeamHistory;

import java.util.OptionalDouble;

public class Russia2018WorldCupPredictor implements Predictor {

    @Override
    public Prediction predict(Match match, Context context)
    {
        Prediction prediction = new Prediction(match);

        System.out.println("Match: " + match.toString());

        OptionalDouble homeScoreAvg = context.getHomeTeamScoreAvg();

        if(homeScoreAvg.isPresent()) {
            Double homeScore = Math.floor(homeScoreAvg.getAsDouble());
            System.out.println("Home Score: " + homeScore);
            prediction.setHomeScore(homeScore.intValue());
        }

        OptionalDouble awayScoreAvg = context.getAwayTeamScoreAvg();

        if(awayScoreAvg.isPresent()) {
            Double awayScore = Math.floor(awayScoreAvg.getAsDouble());
            System.out.println("Away Score: " + awayScore);
            prediction.setAwayScore(awayScore.intValue());
        }

        FifaRank homeFifaRank = context.getHomeFifaRank();
        FifaRank awayFifaRank = context.getAwayFifaRank();

        if(prediction.getHomeScore() == null)
        {
            if(homeFifaRank.getPoints() > awayFifaRank.getPoints()) {
                prediction.setHomeScore(1);
            } else {
                prediction.setHomeScore(0);
            }
        }

        if(prediction.getAwayScore() == null)
        {
            if(homeFifaRank.getPoints() < awayFifaRank.getPoints()) {
                prediction.setAwayScore(1);
            } else {
                prediction.setAwayScore(0);
            }
        }

        return prediction;
    }
}
