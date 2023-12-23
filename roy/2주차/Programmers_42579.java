import java.util.*;
import java.util.stream.Collectors;

class Scratch {
  public static void main(String[] args) {
    String[] genres = {"classic", "pop", "classic", "classic", "pop"};
    int[] plays = {500, 600, 150, 800, 2500};

    Map<String, List<Pair<Integer, Integer>>> map = new HashMap<>();

    for(int i = 0; i < genres.length; i++) {
      map.computeIfAbsent(genres[i], key -> new ArrayList<>()).add(Pair.of(plays[i], i));
    }

    map.forEach((genre, pairs) ->
            pairs.sort((pair1, pair2) -> {
              int compareResult = Integer.compare(pair2.first, pair1.first);
              if (compareResult == 0) {
                return Integer.compare(pair1.second, pair2.second);
              }
              return compareResult;
            })
    );

    List<Map.Entry<String, List<Pair<Integer, Integer>>>> sortedGenres = map.entrySet().stream()
            .sorted((entry1, entry2) -> Integer.compare(
                    entry2.getValue().stream().mapToInt(pair -> pair.first).sum(),
                    entry1.getValue().stream().mapToInt(pair -> pair.first).sum())
            )
            .collect(Collectors.toList());

    List<Integer> indexes = new ArrayList<>();

    for(Map.Entry<String, List<Pair<Integer, Integer>>> e:sortedGenres){
      List<Pair<Integer, Integer>> pairs = e.getValue();

      int size = Math.min(pairs.size(), 2); // 리스트 크기가 2 미만인 경우를 대비

      for(int i = 0; i < size; i++) {
        indexes.add(pairs.get(i).second);  // number 필드를 ans에 추가
      }
    }

    int[] array = indexes.stream().mapToInt(i -> i).toArray();

    for (int a: array) {
      System.out.println(a);
    }
  }

  static class Pair<S, T> {

    private final S first;
    private final T second;

    private Pair(S first, T second) {
      this.first = first;
      this.second = second;
    }

    public static <S, T> Pair<S, T> of(S first, T second) {
      return new Pair<>(first, second);
    }
  }
}