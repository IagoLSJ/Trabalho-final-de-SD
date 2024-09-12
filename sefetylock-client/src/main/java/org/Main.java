package org;

import com.google.gson.Gson;
import org.objects.Password;
import org.objects.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    ProxyClient proxy;
    Gson gson;

    public Main() {
        this.proxy = new ProxyClient();
        this.gson = new Gson();
    }

    public String selecionaOperacao() throws IOException {

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String opt = null;
        do {
            opt = stdin.readLine();
        } while (opt.equals("\n") || opt.equals("") || opt.isEmpty());
        switch (opt) {
            case "Login":

                User user = new User();
                System.out.print("\nDigite o seu e-mail: ");

                user.setEmail(stdin.readLine());

                System.out.print("\nDigite sua senha: ");

                user.setUserpass(stdin.readLine());

                try {
                    User userLoged = proxy.login(user);

                    System.out.println("Olá " + userLoged.getUsername() + "!");

                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case "sing up":

               User createdUser = new User();

                System.out.println("\n Digite o seu nome");
                createdUser.setUsername(stdin.readLine());

                System.out.println("\n Digite o seu e-mail");
                createdUser.setEmail(stdin.readLine());

                System.out.println("\n Digite sua senha");
                createdUser.setUserpass(stdin.readLine());

                try {
                    User created = proxy.sing_up(createdUser);

                    System.out.println("Usuário criado com sucesso");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case "Cadastrar senha":

                Password createPassword = new Password();
                System.out.print("\n Digite o titulo/nome da senha: ");
                createPassword.setTitle(stdin.readLine());

                System.out.print("\n Digite a senha que você deseja guardar: ");
                createPassword.setPassword(stdin.readLine());

                try {
                   Password createdPassword = proxy.salvar_senha(createPassword);

                   System.out.println("Senha criada com sucesso");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }

                break;

            case "Listar senhas":
                try {
                    Password passwords = proxy.listar_senha();
                    System.out.println("\n senhas: "+passwords);
                }catch (RuntimeException e){
                    System.out.println(e.getMessage());
                }
                break;
            case "Encerrar":
                break;
            default:
                System.out.println("Operação invalida, tente outra.");
                break;
        }
        return opt;
    }

    public void printMenu() {
        System.out.println("\nDigite a operação que deseja executar: ");
        System.out.println("sing up");
        System.out.println("Login");
        System.out.println("Guardar senha");
        System.out.println("Listar senhas");
        System.out.println("Encerrar -  Desligar contato com o servidor \n");
    }

    public static void main(String[] args) {

        Main main = new Main();
        String operacao = "empty";
        do {
            main.printMenu();
            try {
                operacao = main.selecionaOperacao();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Escolha uma das operações pelo número: " + ex);
            }
        } while (!operacao.equals("Encerrar"));
    }
}