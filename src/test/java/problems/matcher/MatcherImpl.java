package problems.matcher;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.UUID.fromString;

/*
  Implementation will be coded with following assumptions:

  1. I will use Titles, Actors and Directors names 'as is' without applying Levenshtein distance algorithm.
     So implementation will not provide result or suggestions for possible typos or partial information.

  2. I will use [MediaId] as External ID

  3. If we have multiple Movies with the same Title but different MediaID I will assume that this is a different movie.

  4. Significant matching fields are: [Title], [Actor] and [Director]

  5. I will not count ALL matches for Feed -> when, for example, we will match by title then we will mark this ID as matched and will move to another feed

 */

@Slf4j
public class MatcherImpl implements Matcher {

    private final Trie cast = new Trie();
    private final Trie directors = new Trie();
    private final Trie titles = new Trie();

    public final static int CAST_AND_TITLES_DATA_LENGTH = 3, CAST_AND_TITLES_DATA = 1, CAST_TYPE = 2;
    private final static String regex = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)";

    public MatcherImpl(CsvStream movieDb, CsvStream actorAndDirectorDb) {
        log.info("importing database");

        movieDb.dataRows().forEach(x -> {
            var movie = x.split(regex);
            if (CAST_AND_TITLES_DATA_LENGTH == movie.length && !StringUtils.isBlank(movie[CAST_AND_TITLES_DATA])) {
                titles.insert(movie[1].replace("\"", ""));
            } else log.error("invalid movie data :: " + x);
        });

        actorAndDirectorDb.dataRows().forEach(x -> {
            var creators = x.split(regex);
            if (CAST_AND_TITLES_DATA_LENGTH == creators.length && !StringUtils.isBlank(creators[CAST_AND_TITLES_DATA]) && !StringUtils.isBlank(creators[CAST_TYPE])) {
                if (creators[CAST_TYPE].equalsIgnoreCase("cast")) cast.insert(creators[CAST_AND_TITLES_DATA].replace("\"", ""));
                else if (creators[CAST_TYPE].equalsIgnoreCase("director")) directors.insert(creators[1].replace("\"", ""));
            } else log.error("invalid actors/directors data :: " + x);
        });

        log.info("database imported");
    }

    @Override
    public List<IdMapping> match(DatabaseType databaseType, CsvStream externalDb) {
        var map = new HashMap<String, Integer>();
        AtomicInteger counter = new AtomicInteger();
        AtomicInteger matchByTitle = new AtomicInteger();
        AtomicInteger matchByActor = new AtomicInteger();
        AtomicInteger matchByDirector = new AtomicInteger();

        externalDb.dataRows().forEach(x -> {
            var feed = create(databaseType, x);
            counter.getAndIncrement();
            if(feed != null && feed.isValid()) {
                if(!map.containsKey(feed.getId())) {
                    // match title first
                    boolean hasMatch = titles.contains(feed.getTitle());
                    if(hasMatch) matchByTitle.getAndIncrement();
                    // match cast
                    else {
                        for(int i = 0; i != feed.getCast().size(); i++) {
                            if(cast.contains(feed.getCast().get(i))) {
                                hasMatch = true;
                                matchByActor.getAndIncrement();
                                break;
                            }
                        }
                    }

                    //match directors
                    if(!hasMatch) {
                        for(int i = 0; i != feed.getDirectors().size(); i++) {
                            if(cast.contains(feed.getDirectors().get(i))) {
                                hasMatch = true;
                                matchByDirector.getAndIncrement();
                                break;
                            }
                        }
                    }

                    if(hasMatch) {
                        map.put(feed.getId(), map.size() + 1);
                    }
                }
            }
        });

        log.info("Processed number of items :: " + counter);
        log.info("matches by Title :: " + matchByTitle.get() +
                " matches by Actors :: " + matchByActor.get() +
                " matches by Directors :: " + matchByDirector.get());

        return map.entrySet().stream().map(x -> new IdMapping(x.getValue(), x.getKey())).toList();
    }

    private static Feed create(DatabaseType databaseType, String feed) {
        if(databaseType == DatabaseType.XBOX) return new XBoxFeed(feed);
        return null;
    }

    interface Feed {
        String getId();
        String getTitle();
        List<String> getCast();
        List<String> getDirectors();
        boolean isValid();
    }

    @Getter
    static class XBoxFeed implements Feed {
        XBoxFeed(String feed) {
            var data = feed.split(regex);
            if (XBOX_FEED_LENGTH == data.length) {
                if (!StringUtils.isBlank(data[MediaId]) && canConvertToUUID(data[MediaId])) {
                    id = data[MediaId];
                }

                title = data[Title].replace("\"", "");
                cast = Arrays.stream(data[Actors].replace("\"", "").split(",")).toList();
                directors = Arrays.stream(data[Directors].replace("\"", "").split(",")).toList();
            }

        }

        public final static int XBOX_FEED_LENGTH = 18;
        private final static int MediaId = 2, Title = 3, Actors = 15, Directors = 16;

        private String id;
        private String title;
        private List<String> cast = new ArrayList<>();
        private List<String> directors = new ArrayList<>();

        public boolean isValid() {
            if(getId() == null) return false;
            return (title != null && !StringUtils.isBlank(title)) || !directors.isEmpty() || !cast.isEmpty();
        }

        private static boolean canConvertToUUID(String str) {
            try {
                var UUID = fromString(str);
                return true;
            } catch(IllegalArgumentException ex) {
                return false;
            }

        }
    }
}
