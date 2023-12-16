class Programmers_1845 {
  public static void main(String[] args) {
    int[] nums = {3,3,3,2,2,4};

    Set<Integer> collect = Arrays.stream(nums).boxed().collect(Collectors.toSet());

    int answer = Math.min(collect.size(), nums.length / 2);

    System.out.println(answer);
  }
}