package com.greensteam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.greensteam.proto.Greensteam.Game;
import com.greensteam.proto.Greensteam.Reviews;
import com.greensteam.proto.Greensteam.User;

public class GreenSteamClient {
    
    GreenSteamProxy proxy;

	
	public GreenSteamClient() {
		this.proxy = new GreenSteamProxy();

	}
	
    public String selecionaOperacao() throws IOException {

		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		String opt = null;
		do {
			opt = stdin.readLine();
		} while (opt.equals("\n") || opt.equals("") || opt.isEmpty());
		switch (opt) {
		case "Checar atividade":

			User.Builder profile = User.newBuilder();
			System.out.print("\nDigite o nome do usuário: ");

			profile.setName(stdin.readLine());

			try {
				Game activity = proxy.getLastPlayedGame(profile);
				double reviewsPercentage = activity.getReviewsPercentage();

				System.out.println("\nUltimo jogo jogado: " + activity.getName());
				System.out.println("Descrição do jogo: " + activity.getDescription());
				System.out.println("Quantidade de downloads: " + activity.getDownloadQuantity());
				System.out.println("Metacritc: " + reviewsPercentage);

			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			
			break;

		case "Encontrar desenvolvedora":

			Game.Builder game = Game.newBuilder();
			System.out.print("\nDigite o nome do jogo: ");
			game.setName(stdin.readLine());
			

			try {
				com.greensteam.proto.Greensteam.Publisher pub = proxy.getPublisher(game);
				System.out.println("\nNome da Empresa: " + pub.getName());
				System.out.println("Quantidade de seguidores: " + pub.getFollowers());

			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}

			break;

		case "Obter avaliações":

			Game.Builder gameReviews = Game.newBuilder();
			System.out.print("\nDigite o nome do jogo: ");

			gameReviews.setName(stdin.readLine());
			
			try {
				Reviews review = proxy.getReviews(gameReviews);
				int size = review.getCommentsList().size();

				System.out.println("\nAvaliações:");
				for(int i = 0; i < size; i++) {
					System.out.println("Autor: " + review.getComments(i).getAuthorName());
					System.out.println("Avaliação: " + review.getComments(i).getContent());
					System.out.println("Recomendação: " + (review.getComments(i).getRecomendation() ? "Recomendo" : "Não recomendo"));
				}

			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
			
			break;

		case "Finalizar":
			break;

		default:
			System.out.println("Operação invalida, tente outra.");
			break;
		}
		return opt;
	}

    public void printMenu() {
		System.out.println("\nDigite a operação que deseja executar: ");
		System.out.println("Checar atividade - Consulta o ultimo jogo jogado");
		System.out.println("Encontrar desenvolvedora - Consultar a empresa desenvolvedora do jogo");
		System.out.println("Obter avaliações - Consulta as avaliações do jogo");
		System.out.println("Finalizar - Encerrar contato com o servidor\n");
	}

    public static void main(String[] args) {
		GreenSteamClient greenSteamClient = new GreenSteamClient();
		String operacao = "empty";
		do {
			greenSteamClient.printMenu();
			try {
				operacao = greenSteamClient.selecionaOperacao();
			} catch (IOException ex) {
				ex.printStackTrace();
				System.out.println("Escolha uma das operações pelo número: " + ex);
			}
		} while (!operacao.equals("Finalizar"));
	}

}