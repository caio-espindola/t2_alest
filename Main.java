import java.util.Scanner;

public class Main{

    private static Scanner input = new Scanner(System.in);

    private static Deck<String>          return_stack  = new Deck<String>(5); // current is return.getLast() / tail
    private static Deck<String>          forward_stack = new Deck<String>(5);

    private static List<String>          history       = new List<String>();
    private static ExclusiveList<String> favorites     = new ExclusiveList<String>();



    public static void main(String[] args) {
        while (true){
            System.out.println(prompt());
        }
    }

    private static String prompt(){

        String result;

        String cmdfull = "";
        String cmd[] = {"", ""};

        System.out.print(">");
        cmdfull = input.nextLine();
        cmdfull = cmdfull.toLowerCase();

        cmd = cmdfull.split("\s");

        switch (cmd[0]){
            case "visitar":
                result = visitar(cmd[1]);
                break;

            case "voltar":
                result = voltar();
                break;

            case "avancar":
                result = avancar();
                break;

            case "atual":
                result = atual();
                break;

            case "historico":
                result = historico();
                break;

            case "favoritar":
                result = favoritar(cmd[1]);
                break;

            case "favoritos":
                result = favoritos();
                break;

            case "sair":
                System.exit(0);

            default:
                result = "Unknown Command";
        }

        return result;
    }

    private static String visitar(String url){

    }

    private static String voltar(){

    }

    private static String avancar(){

    }

    private static String atual(){

    }

    private static String historico(){

    }

    private static String favoritar(String url){

    }

    private static String favoritos(){

    }
}