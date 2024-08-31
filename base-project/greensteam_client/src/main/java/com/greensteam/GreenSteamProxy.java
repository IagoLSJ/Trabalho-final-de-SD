package com.greensteam;

import java.net.SocketTimeoutException;
import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.greensteam.proto.Greensteam.Game;
import com.greensteam.proto.Greensteam.Message;
import com.greensteam.proto.Greensteam.Publisher;
import com.greensteam.proto.Greensteam.Reviews;
import com.greensteam.proto.Greensteam.User;

public class GreenSteamProxy {

    int requestId = 0;
	UDPClient udpClient = new UDPClient("localhost", 9090);

	public Game getLastPlayedGame(User.Builder profile) throws InvalidProtocolBufferException {

		Game game = Game.parseFrom(doOperation("Game", "get_last_played_game", profile.build().toByteArray()));

		return game;
	}

	public Publisher getPublisher(Game.Builder game) throws InvalidProtocolBufferException {
		
		Publisher publisher = Publisher.parseFrom(doOperation("Publisher", "get_publisher", game.build().toByteArray()));
		
		return publisher;
	}

	public Reviews getReviews(Game.Builder game) throws InvalidProtocolBufferException {
		
		Reviews reviews = Reviews.parseFrom(doOperation("Comment", "get_reviews", game.build().toByteArray()));
		
		return reviews;
	}

	public byte[] doOperation(String objectRef, String method, byte[] args) throws InvalidProtocolBufferException {
		
		int attempt = 0;
		byte[] data = empacotaMensagem(objectRef, method, args);

		while (attempt++ < 5) {
			try {
				udpClient.sendRequest(data);

				Message resposta = desempacotaMensagem(udpClient.getReply());

				if (!resposta.getError().getError().equals("")) {
					throw new RuntimeException(resposta.getError().getError());
				}

				return resposta.getArguments().toByteArray();

			} catch (SocketTimeoutException ste) {
				System.out.println("Socket timeout occurred. Retrying...");

			} catch (InvalidProtocolBufferException e) {
				e.printStackTrace();
			}
		}

		throw new RuntimeException("Operation couldn't be completed after 5 attempts.");
	}

	private byte[] empacotaMensagem(String objectRef, String method, byte[] args) {

		Message message = Message.newBuilder()
			.setType(0)
			.setId(this.requestId++)
			.setObfReference(objectRef)
			.setMethodId(method)
			.setArguments(ByteString.copyFrom(args))
			.build();

        return message.toByteArray();
	}

	private Message desempacotaMensagem(byte[] resposta) throws InvalidProtocolBufferException {

		return Message.parseFrom(resposta);
	}
}
