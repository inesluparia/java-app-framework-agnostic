package ines.gameinfo.cli;

import ines.gameinfo.cli.service.APICourse;
import ines.gameinfo.cli.service.GameRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class GameRetriever {
    public static final Logger LOG = LoggerFactory.getLogger(GameRetriever.class);
    public static void main (String... args) {
        LOG.info("App starting :)");

        if (args.length == 0) {
            LOG.warn("Please provide a username as first argument");
            return;
        };
        try {
            retrieveGames(args[0]);
        } catch (Exception e) {
            LOG.error("unexpected error", e);
        }
    }

    private static void retrieveGames(String authorId) {
        LOG.info("retrieving games for user '{}'", authorId);
        GameRetrievalService gameRetrievalService = new GameRetrievalService();

        List<APICourse> gamesRetrieved = gameRetrievalService.getGamesFor( authorId )
                .stream()
                .filter( course -> !course.isRetired())
//              .filter(not(APICourse::isRetired))
                .toList();

        LOG.info("Retrieved the following {} games {}", gamesRetrieved.size(), gamesRetrieved);
    }

}
