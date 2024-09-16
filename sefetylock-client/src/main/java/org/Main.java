package org;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.entities.Password;
import org.entities.User;

public class Main {
    ProxyClient proxy;
    Gson gson;
    private User loggedInUser;

    public Main() {
        this.proxy = new ProxyClient();
        this.gson = new Gson();
        this.loggedInUser = null;
    }

    public int selecionaOperacao() throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int opt = 0;
        String input;

        do {
            input = stdin.readLine().trim();
            try {
                opt = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
            }
        } while (opt < 1 || opt > (loggedInUser != null ? 6 : 3)); // Ajusta o número máximo com base no estado de login

        switch (opt) {
            case 1:
                User createdUser = new User();

                System.out.println("\nDigite o seu nome");
                createdUser.setUsername(stdin.readLine());

                System.out.println("\nDigite o seu e-mail");
                createdUser.setEmail(stdin.readLine());

                System.out.println("\nDigite sua senha");
                createdUser.setUserpass(stdin.readLine());

                try {
                    User created = proxy.sing_up(createdUser);
                    System.out.println("Usuário criado com sucesso");
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 2:
                User user = new User();
                System.out.print("\nDigite o seu e-mail: ");
                user.setEmail(stdin.readLine());

                System.out.print("\nDigite sua senha: ");
                user.setUserpass(stdin.readLine());

                try {
                    User userLoged = proxy.login(user);
                    if (userLoged != null) {
                        loggedInUser = userLoged;
                        System.out.println("Login bem-sucedido!");
                        System.out.println(userLoged);
                    } else {
                        System.out.println("Falha no login.");
                    }
                } catch (RuntimeException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case 3:
                System.out.println("Saindo...");
                break;

            case 4:
                if (loggedInUser != null) {
                    Password createPassword = new Password();
                    System.out.print("\nDigite o título/nome da senha: ");
                    createPassword.setTitle(stdin.readLine());

                    System.out.print("\nDigite a senha que você deseja guardar: ");
                    createPassword.setPassword(stdin.readLine());
                    createPassword.setUserId(loggedInUser.getId());

                    try {
                        Password createdPassword = proxy.salvar_senha(createPassword);
                        System.out.println(createdPassword);
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Você deve estar logado para salvar uma senha.");
                }
                break;

            case 5:
                if (loggedInUser != null) {
                    try {
                        List<Password> passwords = proxy.listar_senhas(loggedInUser.getId());
                        if (passwords != null && !passwords.isEmpty()) {
                            System.out.println("\nSenhas:");
                            for (Password password : passwords) {
                                System.out.println(password);
                            }
                        } else {
                            System.out.println("Nenhuma senha encontrada.");
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.out.println("Você deve estar logado para listar as senhas.");
                }
                break;

            case 6:
                if (loggedInUser != null) {
                    loggedInUser = null;
                    System.out.println("Logout realizado com sucesso.");
                } else {
                    System.out.println("Você não está logado.");
                }
                break;

            default:
                System.out.println("Operação inválida, tente outra.");
                break;
        }
        return opt;
    }

    public void printMenu() {
        System.out.println("\nDigite o número da operação que deseja executar: \n");
        if (loggedInUser == null) {
            System.out.println("(1) Cadastrar-se");
            System.out.println("(2) Login");
        }
        if (loggedInUser != null) {
            System.out.println("(4) Salvar senha");
            System.out.println("(5) Listar senhas");
            System.out.println("(6) Logout");
        }
        if (loggedInUser == null) {
            System.out.println("(3) Sair\n");
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        int operacao = 0;

        do {
            main.printMenu();
            try {
                operacao = main.selecionaOperacao();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Escolha uma das operações pelo número: " + ex);
            }
        } while (operacao != 3);
    }
}
