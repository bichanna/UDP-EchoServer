package com.nobuharushimazu;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Echoer extends Thread{
    private byte[] buffer;
    private DatagramPacket packet;
    private DatagramSocket socket;

    public Echoer(byte[] buffer, DatagramPacket packet, DatagramSocket socket) {
        this.buffer = buffer;
        this.packet = packet;
        this.socket = socket;
    }

    @Override
    public void run() {
        // send back the received string
        System.out.println("Text received is: " + new String(buffer, 0, packet.getLength()));
        String returnString = "echo: " + new String(buffer, 0, packet.getLength());
        byte[] buffer2 = returnString.getBytes();
        InetAddress address = packet.getAddress();
        int port = packet.getPort();
        packet = new DatagramPacket(buffer2, buffer2.length, address, port);

        // sleep
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted: " + e.getLocalizedMessage());
//        }

        try {
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}





