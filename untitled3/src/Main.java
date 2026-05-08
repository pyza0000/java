import java.util.function.*;
public static String ChangeLetters (String Text){
    return Text.toUpperCase();
}
void main() {
    Supplier<String> lambdaProsta = () -> "Hello World";
    System.out.println("1." + lambdaProsta.get());

    Function<Integer, Integer> lambdaBlokowa = (x) -> {
        int result = x * x;
        return result;
    };
    System.out.println("2." + lambdaBlokowa.apply(5));

    Function<String, String> lambdaKlasa = Main::ChangeLetters;
    System.out.println("3. Metoda w klasie: " + ChangeLetters.apply("java"));

}
