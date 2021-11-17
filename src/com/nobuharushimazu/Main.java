package com.nobuharushimazu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(6000);
            while (true) {
                byte[] buffer = new byte[400];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                new Echoer(buffer, packet, socket).start();
            }
        } catch (SocketException e) {
            System.out.println("SocketException: " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getLocalizedMessage());
        }
    }
}
