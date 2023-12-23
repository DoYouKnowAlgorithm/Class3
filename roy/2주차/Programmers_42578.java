import java.util.HashMap;
import java.util.Map;

class Scratch {
  public static void main(String[] args) {
    String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

    int answer = 1;

    Map<String, Integer> map = clothesCount(clothes);

    for (String s : map.keySet()) {
      answer *= (map.get(s)+1);
    }

    answer =  answer - 1;

    System.out.println(answer);
  }

  private static Map<String, Integer> clothesCount(String[][] clothes) {
    Map<String, Integer> map = new HashMap<>();

    for (String[] clothe : clothes) {
      map.put(clothe[1], map.getOrDefault(clothe[1], 0) + 1);
    }

    return map;
  }
}