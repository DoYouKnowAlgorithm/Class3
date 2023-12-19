public class Programmers_42577 {
  public static void main(String[] args) {
    String[] phone_book = {"123","456","789"};

    Arrays.sort(phone_book);

    boolean answer = true;

    for (int i = 1; i < phone_book.length; i ++) {
      String prefix = phone_book[i - 1];

      if (phone_book[i].startsWith(prefix)) {
        answer = false;
        break;
      }
    }

    System.out.println(answer);
  }
}