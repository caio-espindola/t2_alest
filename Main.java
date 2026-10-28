import java.util.Scanner;
import javax.naming.OperationNotSupportedException;

public class Main{

    private static Scanner input = new Scanner(System.in);

    private static Deck<String>          return_stack  = new Deck<String>(6); // current is return.getLast() / tail
    private static Deck<String>          forward_stack = new Deck<String>(5);

    private static List<String>          history       = new List<String>();
    private static ExclusiveList<String> favorites     = new ExclusiveList<String>();



    public static void main(String[] args) {
        while (true){
            try {

                System.out.println(prompt() + "\n");

            } catch (Exception e) {

                System.out.println(Erro.e[0] + e.getMessage());

            }
            
        }
    }

    private static String prompt() throws Exception {

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
                result = Erro.e[1];
        }

        return result;
    }

    private static String visitar(String url) throws Exception {

        if (url.equals("")){
            return Erro.e[7];
        }

        try {

            if (!url.equals(return_stack.getLast())) {
                return_stack.addLast(url);
                history.add(0, url);
            }

        } catch (NullPointerException e){

            return_stack.addLast(url);
            history.add(0, url);

        }

        return "Site Atual: " + url;

    }

    private static String voltar() throws OperationNotSupportedException{

        try {

            forward_stack.addLast(return_stack.removeLast());
            
        } catch (NullPointerException e) {

            return Erro.e[5];

        }

        try {

            return "Site Atual: " + return_stack.getLast();

        } catch (NullPointerException e){

            return Erro.e[5];

        }

    }

    private static String avancar() throws OperationNotSupportedException{

        try {

            return_stack.addLast(forward_stack.removeLast());
            
        } catch (NullPointerException e) {

            return Erro.e[4];

        }
        
        return "Site Atual: " + return_stack.getLast();

    }

    private static String atual(){

        if (return_stack.isEmpty()) {
            return Erro.e[8];
        }

        return "Site Atual: " + return_stack.getLast();

    }

    private static String historico(){

        if (history.isEmpty()) {
            return Erro.e[2];
        }

        String h = "Historico:";

        for (String elem : history){
            h += "\n" + elem;
        }

        return h;

    }

    private static String favoritar(String url){

        if (url.equals("")){
            return Erro.e[7];
        }

        if (url.equals("atual")){

            try {
                return favoritar(return_stack.getLast());
            } catch (NullPointerException e){
                return Erro.e[8];
            }

        }

        boolean b = favorites.add(url);

        if (b){

            return "Site " + url + " Favoritado";
            
        } else {

            return Erro.e[6];

        }

    }

    private static String favoritos(){

        if (favorites.isEmpty()){
            return Erro.e[3];
        }

        String f = "Favoritos:";

        for (String elem : favorites){
            f += "\n" + elem;
        }

        return f;

    }
}