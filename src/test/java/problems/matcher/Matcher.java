package problems.matcher;

import lombok.Getter;

import java.util.List;
import java.util.stream.Stream;

public interface Matcher {

  record IdMapping(int internalId, String externalId) {

  }

  record CsvStream(String headerRow, Stream<String> dataRows) {

  }

  enum DatabaseType {
    XBOX, GOOGLE_PLAY, VUDU, AMAZON_INSTANT
  }

  List<IdMapping> match(DatabaseType databaseType, CsvStream externalDb);
}
