import java.util.Scanner;

public class GameFin {
    public GameFin(){


    }
    public void lose(){


        System.out.println("                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "YYYYYYY       YYYYYYY                                     LLLLLLLLLLL                                                        tttt          \n" +
                "Y:::::Y       Y:::::Y                                     L:::::::::L                                                     ttt:::t          \n" +
                "Y:::::Y       Y:::::Y                                     L:::::::::L                                                     t:::::t          \n" +
                "Y::::::Y     Y::::::Y                                     LL:::::::LL                                                     t:::::t          \n" +
                "YYY:::::Y   Y:::::YYYooooooooooo   uuuuuu    uuuuuu         L:::::L                  ooooooooooo       ssssssssss   ttttttt:::::ttttttt    \n" +
                "   Y:::::Y Y:::::Y oo:::::::::::oo u::::u    u::::u         L:::::L                oo:::::::::::oo   ss::::::::::s  t:::::::::::::::::t    \n" +
                "    Y:::::Y:::::Y o:::::::::::::::ou::::u    u::::u         L:::::L               o:::::::::::::::oss:::::::::::::s t:::::::::::::::::t    \n" +
                "     Y:::::::::Y  o:::::ooooo:::::ou::::u    u::::u         L:::::L               o:::::ooooo:::::os::::::ssss:::::stttttt:::::::tttttt    \n" +
                "      Y:::::::Y   o::::o     o::::ou::::u    u::::u         L:::::L               o::::o     o::::o s:::::s  ssssss       t:::::t          \n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u         L:::::L               o::::o     o::::o   s::::::s            t:::::t          \n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u         L:::::L               o::::o     o::::o      s::::::s         t:::::t          \n" +
                "       Y:::::Y    o::::o     o::::ou:::::uuuu:::::u         L:::::L         LLLLLLo::::o     o::::ossssss   s:::::s       t:::::t    tttttt\n" +
                "       Y:::::Y    o:::::ooooo:::::ou:::::::::::::::uu     LL:::::::LLLLLLLLL:::::Lo:::::ooooo:::::os:::::ssss::::::s      t::::::tttt:::::t\n" +
                "    YYYY:::::YYYY o:::::::::::::::o u:::::::::::::::u     L::::::::::::::::::::::Lo:::::::::::::::os::::::::::::::s       tt::::::::::::::t\n" +
                "    Y:::::::::::Y  oo:::::::::::oo   uu::::::::uu:::u     L::::::::::::::::::::::L oo:::::::::::oo  s:::::::::::ss          tt:::::::::::tt\n" +
                "    YYYYYYYYYYYYY    ooooooooooo       uuuuuuuu  uuuu     LLLLLLLLLLLLLLLLLLLLLLLL   ooooooooooo     sssssssssss              ttttttttttt  \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           \n" +
                "                                                                                                                                           "
        );
        ask();
    }
    private void ask(){
        System.out.println("Enter r to restart, m for the main menu or q to quit");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        if (input== "m"){
            //will be implemented

        }
        if(input == "r"){

        }
        if(input == "q"){

        }else {
            ask();
        }

    }
    public void win(){
        System.out.println("                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "YYYYYYY       YYYYYYY                                     WWWWWWWW                           WWWWWWWW                                \n" +
                "Y:::::Y       Y:::::Y                                     W::::::W                           W::::::W                                \n" +
                "Y:::::Y       Y:::::Y                                     W::::::W                           W::::::W                                \n" +
                "Y::::::Y     Y::::::Y                                     W::::::W                           W::::::W                                \n" +
                "YYY:::::Y   Y:::::YYYooooooooooo   uuuuuu    uuuuuu        W:::::W           WWWWW           W:::::W ooooooooooo   nnnn  nnnnnnnn    \n" +
                "   Y:::::Y Y:::::Y oo:::::::::::oo u::::u    u::::u         W:::::W         W:::::W         W:::::Woo:::::::::::oo n:::nn::::::::nn  \n" +
                "    Y:::::Y:::::Y o:::::::::::::::ou::::u    u::::u          W:::::W       W:::::::W       W:::::Wo:::::::::::::::on::::::::::::::nn \n" +
                "     Y:::::::::Y  o:::::ooooo:::::ou::::u    u::::u           W:::::W     W:::::::::W     W:::::W o:::::ooooo:::::onn:::::::::::::::n\n" +
                "      Y:::::::Y   o::::o     o::::ou::::u    u::::u            W:::::W   W:::::W:::::W   W:::::W  o::::o     o::::o  n:::::nnnn:::::n\n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u             W:::::W W:::::W W:::::W W:::::W   o::::o     o::::o  n::::n    n::::n\n" +
                "       Y:::::Y    o::::o     o::::ou::::u    u::::u              W:::::W:::::W   W:::::W:::::W    o::::o     o::::o  n::::n    n::::n\n" +
                "       Y:::::Y    o::::o     o::::ou:::::uuuu:::::u               W:::::::::W     W:::::::::W     o::::o     o::::o  n::::n    n::::n\n" +
                "       Y:::::Y    o:::::ooooo:::::ou:::::::::::::::uu              W:::::::W       W:::::::W      o:::::ooooo:::::o  n::::n    n::::n\n" +
                "    YYYY:::::YYYY o:::::::::::::::o u:::::::::::::::u               W:::::W         W:::::W       o:::::::::::::::o  n::::n    n::::n\n" +
                "    Y:::::::::::Y  oo:::::::::::oo   uu::::::::uu:::u                W:::W           W:::W         oo:::::::::::oo   n::::n    n::::n\n" +
                "    YYYYYYYYYYYYY    ooooooooooo       uuuuuuuu  uuuu                 WWW             WWW            ooooooooooo     nnnnnn    nnnnnn\n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     \n" +
                "                                                                                                                                     "
        );
        System.out.println("Please enter your name to save the score:");
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
    }
}
