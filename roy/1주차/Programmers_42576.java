
public class programmers_42576 {
  public static void main(String[] args) {
    String answer = "";

    String[] participant = {"leo", "kiki", "eden"};
    String[] completion = {"eden", "kiki"};

    Map<String, Integer> completionCount = new HashMap<>();

    for (String finisher : completion) {
      completionCount.put(finisher, completionCount.getOrDefault(finisher, 0) + 1);
    }

    for (String runner : participant) {
      if (!completionCount.containsKey(runner) || completionCount.get(runner) == 0) {
        answer = runner;
        break;
      }
      completionCount.put(runner, completionCount.get(runner) - 1);
    }

    System.out.println(answer);
  }
}